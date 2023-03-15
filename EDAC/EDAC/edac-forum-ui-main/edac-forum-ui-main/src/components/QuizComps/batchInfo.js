import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import React, { useState } from 'react';
import {
    MDBBadge
} from "mdb-react-ui-kit";
const styles = {
    spanButton: {
        cursor: "pointer",
    },
};
const BatchInfo = (props) => {
    const { batch } = props
    const [show, setShow] = useState(false);
    const handleClose = () => {
        setShow(false);
    }
    const handleShow = () => setShow(true);

    return (
        <span>
            <MDBBadge color="success" style={styles.spanButton} className="ms-2" onClick={handleShow}>{batch.batchId}</MDBBadge>{" "}
            {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Question Description</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        # Batch Id: {batch.id}
                        <hr />
                    </div>
                    <div>
                        Batch Name : {batch.batchId}
                        <hr />
                    </div>
                    <div>
                        Course Name : {batch.course.courseName}
                        <hr />
                    </div>

                    <div>
                        Maximum Strength : {batch.maxStrength}
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
export default BatchInfo