import React, { useEffect, useState } from 'react'
import axios from 'axios';

import { MANAGEMENT_URL, CREATE_ADMIN, AUTH_SERVICE, CREATE_USER } from '../../config'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function CreateAdminComponent() {

  const date = new Date();
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  if (month < 10)
    month = '0' + month;
  if (day < 10)
    day = '0' + day;
  console.log(`${year}-${month}-${day}`);

  //------------------------------- STATES -----------------------------------
  const [adminObj, setAdminObj] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    experience: 0,
    joinDate: `${year}-${month}-${day}`,
    role: "ADMIN",
    salary: 0,
    contactDetails: {
      phoneNo: "",
      country: "INDIA",
      state: "MAHARASHTRA",
      city: "",
      pincode: "",
    }
  });

  const tempAdminObjectForReset = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    experience: 0,
    joinDate: `${year}-${month}-${day}`,
    role: "ADMIN",
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

  // ------------------------------------- CREATE ADMIN FUNCTION --------------------------

  function addUserInUserService() {
    debugger;
    var userObj = {
      "email": adminObj.email,
      "encPassword": adminObj.password,
      "authorities": "ROLE_ADMIN",
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
  }

  const addAdmin = () => {

    var userObj = {
      "email": adminObj.email,
      "encPassword": adminObj.password,
      "authorities": "ROLE_ADMIN",
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


    // addUserInUserService();
    axios.post(MANAGEMENT_URL + CREATE_ADMIN, {
      ...adminObj
    })
      .then(function (response) {

        var success = response.data;
        if (success.status == "success") {
          setMessageState({ message: success.data, type: "success" });
          setAdminObj(tempAdminObjectForReset);
          window.scrollTo(0, 0)
        }
      })
      .catch(function (response) {
        var error = response.response.data.data;
        if (error.errorType == "Server Error") {
          debugger;
          setMessageState({ message: "Something Went Wrong", type: "error" });
          setAdminObj(tempAdminObjectForReset);
          window.scrollTo(0, 0);
        }
        else {
          var error = response.response.data.data;
          if (error.errorType == "Client Error") {
            setMessageState({ message: error, type: "error" });
            setAdminObj(tempAdminObjectForReset);
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

      <button type="submit" className="btn btn-primary btn-block" onClick={addAdmin}>Add Admin</button>
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

export default CreateAdminComponent;