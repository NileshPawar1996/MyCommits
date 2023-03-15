import axios from "axios";
import {
    MDBBadge,
    MDBTable,
    MDBTableHead,
    MDBTableBody,
} from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL } from "../../config";
import { MANAGEMENT_SERVICE_URL } from "../../config";
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

const CreateExam = () => {
    //------------------------------------------ USE STATES ----------------------------------------------------------------------------

    const [maxMarks, setMaxMarks] = useState(0);
    const [selectStartTime, setSelectStartTime] = useState("");
    const [selectEndTime, setSelectEndTime] = useState("");
    const [selectBatch, setSelectBatch] = useState("");
    const [selectModule, setSelectModule] = useState("");
    const [selectCourse, setSelectCourse] = useState("");
    const [selectExamType, setSelectExamType] = useState("");
    const [modules, setModules] = useState([]);
    const [examTypes, setExamTypes] = useState([]);
    const [batches, setBatches] = useState([]);
    const [exams, setExams] = useState([]);
    const [courses, setCourses] = useState([]);

    //------------------------------------------ STATIC DATA ----------------------------------------------------------------------------

    const batchesArray = [
        { label: "PG-DAC", id: "1" },
        { label: "PG-DMC", id: "2" },
        { label: "DBDA", id: "3" },
    ];
    const modulesArray = [
        { label: "CORE-JAVA", id: "1" },
        { label: "ADV-JAVA", id: "2" },
        { label: "WPT", id: "3" },
    ];
    const examTypesArray = [
        { label: "LAB" },
        { label: "THEORY" },
        { label: "INTERNAL" },
    ];
    //------------------------------------------ HANDLE CHANGE FUNCTIONS ----------------------------------------------------------------------------
    const handleCourseChange = (event) => {
        setSelectCourse(event.target.value);
        const loadBatches = async () => {
            const url = `${MANAGEMENT_SERVICE_URL}/batch/course/${event.target.value}`;
            const response = await axios.get(url)
            const result = response.data;
            if (result["status"] == "success") {
                setBatches(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        };
        loadBatches();
        const loadModules = async () => {
            const url = `${MANAGEMENT_SERVICE_URL}/module/course/${event.target.value}`;
            const response = await axios.get(url)
            const result = response.data;
            if (result["status"] == "success") {
                setModules(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        };
        loadModules();
    };
    const handleModuleChange = (event) => {
        setSelectModule(event.target.value);
    };
    const handleBatchChange = (event) => {
        setSelectBatch(event.target.value);
    };
    const handleExamTypeChange = (event) => {
        setSelectExamType(event.target.value);
    };
    const handleStartTimeChange = (event) => {
        setSelectStartTime(event.target.value);
    };
    const handleEndTimeChange = (event) => {
        setSelectEndTime(event.target.value);
    };
    const handleMaxMarksChange = (event) => {
        setMaxMarks(event.target.value);
    };

    //------------------------------------------ SUBMIT CREATE EXAM ----------------------------------------------------------------------------
    const submitCreateExam = () => {
        if (maxMarks == 0) {
            toast.warning("Maximum marks cannot be 0");
        } else if (selectExamType == "") {
            toast.warning("Select Exam Type");
        } else if (selectModule == "") {
            toast.warning("Select Module");
        } else if (selectBatch == "") {
            toast.warning("Select Batch");
        } else if (selectStartTime == "") {
            toast.warning("Select start Date and Time");
        } else if (selectEndTime == "") {
            toast.warning("Select end Date and Time")
        } else {
            const body = {
                batchId: selectBatch,
                moduleId: selectModule,
                type: selectExamType,
                startDateTime: selectStartTime,
                endDateTime: selectEndTime,
                maxMarks: maxMarks,
            };
            const url = `${QUIZ_SERVICE_URL}/quiz/exam/add`;
            axios.post(url, body).then((response) => {
                const result = response.data;
                if (result["status"] == "success") {
                    toast.success(result["data"]);
                    loadExams()
                    setMaxMarks(0)
                    setSelectBatch("")
                    setSelectEndTime("")
                    setSelectStartTime("")
                    setSelectModule("")
                    setSelectExamType("")
                } else {
                    toast.error(result["error"]);
                }
            });
        }

    };
    //------------------------------------------ USE EFFECTS ----------------------------------------------------------------------------
    useEffect(() => {
        const loadCourses = async () => {
            const url = `${MANAGEMENT_SERVICE_URL}/course/all`
            const response = await axios.get(url)
            const result = response.data
            if (result['status'] == 'success') {
                setCourses(result['data'])
            } else {
                toast.error(result["error"])
            }
        }
        loadCourses()
    }, [])
    const loadExamTypes = () => {
        setExamTypes(examTypesArray);
    };
    const loadExams = () => {
        const url = `${QUIZ_SERVICE_URL}/quiz/exam/get`;
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

        loadExamTypes();
        loadExams();
    }, []);
    //------------------------------------------ RETURN ----------------------------------------------------------------------------
    return (
        <div>
            <div style={styles.titleDiv}>
                <h1 className="title">Create Exam</h1>
            </div>
            <div className="form-group">
                <MDBTable align="middle">
                    <MDBTableHead>
                        <tr>
                            <th scope="col">Action</th>
                            <th scope="col">Select</th>
                        </tr>
                    </MDBTableHead>
                    <MDBTableBody>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Select Course</MDBBadge>
                            </td>
                            <td>
                                <select
                                    className="select col-4"
                                    value={selectCourse}
                                    onChange={handleCourseChange}
                                >
                                    <option key={-1} value={-1}>
                                        Select Course
                                    </option>
                                    {courses.map((course) => (
                                        <option key={course.id} value={course.id}>
                                            {course.courseName}
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Select Batch</MDBBadge>
                            </td>
                            <td>
                                <select
                                    className="select col-4"
                                    value={selectBatch}
                                    onChange={handleBatchChange}
                                >
                                    <option key={-1} value={-1}>
                                        Select Batch
                                    </option>
                                    {batches.map((batch) => (
                                        <option key={batch.id} value={batch.id}>
                                            {batch.batchId}
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Select Module</MDBBadge>
                            </td>
                            <td>
                                <select className="select col-4" value={selectModule} onChange={handleModuleChange}>
                                    <option key={-1} value={-1}>
                                        Select Module
                                    </option>
                                    {modules.map((module) => (
                                        <option key={module.id} value={module.id}>
                                            {module.moduleName}
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Select Exam Type</MDBBadge>
                            </td>
                            <td>
                                <select
                                    className="select col-4"
                                    value={selectExamType}
                                    onChange={handleExamTypeChange}
                                >
                                    <option key={-1} value={-1}>
                                        Select Exam Type
                                    </option>
                                    {examTypes.map((examType) => (
                                        <option key={examType.id} value={examType.value}>
                                            {examType.label}
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Select Start Time</MDBBadge>
                            </td>
                            <td>
                                <input
                                    type="datetime-local"
                                    className="col-4"
                                    onChange={handleStartTimeChange}
                                    value={selectStartTime}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Select End Time</MDBBadge>
                            </td>
                            <td>
                                <input
                                    type="datetime-local"
                                    className="col-4"
                                    onChange={handleEndTimeChange}
                                    value={selectEndTime}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <MDBBadge className="ms-2">Maximum Marks</MDBBadge>
                            </td>
                            <td>
                                <input
                                    type="number"
                                    className="col-4"
                                    placeholder="Enter maximum marks"
                                    onChange={handleMaxMarksChange}
                                    value={maxMarks}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2} style={{ textAlign: "center" }}>
                                <button className="btn btn-success" onClick={submitCreateExam}>
                                    Save
                                </button>
                            </td>
                        </tr>
                    </MDBTableBody>
                </MDBTable>
                <br />
                <br />
                <div style={styles.titleDiv}>
                    <h3>
                        <MDBBadge className="ms-0" color="dark">
                            Created Exams
                        </MDBBadge>
                        {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
                    </h3>
                </div>
                <MDBTable>
                    <MDBTableHead dark>
                        <tr>
                            <th scope="col">#Exam Id</th>
                            <th scope="col">Batch</th>
                            <th scope="col">Module</th>
                            <th scope="col">Type</th>
                            <th scope="col">Start Date</th>
                            <th scope="col">End Date</th>
                            <th scope="col">Maximum marks</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </MDBTableHead>
                    <MDBTableBody>
                        {exams.map((exam) => {
                            return <CreatedExamRow exam={exam} loadExams={loadExams} isSelect={false} isCreateExam={true} isQuizAttempt={false} />;
                        })}
                    </MDBTableBody>
                </MDBTable>
            </div>
        </div >
    );
};
export default CreateExam;
