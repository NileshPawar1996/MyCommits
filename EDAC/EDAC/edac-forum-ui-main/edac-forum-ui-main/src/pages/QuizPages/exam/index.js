import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import { QUIZ_SERVICE_URL } from "../../../config";
import axios from "axios";
import { toast } from "react-toastify";
import './index.css'
import { useNavigate } from "react-router";

const Exam = () => {
    const navigate = useNavigate()
    const { state } = useLocation();
    const exam = state.exam
    const student = state.student
    const [examQuestions, setExamQuestions] = useState([])
    const [answers, setAnswers] = useState([])
    const [currentQuestion, setCurrentQuestion] = useState(0)
    const [showScore, setShowScore] = useState(false)
    var [selectedAnswersArray, setSelectedAnswersArray] = useState([])
    // const [countDown, setCountDown] = useState(new Date (exam.endDateTime).getSeconds() - new Date().getSeconds());

    const [timeLeft, setTimeLeft] = useState(Math.abs(new Date(exam?.endDateTime).getTime() - new Date().getTime()) / 1000);

    useEffect(() => {
        if (timeLeft <= 0) {
            handleSubmit()
        }

        const intervalId = setInterval(() => {
            setTimeLeft(timeLeft - 1);
        }, 1000);

        return () => clearInterval(intervalId);
    }, [timeLeft]);
    const minutes = Math.floor(timeLeft / 60);
    const secondsLeft = Math.floor(timeLeft % 60);

    const loadExamQuestions = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/examQuestion/getQuestionsByExamIdDTO/${exam?.id}`;
        axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                setExamQuestions(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        });
    }

    const handlePrevious = () => {
        const prevQue = currentQuestion - 1
        setCurrentQuestion(prevQue)
    }
    const handleAnswerSelect = (event) => {
        // debugger
        let newAnswersArray = [...selectedAnswersArray]
        const ansObj = {
            question: { id: examQuestions[currentQuestion].id },
            exam: { id: exam?.id },
            studentId: student.id,
            selectedAnswer: event.target.value
        }
        // newAnswersArray[currentQuestion] = event.target.value
        newAnswersArray[currentQuestion] = ansObj
        setSelectedAnswersArray(newAnswersArray)
        let nextQue = currentQuestion + 1
        if (nextQue < examQuestions.length) {
            setCurrentQuestion(nextQue)
        } else {
            toast.warning("You have reached the end of the quiz, Click submit to end the quiz")
        }
    }
    const handleSubmit = () => {
        // debugger
        for (let i = 0; i < examQuestions.length; i++) {
            if (selectedAnswersArray[i] == null) {
                const ansObj = {
                    question: { id: examQuestions[i].id },
                    exam: { id: exam?.id },
                    studentId: student.id,
                    selectedAnswer: "x"
                }
                selectedAnswersArray[i] = ansObj
            }
        }
        const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/addAll`
        console.log(selectedAnswersArray)
        axios.post(url, selectedAnswersArray).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                toast.success(result['data'])
                navigate('/examResults', { state: { exam: exam, student: student, studentExamAnswers: selectedAnswersArray } })
            } else {
                toast.error(result['error'])
            }
        })
    }
    const handleNext = () => {//skipping an question ==> set selected answer to x 
        setCurrentQuestion(currentQuestion + 1)
    }

    useEffect(() => {
        loadExamQuestions()
        // console.log(examQuestions)
        // console.log(student)
    }, [])

    return (
        <div className="mainDiv">
            <div className='app'>
                {/* HINT: replace "false" with logic to display the 
      score when the user has answered all the questions */}
                {showScore ? (
                    <div className='score-section'>You scored 1 out of {examQuestions.length}</div>
                ) : (
                    <>
                        <div className='question-section'>
                            <div className='question-count'>
                                <span>Question {currentQuestion + 1}</span>/{examQuestions.length}
                                <span>
                                    Time left: {`${minutes}:${secondsLeft < 10 ? `0${secondsLeft}` : secondsLeft}`}
                                </span>
                            </div>
                            <div className='question-text'>{examQuestions[currentQuestion]?.question}</div>
                        </div>
                        <div className='answer-section'>
                            <button onClick={handleAnswerSelect} value={"A"} className="option-button">{examQuestions[currentQuestion]?.optionA}</button>
                            <button onClick={handleAnswerSelect} value={"B"} className="option-button">{examQuestions[currentQuestion]?.optionB}</button>
                            <button onClick={handleAnswerSelect} value={"C"} className="option-button">{examQuestions[currentQuestion]?.optionC}</button>
                            <button onClick={handleAnswerSelect} value={"D"} className="option-button">{examQuestions[currentQuestion]?.optionD}</button>
                        </div>
                        <div className="d-flex justify-content-between">
                            {currentQuestion != 0 && (
                                <div>
                                    <button className="btn btn-primary" onClick={handlePrevious}>Previous</button>
                                </div>
                            )}
                            {currentQuestion + 1 != examQuestions.length && (
                                <div>
                                    <button className="btn btn-primary" onClick={handleNext}>Next</button>
                                </div>
                            )}
                            {currentQuestion + 1 == examQuestions.length && (
                                <div>
                                    <button className="btn btn-success" onClick={handleSubmit}>Submit</button>
                                </div>
                            )}
                        </div>
                    </>
                )}
            </div>
        </div>

    )
}
export default Exam



{/* <div >
<div>
{
    examQuestions.map((examQue) => {
        return <ExamQuestionDiv examQuestion={examQue} student={student} answers={answers} setAnswers={setAnswers}/>
    })
}
</div>
<div>
    <button onClick={handleSubmit}>Submit</button>
</div>
</div> */}