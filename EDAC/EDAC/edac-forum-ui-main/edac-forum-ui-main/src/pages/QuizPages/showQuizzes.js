import axios from "axios";
import moment from "moment";
import ExamAnalysis from "../../components/QuizComps/examsAnalysis";
import ModuleWiseAnalysis from "../../components/QuizComps/moduleWiseAnalysis";
import TopicWiseAnalysis from "../../components/QuizComps/topicWiseAnalysis";
import {
    MDBTable,
    MDBTableHead,
    MDBTableBody,
    MDBBadge,
    MDBBtn,
} from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL } from "../../config";
import CreatedExamRow from "../../components/QuizComps/createdExamRow";
const styles = {
    titleDiv: {
        fontSize: "1.3em",
        fontWeight: "bold",
    },
    bodyDiv: {
        marginTop: "30px",
        marginBottom: "30px",
    },
    spanButton: {
        cursor: "pointer",
    },
};

const ShowQuizzes = () => {
    //------------------------------------------ USE STATES ----------------------------------------------------------------------------
    const [exams, setExams] = useState([]);
    const [student, setStudent] = useState({
        id: 1,
        firstName: "Vaibhav",
        lastName: "Chavan",
        batchId: 2
    })
    const [currentTime, setCurrentTime] = useState(new Date());
    const [selectModule, setSelectModule] = useState(null);
    const [attemptedModules, setAttemptedModules] = useState([]);

    //------------------------------------------ HANDLE CHANGE ----------------------------------------------------------------------------
    const handleModuleChange = (event) => {
        setSelectModule(event.target.value)
    }
    //------------------------------------------ USE EFFECTS ----------------------------------------------------------------------------

    const loadExams = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/exam/get/batch/${student.batchId}`;
        axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                setExams(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        });
    };

    useEffect(() => {
        const loadAttemptedModules = async () => {
            const url = `${QUIZ_SERVICE_URL}/quiz/studentExamAnswer/attemptedModules/student/${student.id}`
            const response = await axios.get(url)
            const result = response.data;
            if (result["status"] == "success") {
                setAttemptedModules(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        }
        loadAttemptedModules()
    }, [])

    useEffect(() => {
        loadExams()
    }, [])


    useEffect(() => {
        return () => clearInterval(timer);
    }, [currentTime]);

    const timer = setInterval(() => {
        // console.log("hii")
        setCurrentTime(new Date());
    }, 1000);

    // console.log(currentTime)
    //------------------------------------------ RETURN ----------------------------------------------------------------------------

    return (
        <div style={{ margin: "" }}>
            <div className="header">
                {/* <h3>Welcome {student.firstName}</h3> */}
                <h4>
                    <MDBBadge color="dark" className="ms-0">
                        Date: {currentTime.toDateString()}
                    </MDBBadge>{" "}
                    <br />
                    <MDBBadge color="dark" className="ms-0">
                        Date: {currentTime.toDateString()}
                        Time: {currentTime.toLocaleTimeString()}
                    </MDBBadge>{" "}
                </h4>
                <hr />
            </div>
            <div >
                <div>
                    <MDBTable>
                        <MDBTableHead dark>
                            <tr>
                                <th scope="col">#Exam Id</th>
                                <th scope="col">Batch</th>
                                <th scope="col">Module</th>
                                <th scope="col">Type</th>
                                <th scope="col">Start Date-Time</th>
                                <th scope="col">End Date-Time</th>
                                <th scope="col">Maximum marks</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </MDBTableHead>
                        <MDBTableBody>
                            {exams.map((exam) => {
                                return <CreatedExamRow exam={exam} loadExams={loadExams} isSelect={false} isQuizAttempt={true} isCreateExam={false} student={student} />;
                            })}
                        </MDBTableBody>
                    </MDBTable>
                </div>
                <div>
                    <hr />
                    <div className=" row">
                        <div style={{ margin: "20px", height: "400px" }} className="col-8 p-3  myshadow" >
                            <h2>Exams Wise Analysis</h2>
                            <ExamAnalysis student={student} />
                        </div>
                        <div style={{ margin: "30px", height: "400px" }} className="col-8 p-3 myshadow" >
                            <h2>Module Wise Analysis</h2>
                            <ModuleWiseAnalysis student={student} />
                        </div>
                    </div>
                    <div>
                        <h2>Topic Wise Analysis</h2>
                        <select
                            className="select col-4 "
                            value={selectModule}
                            onChange={handleModuleChange}
                        >
                            <option key={-1} value={null}>Select Module</option>
                            {attemptedModules.map((attemptedModule) => (
                                <option key={attemptedModule.id} value={attemptedModule.id}>
                                    {attemptedModule.moduleName}
                                </option>
                            ))}
                        </select>
                        <div style={{ margin: "30px", height: "400px", width: "100%" }} >
                            {selectModule ? <TopicWiseAnalysis student={student} moduleId={selectModule} /> : 'Select a Module'}
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )

}
export default ShowQuizzes