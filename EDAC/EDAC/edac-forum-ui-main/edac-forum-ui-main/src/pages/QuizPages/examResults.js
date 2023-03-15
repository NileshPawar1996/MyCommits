import { useLocation, useNavigate } from "react-router";
import { QUIZ_SERVICE_URL } from "../../config";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import axios from "axios";
const ExamResults = () => {
    const { state } = useLocation()
    const exam = state.exam
    const student = state.student
    const [score, setScore] = useState()
    const [currentQuestion, setCurrentQuestion] = useState(0)
    const [queNo, setQueNo] = useState(1)
    const [studentExamAnswersDetails, setStudentExamAnswersDetails] = useState([])
    const navigate = useNavigate()
    const loadScore = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/getScore/student/${student.id}/exam/${exam.id}`;
        axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                setScore(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        });
    }
    const loadStudentExamAnswerDetails = () => {
        if (student != null && exam != null) {
            const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/examDetails/student/${student.id}/exam/${exam.id}`;
            axios.get(url).then((response) => {
                const result = response.data;
                if (result["status"] == "success") {
                    setStudentExamAnswersDetails(result["data"]);
                } else {
                    toast.error(result["error"]);
                }
            });
        }

    }
    const handleNext = () => {//skipping an question ==> set selected answer to x 
        // debugger
        setQueNo(queNo + 1)
        setCurrentQuestion(currentQuestion + 1)
        // console.log(currentQuestion)
    }
    const handlePrevious = () => {
        setQueNo(queNo - 1)
        setCurrentQuestion(currentQuestion - 1)
        // console.log(currentQuestion)
    }
    useEffect(() => {
        loadScore()
        loadStudentExamAnswerDetails()
    }, [])
    return (
        <div className="mainDiv">
            <div className="app">
                <div className='question-section'>
                    <div className='score-section'>You have scored {score?.correctAnswers} out of {score?.totalQuestions}</div>
                </div>
                <div className='question-count'>
                    <div >-
                        <span>Question {queNo}</span>/{studentExamAnswersDetails?.length}
                    </div>
                    <div className='question-text'>{studentExamAnswersDetails[currentQuestion]?.question}</div>
                </div>
                <div className='answer-section'>
                    <button disabled={true} className="option-button">{studentExamAnswersDetails[currentQuestion]?.optionA}</button>
                    <button disabled={true} className="option-button">{studentExamAnswersDetails[currentQuestion]?.optionB}</button>
                    <button disabled={true} className="option-button">{studentExamAnswersDetails[currentQuestion]?.optionC}</button>
                    <button disabled={true} className="option-button">{studentExamAnswersDetails[currentQuestion]?.optionD}</button>
                    <hr />
                    <div>
                        <span style={{ color: "Green", marginTop: "20px", marginBottom: "20px" }}>
                            CorrectAnswer: {studentExamAnswersDetails[currentQuestion]?.correctAnswer}
                        </span>
                        <br />
                        {studentExamAnswersDetails[currentQuestion]?.correctAnswer == studentExamAnswersDetails[currentQuestion]?.submittedAnswer && (
                            <span style={{ color: "Green", marginTop: "20px", marginBottom: "20px" }}>
                                Your Selected Answer: {studentExamAnswersDetails[currentQuestion]?.submittedAnswer}
                                <br />
                            </span>

                        )}
                        {studentExamAnswersDetails[currentQuestion]?.correctAnswer != studentExamAnswersDetails[currentQuestion]?.submittedAnswer && (
                            <span style={{ color: "Red", marginTop: "20px", marginBottom: "20px" }}>
                                Your Selected Answer: {studentExamAnswersDetails[currentQuestion]?.submittedAnswer}
                                <br />
                            </span>
                        )}
                        <span style={{ color: "green" }}>
                            <hr />
                            {studentExamAnswersDetails[currentQuestion]?.answerDescription}
                            <hr />
                        </span>
                    </div>
                </div>

                <div className="d-flex justify-content-between">
                    {queNo != 1 && (
                        <div>
                            <button className="btn btn-primary" onClick={handlePrevious}>Previous</button>
                        </div>
                    )}
                    {queNo != studentExamAnswersDetails.length && (
                        <div>
                            <button className="btn btn-primary " onClick={handleNext}>Next</button>
                        </div>
                    )}
                    {queNo == studentExamAnswersDetails.length && (
                        <div>
                            <button className="btn btn-danger" onClick={() => { navigate('/quizzes') }}>Exit</button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    )
}
export default ExamResults

// onClick={navigate('/showQuizzes')}