import { useState } from "react";
import { Modal, Button } from "react-bootstrap";


export default function CustomModal({ btnText, modalAttr, clearState, handleSubmit, submitBtnText }) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return <>
    <Button className={modalAttr.btnClass} onClick={handleShow} onHide={clearState}>
      {btnText}
    </Button>

    <Modal show={show} onHide={handleClose}>
      <Modal.Header>
        <Modal.Title>{modalAttr.title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {modalAttr.body}
      </Modal.Body>
      <Modal.Footer>
        <Button variant="outline-secondary" onClick={() => {
          clearState();
          handleClose();
        }}>
          Cancel
        </Button>
        <Button variant="primary" onClick={() => {
          handleSubmit();
          handleClose();
        }}>
          {submitBtnText || "Submit"}
        </Button>
      </Modal.Footer>
    </Modal>
  </>
}