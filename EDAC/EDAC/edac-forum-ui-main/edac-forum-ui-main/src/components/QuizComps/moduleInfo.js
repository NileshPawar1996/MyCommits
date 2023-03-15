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
const ModuleInfo = (props) => {
    const { module } = props
    const [show, setShow] = useState(false);
    const handleClose = () => {
        setShow(false);
    }
    const handleShow = () => setShow(true);

    return (
        <span>
            <MDBBadge color="success" style={styles.spanButton} className="ms-2" onClick={handleShow}>{module.moduleName}</MDBBadge>{" "}
            {/* colors: Primary Secondary Success Danger Warning Info Light Dark */}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Question Description</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        # Module Id: {module.id}
                        <hr />
                    </div>
                    <div>
                        Module Name : {module.moduleName}
                        <hr />
                    </div>
                    <div>
                        Course Name : {module.course. courseName}
                        <hr />
                    </div>

                    <div>
                        Duration : {module.durationHrs}
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
export default ModuleInfo