import { useEffect, useState } from "react"

const ExamQuestionDiv = (props) => {
    const { examQuestion, student, answers, setAnswers } = props
    const [ selectedAnswer, setSelectedAnswer ] = useState()


    const handleSelectedAnswerChange = (event) => {
        setSelectedAnswer(event.target.value)
        // const newSelectedAnswer = {questionId:examQuestion.id, selectedAnswer:event.target.value}
        // setSelectedAnswer(newSelectedAnswer)
        // setAnswers([...answers, selectedAnswer]);
        const newAnswersArray = [...answers]
        console.log(newAnswersArray)
        newAnswersArray[examQuestion.id] = selectedAnswer
        setAnswers(newAnswersArray)
    }
    return (
        <div className="container">
            <div className="row">
                <h4>
                    Q. {examQuestion.question}
                </h4>
                <hr />
            </div>
            <div className="row">
                <div className="col-md-1">
                    <input
                        type="radio"
                        value="A"
                        name={examQuestion.id}
                        onChange={handleSelectedAnswerChange}
                    /> A
                </div>
                <div className="col-md-11">
                    {examQuestion.optionA}
                </div>
                <div className="col-md-1">
                    <input type="radio"
                        value="B"
                        name={examQuestion.id}
                        onChange={handleSelectedAnswerChange} /> B
                </div>
                <div className="col-md-11">
                    {examQuestion.optionB}
                </div>
                <div className="col-md-1">
                    <input type="radio"
                        value="C"
                        name={examQuestion.id}
                        onChange={handleSelectedAnswerChange} /> C
                </div>
                <div className="col-md-11">
                    {examQuestion.optionC}
                </div>
                <div className="col-md-1">
                    <input type="radio"
                        value="D"
                        name={examQuestion.id}
                        onChange={handleSelectedAnswerChange} /> D
                </div>
                <div className="col-md-11">
                    {examQuestion.optionD}
                </div>
            </div>
            <hr />
        </div>
    )
}
export default ExamQuestionDiv