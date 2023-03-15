import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, { useEffect, useState } from 'react';
import axios from "axios";
import { toast } from "react-toastify";
import { QUIZ_SERVICE_URL, MANAGEMENT_SERVICE_URL } from "../../config";

import {
    MDBTable,
    MDBTableHead,
    MDBTableBody,
    MDBBadge
} from "mdb-react-ui-kit";
import TopicRow from './topicRow';

const styles = {
    spanButton: {
        cursor: "pointer",
    },
};
const AddTopic = () => {
    const [show, setShow] = useState(false);
    const [selectModule, setSelectModule] = useState("");
    const [modules, setModules] = useState([]);
    const [topicName, setTopicName] = useState("")
    const [topics, setTopics] = useState([])

    const handleClose = () => {
        setShow(false);
        setSelectModule("")
        setTopicName("")
        setTopics([])
    }
    const handleShow = () => setShow(true);

    const handleModuleChange = (event) => {
        setSelectModule(event.target.value);
        loadTopics(event.target.value)
    };
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
        loadModules()
    }, [])

    const submitAddTopic = () => {
        if (selectModule == -1) {
            toast.warning("Select a module");
        } else if (topicName == "") {
            toast.warning("Enter topic name");
        } else {
            const body = {
                topicName: topicName,
                moduleId: selectModule
            }
            const url = `${QUIZ_SERVICE_URL}/quiz/topic/add`
            axios.post(url, body).then((response) => {
                const result = response.data
                if (result["status"] == "success") {
                    toast.success(result["data"]);
                } else {
                    toast.error(result['error'])
                }
            })
        }
    }

    const getSelectedModuleName = (selectedModuleId) => {
        let name;
        modules.map(m => {
            if (m.id == selectedModuleId) {
                name = m.moduleName;
                return;
            }
        })
        return name;
    }
    return (
        <span>
            <MDBBadge color="dark" style={styles.spanButton} className="ms-2" onClick={handleShow}>Add a topic</MDBBadge>{" "}
            {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add a Topic</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <select
                        className="select col-4"
                        value={selectModule}
                        onChange={handleModuleChange}
                    >
                        <option key={-1} value={-1}>
                            Select Module
                        </option>
                        {modules.map((module) => (
                            <option key={module.id} value={module.id} >
                                {module.moduleName}
                            </option>
                        ))}
                    </select>
                    <br /> <br />
                    <input type="text" className="form-control" placeholder='Enter Topic name' onChange={(event) => {
                        setTopicName(event.target.value)
                    }} />
                    <hr />
                    <br />
                    <div>
                        <h5>
                            <MDBBadge className="ms-0">Existing topics: {getSelectedModuleName(selectModule)} </MDBBadge>
                        </h5>
                        <MDBTable>
                            <MDBTableHead dark>
                                <tr>
                                    <th scope="col">Module</th>
                                    <th scope="col">Topic</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </MDBTableHead>
                            <MDBTableBody>
                                {topics.map((topic) => (
                                    <TopicRow topic={topic} loadTopics={loadTopics} />
                                ))}
                            </MDBTableBody>
                        </MDBTable>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="success" onClick={submitAddTopic}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>

        </span>
    );
}
export default AddTopic