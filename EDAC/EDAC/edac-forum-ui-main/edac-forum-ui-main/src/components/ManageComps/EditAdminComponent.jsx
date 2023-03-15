import React, { useEffect, useState } from 'react'
import axios from 'axios';

import { MANAGEMENT_URL, EDIT_ADMIN, GET_ADMIN_DETAILS } from '../../config'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function EditAdminComponent(props) {

  //------------------------------- STATES -----------------------------------
  const [adminObj, setAdminObj] = useState({
    firstName: "",
    lastName: "",
    email: "",
    experience: 0,
    joinDate: "",
    role: "ADMIN",
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
    { label: "First Name", value: adminObj.firstName, name: "firstName" },
    { label: "Last Name", value: adminObj.lastName, name: "lastName" },
    { label: "Email", type: "email", value: adminObj.email, name: "email" },
    { label: "Password", value: adminObj.password, name: "password" },
    { label: "Confirm Password", value: adminObj.confirmPassword, name: "confirmPassword" },
  ]

  const contactDetailsFields = [
    { label: "Phone Number", value: adminObj.contactDetails.phoneNo, name: "phoneNo" },
    { label: "Country", value: adminObj.contactDetails.country, name: "country" },
    { label: "State", value: adminObj.contactDetails.state, name: "state" },
    { label: "City", value: adminObj.contactDetails.city, name: "city" },
    { label: "Pin Code", value: adminObj.contactDetails.pincode, name: "pincode" },
  ]

  const careerDetailsFields = [
    { label: "Experience", value: adminObj.experience, name: "experience" },
    { label: "Join Date", type: "date", value: adminObj.joinDate, name: "joinDate" },
    { label: "Salary", type: "number", value: adminObj.salary, name: "salary" },
  ]

  // ------------------------------------ HANDLE CHANGE FUNCTION ---------------------------

  const HandleChangeAdminDetails = (event) => {
    adminObj[event.target.name] = event.target.value;
    setAdminObj({ ...adminObj });
  }

  const HandleChangeContactDetails = (event) => {
    adminObj.contactDetails[event.target.name] = event.target.value;
    setAdminObj({ ...adminObj });
  }

  // ------------------------------------ GET ADMIN DETAILS ----------------------------------

  const getAdminDetails = () => {
    axios.get(MANAGEMENT_URL + GET_ADMIN_DETAILS + props.id, {
    })
      .then(function (response) {
        debugger;
        let adminData = response.data.data;
        setAdminObj(adminData);
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

  // ------------------------------------- CREATE ADMIN FUNCTION --------------------------

  const editAdmin = () => {
    axios.put(MANAGEMENT_URL + EDIT_ADMIN + props.id, {
      ...adminObj
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

  function checkMessage() {
    if (messageState.message != "") {
      toast[messageState.type](messageState.message);
      setMessageState({ message: "" });
    }
  }

  // ------------------------------------ USE EFFECT FUNCTIONS ----------------------------

  useEffect(() => {
    getAdminDetails();
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

      <button type="submit" className="btn btn-primary btn-block" onClick={editAdmin}>Add Admin</button>
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

export default EditAdminComponent;