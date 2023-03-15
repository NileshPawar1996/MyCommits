import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { MANAGEMENT_URL_FRONTEND } from '../config';

export default function HomeController() {
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

  return  <>
        <nav className="navbar navbar-expand-lg">
            <ul>
                <li className="active">
                    <Link to={MANAGEMENT_URL_FRONTEND}>Home</Link>
                </li>
                <li className="active">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/login"}>Login</Link>
                </li>
                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/course/"}>Manage Course</Link>
                </li>
                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/batch"}>Manage Batch</Link>
                </li>
                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/student"}>Manage Student</Link>
                </li>
                {/* <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/student/edit">Edit Student</Link>
                </li> */}
                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/teacher"}>Manage Teacher</Link>
                </li>
                {/* <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/teacher/edit">Edit Teacher</Link>
                </li> */}
                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/admin"}>Manage Admin</Link>
                </li>
                {/* <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/admin/edit">Edit Admin</Link>
                </li> */}
                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/session"}>Manage Session</Link>
                </li>

                <li className="nav-item">
                    <Link to={MANAGEMENT_URL_FRONTEND+"/module"}>Manage Modules</Link>
                </li>
            </ul>
        </nav>

        <Button variant="primary" onClick={handleShow}>
            Launch demo modal
        </Button>

        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
            <Modal.Title>Modal heading</Modal.Title>
            </Modal.Header>
            <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
            <Modal.Footer>

                <Button variant="secondary" onClick={handleClose}>
                    Close
                </Button>
                <Button variant="primary" onClick={handleClose}>
                    Save Changes
                </Button>
            </Modal.Footer>
        </Modal>
      
    </>
}
