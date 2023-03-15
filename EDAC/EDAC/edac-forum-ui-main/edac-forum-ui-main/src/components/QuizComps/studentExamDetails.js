import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, { useEffect, useState } from 'react';
import {
    MDBBadge
} from "mdb-react-ui-kit";
import moment from 'moment/moment';
import { QUIZ_SERVICE_URL } from '../../config.js';
import { toast } from 'react-toastify';
import axios from 'axios';
import { useNavigate } from 'react-router';
const styles = {
    spanButton: {
        cursor: "pointer",
    },
};
const StudentExamDetails = (props) => {
    const { exam, student, hasAttempted, module, batch } = props
    const [show, setShow] = useState(false);
    const [score, setScore] = useState();
    const navigate = useNavigate()
    const handleClose = () => {
        setShow(false);
    }
    const handleShow = () => setShow(true);

    const loadScore = () => {
        if (student != null && exam != null) {
            // debugger
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
    }

    const handleQuestionDetails = () => {
        navigate('/examResults', { state: { exam: exam, student: student } })
    }
    useEffect(() => {
        loadScore()
    }, [])
    return (
        <span className='App'>
            <MDBBadge color="success" style={styles.spanButton} className="ms-0" onClick={handleShow}>Exam Details</MDBBadge>{" "}
            {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Question Description</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        # Exam Id: {exam?.id}
                        <hr />
                    </div>
                    <div>
                        Student Name : {student?.firstName}
                        <hr />
                    </div>
                    <div>
                        Batch Name : {batch?.batchId}
                        <hr />
                    </div>
                    <div>
                        Module Name : {module?.moduleName}
                        <hr />
                    </div>

                    <div>
                        Type : {exam?.type}
                        <hr />
                    </div>
                    <div>
                        Start Date-Time : {moment(exam?.startDateTime).format('MMMM Do YYYY, h:mm:ss a')}
                        <hr />
                    </div>
                    <div>
                        End Date-Time : {moment(exam?.endDateTime).format('MMMM Do YYYY, h:mm:ss a')}
                        <hr />
                    </div>
                    <div>
                        Total Correct : {score?.correctAnswers}
                        <hr />
                    </div>
                    <div>
                        Total Questions : {score?.totalQuestions}
                        <hr />
                    </div>
                    <div>
                        Attempt Status : {hasAttempted ? "Attempted" : "Not Attempted"}
                        <hr />
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>

                    <Button variant="secondary" onClick={handleQuestionDetails}>
                        See Question Details
                    </Button>

                </Modal.Footer>
            </Modal>
        </span>
    )
}
export default StudentExamDetails