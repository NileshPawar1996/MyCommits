import { useEffect, useState } from "react"
import axios from "axios";
import { toast } from "react-toastify";
import { MANAGEMENT_SERVICE_URL, QUIZ_SERVICE_URL } from "../../config";
import StudentExamDetails from "../../components/QuizComps/studentExamDetails";
import {
    MDBBadge
} from "mdb-react-ui-kit";
import moment from "moment";
import { useNavigate } from "react-router";

const styles = {
    spanButton: {
        cursor: "pointer",
    },
};

const CreatedExamRow = (props) => {
    //------------------------------------------ USE STATES ----------------------------------------------------------------------------

    const { exam, loadExams, isSelect, isQuizAttempt, isCreateExam, student } = props
    const [batch, setBatch] = useState("")
    const [module, setModule] = useState("")
    const navigate = useNavigate()
    const [currentTime, setCurrentTime] = useState(new Date());
    const [hasAttempted, setHasAttempted] = useState(true)
    //------------------------------------------ USE EFFECTS ----------------------------------------------------------------------------

    const loadBatch = () => {
        if (exam != null) {
            const url = `${MANAGEMENT_SERVICE_URL}/batch/${exam.batchId}`;
            axios.get(url).then((response) => {
                const result = response.data;
                if (result["status"] == "success") {
                    setBatch(result["data"]);
                } else {
                    toast.error(result["error"]);
                }
            });
        }

    };

    const loadModule = () => {
        if (exam != null) {
            const url = `${MANAGEMENT_SERVICE_URL}/module/${exam.moduleId}`;
            axios.get(url).then((response) => {
                const result = response.data;
                if (result["status"] == "success") {
                    setModule(result["data"]);
                } else {
                    toast.error(result["error"]);
                }
            });
        }

    };
    const loadHasAttempted = () => {
        if (student != null && exam != null) {
            // debugger
            const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/hasAttempted/student/${student.id}/exam/${exam.id}`;
            axios.get(url).then((response) => {
                const result = response.data;
                if (result["status"] == "success") {
                    setHasAttempted(result["data"]);
                } else {
                    toast.error(result["error"]);
                }
            });
        }
    }
    useEffect(() => {
        // console.log(student)
        loadBatch()
        loadModule()
        loadHasAttempted()
    }, [])
    useEffect(() => {
        return () => clearInterval(timer);
    }, [currentTime]);

    const timer = setInterval(() => {
        setCurrentTime(new Date());
    }, 1000);
    //------------------------------------------ HANDLE FUNCTIONS ----------------------------------------------------------------------------
    const deleteExam = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/exam/delete/${exam.id}`;
        axios.delete(url).then((response) => {
            const result = response.data;
            console.log(result)
            if (result["status"] == "success") {
                toast.success(result["data"]);
                loadExams()
            } else if (result['status'] == "error") {
                toast.error("Cannot delete an Exam if it is attempted by a Student or contains questions");
            }
        }).catch((res) => {
            const result = res.response.data;
            console.log(result)
            // debugger
            if (result['status'] == "error") {
                toast.error("Cannot delete an exam if it is attempted by a student or contains questions");
            }
        });
    }

    //------------------------------------------ RETURN ----------------------------------------------------------------------------
    if (!isSelect) {
        return (
            <tr>
                <td>
                    {exam?.id}
                </td>
                <td>
                    {batch?.batchId}
                </td>
                <td>
                    {module?.moduleName}
                </td>
                <td>
                    {exam?.type}
                </td>
                <td>
                    {moment(exam.startDateTime).format('MMMM Do YYYY, h:mm a')}
                </td>
                <td>
                    {moment(exam.endDateTime).format('MMMM Do YYYY, h:mm a')}
                </td>
                <td>
                    {exam?.maxMarks}
                </td>
                <td>
                    {isQuizAttempt && !hasAttempted && (
                        moment(currentTime).isAfter(moment(exam.endDateTime)) ?
                            <MDBBadge className="ms-0" color="warning">
                                EXPIRED
                            </MDBBadge>
                            : <button className="btn btn-primary"
                                disabled={moment(currentTime).isBefore(moment(exam.startDateTime)) || moment(currentTime).isAfter(moment(exam.endDateTime))}
                                onClick={() => navigate('/giveexam', { state: { exam: exam, student: student } })}>
                                Attempt
                            </button>
                    )}
                    {isQuizAttempt && hasAttempted && (
                        <StudentExamDetails exam={exam} student={student} hasAttempted={hasAttempted} module={module} batch={batch} />
                    )}
                    {isCreateExam && (
                        <MDBBadge style={styles.spanButton} className="ms-0" color="danger" onClick={deleteExam}>
                            Delete
                        </MDBBadge>
                    )}
                    {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
                </td>
            </tr>
        )
    } {
        return (
            <>
                Exam Id : {exam.id} | BATCH : {batch.batchId} | MODULE : {module?.moduleName} | EXAM TYPE : {exam.type} | START : {moment(exam.startDateTime).format('dddd, h:mm')} | END : {moment(exam.endDateTime).format('dddd, h:mm')} | MAX MARKS : {exam.maxMarks}
            </>
        )
    }

}
export default CreatedExamRow