import React, { useEffect, useState } from 'react'
import axios from 'axios';

import { MANAGEMENT_URL, EDIT_TEACHER, GET_TEACHER_DETAILS } from '../../config'
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function EditTeacherComponent(props) {

  //------------------------------- STATES -----------------------------------
  const [teacherObj, setTeacherObj] = useState({
    firstName: "",
    lastName: "",
    email: "",
    experience: 0,
    joinDate: "",
    role: "TEACHER",
    salary: 0,
    password: "",
    contactDetails: {
      phoneNo: "",
      country: "INDIA",
      state: "MAHARASHTRA",
      city: "",
      pincode: "",
    }
  });

  const [messageState, setMessageState] = useState({
    message: "",
    type: ""
  });

  //----------------------------------- CONSTANT HTML FIELDS ----------------------------

  const personalDetailsFields = [
    { label: "First Name", value: teacherObj.firstName, name: "firstName" },
    { label: "Last Name", value: teacherObj.lastName, name: "lastName" },
    { label: "Email", type: "email", value: teacherObj.email, name: "email" },
    { label: "Password", value: teacherObj.password, name: "password" },
    { label: "Confirm Password", value: teacherObj.confirmPassword, name: "confirmPassword" },
  ]

  const contactDetailsFields = [
    { label: "Phone Number", value: teacherObj.contactDetails.phoneNo, name: "phoneNo" },
    { label: "Country", value: teacherObj.contactDetails.country, name: "country" },
    { label: "State", value: teacherObj.contactDetails.state, name: "state" },
    { label: "City", value: teacherObj.contactDetails.city, name: "city" },
    { label: "Pin Code", value: teacherObj.contactDetails.pincode, name: "pincode" },
  ]

  const careerDetailsFields = [
    { label: "Experience", value: teacherObj.experience, name: "experience" },
    { label: "Join Date", type: "date", value: teacherObj.joinDate, name: "joinDate" },
    { label: "Salary", type: "number", value: teacherObj.salary, name: "salary" },
  ]

  // ------------------------------------ HANDLE CHANGE FUNCTION ---------------------------

  const HandleChangeAdminDetails = (event) => {
    teacherObj[event.target.name] = event.target.value;
    setTeacherObj({ ...teacherObj });
  }

  const HandleChangeContactDetails = (event) => {
    teacherObj.contactDetails[event.target.name] = event.target.value;
    setTeacherObj({ ...teacherObj });
  }

  // ------------------------------------ GET TEACHER DETAILS ----------------------------------

  const getTeacherDetails = () => {
    axios.get(MANAGEMENT_URL + GET_TEACHER_DETAILS + props.id, {
    })
      .then(function (response) {
        debugger;
        let adminData = response.data.data;
        setTeacherObj(adminData);
      })
      .catch(function (response) {
        var error = response.response.data.data;
        if (error.errorType == "Server Error") {

          setMessageState({ message: "Something Went Wrong", type: "error" });
          window.scrollTo(0, 0);
        }
        else {
          var error = response.response.data.data;
          if (error.errorType == "Client Error") {
            setMessageState({ message: error, type: "error" });
            window.scrollTo(0, 0);
          }
        }
      });
  }

  // ------------------------------------- CREATE TEACHER FUNCTION --------------------------

  const editTeacher = () => {
    axios.put(MANAGEMENT_URL + EDIT_TEACHER + props.id, {
      ...teacherObj
    })
      .then(function (response) {
        var success = response.data;
        if (success.status == "success") {
          setMessageState({ message: success.data, type: "success" });
          window.scrollTo(0, 0)
        }
      })
      .catch(function (response) {
        var error = response.response.data.data;
        if (error.errorType == "Server Error") {
          debugger;
          setMessageState({ message: "Something Went Wrong", type: "error" });
          window.scrollTo(0, 0);
        }
        else {
          var error = response.response.data.data;
          if (error.errorType == "Client Error") {
            setMessageState({ message: error, type: "error" });
            window.scrollTo(0, 0);
          }
        }
      });
  }

  // ----------------------------------- POP UP ERROR MESSAGE ----------------------------
  function checkMessage() {
    if (messageState.message != "") {
      toast[messageState.type](messageState.message);
      setMessageState({ message: "" });
    }
  }

  // ------------------------------------ USE EFFECT FUNCTIONS ----------------------------

  useEffect(() => {
    getTeacherDetails();
  }, []);

  // ------------------------------------- RETURN JSX --------------------------------------

  return (
    <>
      {checkMessage()}

      <h4>Personal Details</h4>
      <hr />
      <div className="form-row">
        <PersonalDetails personalDetailsFields={personalDetailsFields} HandleChangeAdminDetails={HandleChangeAdminDetails} />
      </div>

      <h4>Contact Details</h4>
      <hr />
      <div className="form-row">
        <ContactDetails contactDetailsFields={contactDetailsFields} HandleChangeContactDetails={HandleChangeContactDetails} />
      </div>


      <h4>Career Details</h4>
      <hr />
      <div className="form-row">
        <CareerDetails careerDetailsFields={careerDetailsFields} HandleChangeAdminDetails={HandleChangeAdminDetails} />
      </div>

      <button type="submit" className="btn btn-primary btn-block" onClick={editTeacher}>Edit Teacher</button>
    </>
  );
}


// ------------------------------- COMPONENTS ----------------------------------------

const PersonalDetails = ({ personalDetailsFields, HandleChangeAdminDetails }) => {
  console.log(personalDetailsFields)
  return (
    personalDetailsFields.map(field => {
      return (
        <div key={field.name} className="form-group col-md-6">
          <label htmlFor={field.label + "-input"}>{field.label}</label>
          <input type={field.type} id={field.label + "-input"} name={field.name} className="form-control" value={field.value} onChange={HandleChangeAdminDetails} />
        </div>
      )
    })
  )
}

const CareerDetails = ({ careerDetailsFields, HandleChangeAdminDetails }) => {
  console.log(careerDetailsFields.join_date);
  return (
    careerDetailsFields.map(field => {
      return (
        <div key={field.name} className="form-group col-md-6">
          <label htmlFor={field.label + "-input"}>{field.label}</label>
          <input type={field.type} id={field.label + "-input"} name={field.name} className="form-control" value={field.value} onChange={HandleChangeAdminDetails} />
        </div>
      )
    })
  )
}

const ContactDetails = ({ contactDetailsFields, HandleChangeContactDetails }) => {
  return (
    contactDetailsFields.map(field => {
      return (
        <div key={field.name} className="form-group col-md-6">
          <label htmlFor={field.label + "-input"}>{field.label}</label>
          <input type={field.type} id={field.label + "-input"} name={field.name} className="form-control" value={field.value} onChange={HandleChangeContactDetails} />
        </div>
      )
    })
  )
}

export default EditTeacherComponent;