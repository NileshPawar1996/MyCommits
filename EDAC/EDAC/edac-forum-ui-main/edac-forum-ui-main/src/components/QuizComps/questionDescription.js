import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, { useState } from 'react'
import {
    MDBBadge
} from "mdb-react-ui-kit";
const styles = {
    spanButton: {
        cursor: "pointer",
    },
};
const QuestionDescription = (props) => {
    const { question, loadExamsQuestions } = props
    const [show, setShow] = useState(false);
    const handleClose = () => {
        setShow(false);
    }
    const handleShow = () => setShow(true);

    return (
        <span>
            <MDBBadge color="dark" style={styles.spanButton} className="ms-2" onClick={handleShow}>More Details</MDBBadge>{" "}
            {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Question Description</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        # Question Id: {question.id}
                        <hr />
                    </div>
                    <div>
                        Question : {question.question}
                        <hr />
                    </div>
                    <div>
                        Option A : {question.optionA}
                        <hr />
                    </div>
                    <div>
                        Option B : {question.optionB}
                        <hr />
                    </div>
                    <div>
                        Option c : {question.optionC}
                        <hr />
                    </div>
                    <div>
                        Option D : {question.optionD}
                        <hr />
                    </div>
                    <div>
                        Correct Answer : {question.answer}
                        <hr />
                    </div>
                    <div>
                        Answer Description: {question.answerDesc}
                        <hr />
                    </div>
                    <br />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </span>
    )
}
export default QuestionDescription