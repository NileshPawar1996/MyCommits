import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, { useState } from 'react';
import {
    MDBBadge
} from "mdb-react-ui-kit";
import moment from 'moment/moment';
const styles = {
    spanButton: {
        cursor: "pointer",
    },
};
const ExamInfo = (props) => {
    const { exam, module, batch } = props
    const [show, setShow] = useState(false);
    const handleClose = () => {
        setShow(false);
    }
    const handleShow = () => setShow(true);

    return (
        <span>
            <MDBBadge color="success" style={styles.spanButton} className="ms-2" onClick={handleShow}>Selected Exam</MDBBadge>{" "}
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
                        Maximum marks : {exam?.maxMarks}
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
export default ExamInfo