import React, { useState } from 'react'
import axios from 'axios';

import { MANAGEMENT_URL, CREATE_TEACHER, AUTH_SERVICE, CREATE_USER } from '../../config'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function CreateTeacherComponent() {
  const date = new Date();
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  if (month < 10)
    month = '0' + month;
  if (day < 10)
    day = '0' + day;
  console.log(`${year}-${month}-${day}`);

  //---------------------------------- STATES --------------------------------------------

  const [teacherObj, setTeacherObj] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    experience: 0,
    joinDate: `${year}-${month}-${day}`,
    role: "TEACHER",
    salary: 0,
    contactDetails: {
      phoneNo: "",
      country: "INDIA",
      state: "MAHARASHTRA",
      city: "",
      pincode: "",
    }
  });

  const tempTeacherObjectForReset = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    experience: 0,
    joinDate: `${year}-${month}-${day}`,
    role: "TEACHER",
    salary: 0,
    contactDetails: {
      phoneNo: "",
      country: "INDIA",
      state: "MAHARASHTRA",
      city: "",
      pincode: "",
    }
  }


  const [messageState, setMessageState] = useState({
    message: "",
    type: ""
  });
  // const countries = useState({countries : ["INDIA", "AUSTRALIA", "US", "UK"]})
  // const states = useState({states : ["MH", "GJ", "MP"]})

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

  const HandleChangeTeacherDetails = (event) => {
    teacherObj[event.target.name] = event.target.value;
    setTeacherObj({ ...teacherObj });
  }

  const HandleChangeContactDetails = (event) => {
    teacherObj.contactDetails[event.target.name] = event.target.value;
    setTeacherObj({ ...teacherObj });
  }

  // ------------------------------------- CREATE TEACHER FUNCTION --------------------------

  const addTeacher = () => {
    var userObj = {
      "email": teacherObj.email,
      "encPassword": teacherObj.password,
      "authorities": "ROLE_TEACHER",
    }

    axios.post(AUTH_SERVICE + CREATE_USER, {
      ...userObj
    })
      .then(function (response) {
        debugger;
      })
      .catch(function (response) {
        debugger;
      });



    axios.post(MANAGEMENT_URL + CREATE_TEACHER, {
      ...teacherObj
    })
      .then(function (response) {

        var success = response.data;
        if (success.status == "success") {
          setMessageState({ message: success.data, type: "success" });
          setTeacherObj(tempTeacherObjectForReset);
          window.scrollTo(0, 0)
        }
      })
      .catch(function (response) {
        var error = response.response.data.data;
        if (error.errorType == "Server Error") {
          setMessageState({ message: "Something Went Wrong", type: "error" });
          setTeacherObj(tempTeacherObjectForReset);
          window.scrollTo(0, 0);
        }
        else {
          var error = response.response.data.data;
          if (error.errorType == "Client Error") {
            setMessageState({ message: error, type: "error" });
            setTeacherObj(tempTeacherObjectForReset);
            window.scrollTo(0, 0);
          }
        }
      });
  }

  function checkMessage() {
    if (messageState.message != "") {
      toast[messageState.type](messageState.message);
      setMessageState({ message: "" });
    }
  }

  // ------------------------------------- RETURN JSX --------------------------------------

  return (
    <>
      {checkMessage()}

      <h4>Personal Details</h4>
      <hr />
      <div className="form-row">
        <PersonalDetails personalDetailsFields={personalDetailsFields} HandleChangeTeacherDetails={HandleChangeTeacherDetails} />
      </div>


      <h4>Contact Details</h4>
      <hr />
      <div className="form-row">
        <ContactDetails contactDetailsFields={contactDetailsFields} HandleChangeContactDetails={HandleChangeContactDetails} />
      </div>


      <h4>Career Details</h4>
      <hr />
      <div className="form-row">
        <CareerDetails careerDetailsFields={careerDetailsFields} HandleChangeTeacherDetails={HandleChangeTeacherDetails} />
      </div>

      <button type="submit" className="btn btn-primary btn-block" onClick={addTeacher}>Add Teacher</button>
    </>
  );
}


// ------------------------------- COMPONENTS ----------------------------------------

const PersonalDetails = ({ personalDetailsFields, HandleChangeTeacherDetails }) => {
  console.log(personalDetailsFields)
  return (
    personalDetailsFields.map(field => {
      return (
        <div key={field.name} className="form-group col-md-6">
          <label htmlFor={field.label + "-input"}>{field.label}</label>
          <input type={field.type} id={field.label + "-input"} name={field.name} className="form-control" value={field.value} onChange={HandleChangeTeacherDetails} />
        </div>
      )
    })
  )
}

const CareerDetails = ({ careerDetailsFields, HandleChangeTeacherDetails }) => {
  console.log(careerDetailsFields.join_date);
  return (
    careerDetailsFields.map(field => {
      return (
        <div key={field.name} className="form-group col-md-6">
          <label htmlFor={field.label + "-input"}>{field.label}</label>
          <input type={field.type} id={field.label + "-input"} name={field.name} className="form-control" value={field.value} onChange={HandleChangeTeacherDetails} />
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

export default CreateTeacherComponent;