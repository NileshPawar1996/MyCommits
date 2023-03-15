import axios from "axios";
import { MDBBadge } from "mdb-react-ui-kit";
import { MDBRadio } from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL, MANAGEMENT_SERVICE_URL } from "../../config";
import AddTopic from '../../components/QuizComps/createTopic'

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
const CreateQuestion = () => {
    //------------------------------------------ USE STATES ----------------------------------------------------------------------------

    const [question, setQuestion] = useState("");
    const [optionA, setOptionA] = useState("");
    const [optionB, setOptionB] = useState("");
    const [optionC, setOptionC] = useState("");
    const [optionD, setOptionD] = useState("");
    const [answerDescription, setAnswerDescription] = useState("");
    const [selectModule, setSelectModule] = useState("-1");
    const [selectTopic, setSelectTopic] = useState("-1");
    const [radioAnswer, setRadioAnswer] = useState("");
    const [modules, setModules] = useState([]);
    const [topics, setTopics] = useState([]);
    //------------------------------------------ STATIC DATA ----------------------------------------------------------------------------

    const modulesArray = [
        { label: "CORE-JAVA", id: "1" },
        { label: "ADV-JAVA", id: "2" },
        { label: "WPT", id: "3" },
    ];
    const topicsArray = [
        { label: "JDBC", id: "1" },
        { label: "Generics", id: "2" },
        { label: "Interfaces", id: "3" },
    ];
    //------------------------------------------ HANDLE CHANGE FUNCTIONS ----------------------------------------------------------------------------
    const handleModuleChange = (event) => {
        setSelectModule(event.target.value);
        loadTopics(event.target.value)
    };
    const handleTopicChange = (event) => {
        setSelectTopic(event.target.value);
    };
    const handleRadioAnswerChange = (event) => {
        setRadioAnswer(event.target.value);
    };
    const handleUploadFile = () => {

    }
    //------------------------------------------ USE EFFECTS ----------------------------------------------------------------------------
    const loadModules = () => {
        const url = `${MANAGEMENT_SERVICE_URL}/module/all`;
        axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                setModules(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        });
    };
    const loadTopics = (moduleId) => {
        const url = `${QUIZ_SERVICE_URL}/quiz/topic/module/${moduleId}`;
        axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
                setTopics(result["data"]);
            } else {
                toast.error(result["error"]);
            }
        });
    };
    useEffect(() => {
        loadModules();
    }, []);

    //------------------------------------------ SUBMIT QUESTION ----------------------------------------------------------------------------
    const submitQuestion = () => {
        if (question.length == 0) {
            toast.warning("Questions field cannot be blank");
        } else if (optionA.length === 0) {
            toast.warning("Options field cannot be blank");
        } else if (optionB.length === 0) {
            toast.warning("Options field cannot be blank");
        } else if (optionC.length === 0) {
            toast.warning("Options field cannot be blank");
        } else if (optionD.length === 0) {
            toast.warning("Options field cannot be blank");
        } else if (selectModule == -1 || selectModule == "") {
            toast.warning("Please select the module");
        } else if (selectTopic == -1 || selectTopic == "") {
            toast.warning("Please select the topic");
        } else if (radioAnswer == "") {
            toast.warning("Please select the correct answer choice");
        } else {
            const body = {
                moduleId: selectModule,
                topic: { id: selectTopic },
                question: question,
                optionA: optionA,
                optionB: optionB,
                optionC: optionC,
                optionD: optionD,
                answer: radioAnswer,
                answerDesc: answerDescription,
            };
            console.log(body);
            const url = `${QUIZ_SERVICE_URL}/quiz/question/add`;
            axios.post(url, body).then((response) => {
                const result = response.data;
                if (result["status"] == "success") {
                    toast.success(result["data"]);
                    setQuestion("")
                    setOptionA("")
                    setOptionB("")
                    setOptionC("")
                    setOptionD("")
                    setAnswerDescription("")
                    setSelectModule("")
                    setSelectTopic("")
                    setRadioAnswer("")
                } else {
                    toast.error(result["error"]);
                }
            });
        }
    };
    //------------------------------------------ RETURN ----------------------------------------------------------------------------
    return (
        <div className="container">
            <div style={styles.titleDiv}>
                <h1 className="title">Create Question</h1>
            </div>

            <div style={styles.bodyDiv}>
                <select
                    className="select col-4"
                    value={selectModule}
                    onChange={handleModuleChange}
                >
                    <option key={-1} value={-1}>
                        Select Module
                    </option>
                    {modules.map((module) => (
                        <option key={module.id} value={module.id}>
                            {module.moduleName}
                        </option>
                    ))}
                </select>{" "}
                <select
                    className="select col-2"
                    value={selectTopic}
                    onChange={handleTopicChange}
                >
                    <option key={-1} value={-1}>
                        Select Topic
                    </option>
                    {topics.map((topic) => (
                        <option key={topic.id} value={topic.id}>
                            {topic.topicName}
                        </option>
                    ))}
                </select>
                <br />
                <br />
                <h4>
                    <MDBBadge className="ms-2">Enter Question</MDBBadge>{" "}
                    <AddTopic />
                </h4>
                <br />
                <textarea
                    textarea
                    rows="5"
                    className="form-control"
                    value={question}
                    placeholder="Enter Question"
                    onChange={(e) => {
                        setQuestion(e.target.value);
                    }}
                ></textarea>
                <br />
                <h5>
                    <MDBBadge className="ms-2">Option A</MDBBadge>
                    <br />
                    <br />
                    <textarea
                        textarea
                        value={optionA}
                        rows="1"
                        className="form-control"
                        placeholder="Enter Question"
                        onChange={(e) => {
                            setOptionA(e.target.value);
                        }}
                    ></textarea>
                    <br />
                    <MDBBadge className="ms-2">Option B</MDBBadge>
                    <br />
                    <br />
                    <textarea
                        textarea
                        value={optionB}
                        rows="1"
                        className="form-control"
                        placeholder="Enter Question"
                        onChange={(e) => {
                            setOptionB(e.target.value);
                        }}
                    ></textarea>
                    <br />
                    <MDBBadge className="ms-2">Option C</MDBBadge>
                    <br />
                    <br />
                    <textarea
                        textarea
                        value={optionC}
                        rows="1"
                        className="form-control"
                        placeholder="Enter Question"
                        onChange={(e) => {
                            setOptionC(e.target.value);
                        }}
                    ></textarea>
                    <br />
                    <MDBBadge className="ms-2">Option D</MDBBadge>
                    <br />
                    <br />
                    <textarea
                        textarea
                        value={optionD}
                        rows="1"
                        className="form-control"
                        placeholder="Enter Question"
                        onChange={(e) => {
                            setOptionD(e.target.value);
                        }}
                    ></textarea>
                </h5>
                <br />
                <h5>
                    <MDBBadge className="ms-2">Correct Option</MDBBadge>{" "}
                    <MDBRadio
                        name="correctAnswer"
                        onChange={handleRadioAnswerChange}
                        checked={radioAnswer === 'A'}
                        id="inlineRadio1"
                        value="A"
                        label="A"
                        inline
                    />
                    <MDBRadio
                        name="correctAnswer"
                        onChange={handleRadioAnswerChange}
                        checked={radioAnswer === 'B'}
                        id="inlineRadio2"
                        value="B"
                        label="B"
                        inline
                    />
                    <MDBRadio
                        name="correctAnswer"
                        onChange={handleRadioAnswerChange}
                        checked={radioAnswer === 'C'}
                        id="inlineRadio2"
                        value="C"
                        label="C"
                        inline
                    />
                    <MDBRadio
                        name="correctAnswer"
                        onChange={handleRadioAnswerChange}
                        checked={radioAnswer === 'D'}
                        id="inlineRadio2"
                        value="D"
                        label="D"
                        inline
                    />
                </h5>
                <h4>
                    <MDBBadge className="ms-2">Enter Answer Description</MDBBadge>
                </h4>
                <br />
                <textarea
                    textarea
                    value={answerDescription}
                    rows="5"
                    className="form-control"
                    placeholder="Enter Answer description"
                    onChange={(e) => {
                        setAnswerDescription(e.target.value);
                    }}
                ></textarea>
                <br />
                <button className="btn btn-success" onClick={submitQuestion}>
                    Save
                </button>
                <hr />
                <input type="file" onClick={handleUploadFile} />
            </div>
        </div>
    );
};
export default CreateQuestion;
