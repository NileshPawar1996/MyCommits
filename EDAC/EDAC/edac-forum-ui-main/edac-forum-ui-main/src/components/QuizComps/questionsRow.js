import axios from "axios";
import moment from "moment";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL } from "../../config";
import QuestionDescription from "../../components/QuizComps/questionDescription";

const QuestionsRow = (props) => {
    // debugger
    const { question, loadExamsQuestions, examId, isExam } = props;

    const addToExam = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/examQuestion/add`;
        const body = {
            exam: { id: examId },
            question: { id: question.id },
        };
        axios.post(url, body).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                toast.success("Question added successfully");
                loadExamsQuestions(examId)
            } else {
                toast.error(result.status);
            }
        }).catch((error) => {
            if (error.message == 'Request failed with status code 500') {
                toast.error("Questions already present in exam with id = " + examId)
            }
        })
    };
    const deleteFromExam = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/examQuestion/delete/exam/${examId}/question/${question.id}`;
        const body = {
            exam: { id: examId },
            question: { id: question.id },
        };
        axios.delete(url, body).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                toast.success("Question deleted successfully");
                loadExamsQuestions(examId)
            } else {
                toast.error(result["error"]);
            }
        });
    };
    return (

        <tr key={question.id}>
            <td>{question.id}</td>
            <td>{moment(question.creationTime).format("MMMM Do YYYY")}</td>
            <td>{question.question}</td>
            <td>
                <QuestionDescription question={question} />
            </td>
            <td>
                {!isExam && (
                    <button className="btn btn-primary" onClick={addToExam}>
                        Add
                    </button>
                )}
                {isExam && (
                    <button className="btn btn-danger" onClick={deleteFromExam}>
                        Delete
                    </button>
                )}

            </td>
        </tr>
    );
};
export default QuestionsRow;
