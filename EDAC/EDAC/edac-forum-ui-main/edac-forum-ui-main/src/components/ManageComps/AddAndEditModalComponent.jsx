import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import CreateAdminComponent from './CreateAdminComponent';

export default function AddAndEditModalComponent(props) {
    const [show, setShow] = useState(props.show);
    const handleClose = () => {
        debugger;
        setShow(false);
        props.handleClose();
        if (props["refresh"])
            props.refresh();
    };
    const handleShow = () => setShow(true);

    debugger;
    return (
        <div className="d-grid">
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>{props.value}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <props.component id={props.id} />
                </Modal.Body>
                {/* <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Close
                </Button>
                <Button variant="primary" onClick={() => { props.component.addAdmin(); handleClose();}}>
                    Save Changes
                </Button>
            </Modal.Footer> */}

            </Modal>
            {props.value != "Edit" ? <button className="btn btn-primary btn-md btn-block btn-outline-primary" type="button" onClick={() => { handleShow(); }}>{props.value}</button> : null}
        </div>
    )
}
