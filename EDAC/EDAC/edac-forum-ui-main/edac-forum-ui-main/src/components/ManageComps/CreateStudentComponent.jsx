import React, { useEffect, useState } from 'react'
import axios from 'axios';

import { STUDENT_URL, CREATE_STUDENT, GET_ALL_COURSES, MANAGEMENT_URL, GET_ALL_BATCHES_BY_COURSE, AUTH_SERVICE, CREATE_USER } from '../../config'
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function CreateStudentComponent() {

  //------------------------------- STATES -----------------------------------
  const [studentObj, setStudentObj] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    batchId: null,
    courseId: null,
    photoUrl: "",
    aadharNo: "",
    overallMarks: 0,
    dob: "",
    contactDetails: {
      phoneNo: "",
      country: "INDIA",
      state: "MAHARASHTRA",
      city: "",
      pincode: "",
    },

  });

  const tempStudentObjectForReset = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    batchId: "",
    courseId: "",
    photoUrl: "",
    aadharNo: "",
    overallMarks: 0,
    dob: "",
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

  const [studentSelectedCourse, setStudentSelectedCourse] = useState({ selectedCourse: 'invalid' });
  const [studentSelectedBatch, setStudentSelectedBatch] = useState({ selectedBatch: 0 });
  const [coursesList, setCourses] = useState([{ id: 0, courseName: "None" }]);
  const [batchesList, setBatches] = useState([{ id: 0, batchId: "None" }]);

  // ------------------------------------- USE EFFECT -------------------------------------

  useEffect(() => {
    getAllCourses();
  }, [])

  useEffect(() => {
    getAllCourses();
    getAllBatchesByCourses();
  }, [messageState])

  useEffect(() => {
    getAllBatchesByCourses();
  }, [studentSelectedCourse])


  // ---------------------------------- GET ALL coursesList -------------------------------

  function getAllCourses() {
    axios.get(MANAGEMENT_URL + GET_ALL_COURSES, {
    })
      .then(function (response) {
        debugger;
        var success = response.data;
        if (success.status == "success") {
          let tempCoursesList = [...coursesList];
          tempCoursesList.push(...success.data);
          setCourses(tempCoursesList);
        }
      })
      .catch(function (response) {
        debugger;
        var error = response.response.data.data;
        if (error.errorType == "Server Error") {
          setMessageState({ message: "Something Went Wrong", type: "error" });
          setCourses([{ id: 0, courseName: "None" }]);
          window.scrollTo(0, 0);
        }
        else {
          var error = response.response.data.data;
          if (error.errorType == "Client Error") {
            setMessageState({ message: error, type: "error" });
            setCourses([{ id: 0, courseName: "None" }]);
            window.scrollTo(0, 0);
          }
        }
      });
  }

  function getAllBatchesByCourses() {
    axios.get(MANAGEMENT_URL + GET_ALL_BATCHES_BY_COURSE + studentSelectedCourse.selectedCourse, {
    })
      .then(function (response) {
        debugger;
        var success = response.data;
        if (success.status == "success") {
          let tempBatches = [{ id: 0, batchId: "None" }];
          tempBatches.push(...success.data);
          setBatches(tempBatches);
        }
      })
      .catch(function (response) {
        setBatches([{ id: 0, batchId: "None" }]);
      });
  }

  //----------------------------------- CONSTANT HTML FIELDS ----------------------------

  const personalDetailsFields = [
    { label: "First Name", value: studentObj.firstName, name: "firstName" },
    { label: "Last Name", value: studentObj.lastName, name: "lastName" },
    { label: "Email", type: "email", value: studentObj.email, name: "email" },
    { label: "Password", value: studentObj.password, name: "password" },
    { label: "Confirm Password", value: studentObj.confirmPassword, name: "confirmPassword" },
    { label: "Photo", value: studentObj.photoUrl, name: "photoUrl" },
    { label: "Aadhar Number", value: studentObj.aadharNo, name: "aadharNo" },
    { label: "Date Of Birth", type: "date", value: studentObj.dob, name: "dob" },
    { label: "Overall Marks", value: studentObj.overallMarks, name: "overallMarks" },
  ]

  const contactDetailsFields = [
    { label: "Phone Number", value: studentObj.contactDetails.phoneNo, name: "phoneNo" },
    { label: "Country", value: studentObj.contactDetails.country, name: "country" },
    { label: "State", value: studentObj.contactDetails.state, name: "state" },
    { label: "City", value: studentObj.contactDetails.city, name: "city" },
    { label: "Pin Code", value: studentObj.contactDetails.pincode, name: "pincode" },
  ]

  const courseDetailsFields =
    { label: "Course", value: studentObj.contactDetails.course, name: "course" }
  //{ label: "Batch", value : studentObj.contactDetails.batch, name : "batch"},

  const batchDetailsFields =
    { label: "Batch", value: studentObj.contactDetails.batch, name: "batch" };

  // ------------------------------------ HANDLE CHANGE FUNCTION ---------------------------

  const HandleChangeStudentDetails = (event) => {
    studentObj[event.target.name] = event.target.value;
    setStudentObj({ ...studentObj });
  }

  const HandleChangeContactDetails = (event) => {
    studentObj.contactDetails[event.target.name] = event.target.value;
    setStudentObj({ ...studentObj });
  }

  const HandleChangeStudentCourseDetails = (event) => {
    setStudentSelectedCourse({ "selectedCourse": event.target.value });
  }

  const HandleChangeStudentBatchDetails = (event) => {
    setStudentSelectedBatch({ "selectedBatch": event.target.value });
  }

  // ------------------------------------- CREATE ADMIN FUNCTION --------------------------

  const addStudent = () => {

    var userObj = {
      "email": studentObj.email,
      "encPassword": studentObj.password,
      "authorities": "ROLE_STUDENT",
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



    studentObj["courseId"] = studentSelectedCourse.selectedCourse;
    studentObj["batchId"] = studentSelectedBatch.selectedBatch;
    setStudentObj({ ...studentObj });

    axios.post(STUDENT_URL + CREATE_STUDENT, {
      ...studentObj
    })
      .then(function (response) {
        debugger;
        var success = response.data;
        if (success.status == "success") {
          setMessageState({ message: success.data, type: "success" });
          setStudentObj(tempStudentObjectForReset);
          setBatches([{ id: 0, batchId: "None" }]);
          setCourses([{ id: 0, courseName: "None" }]);
          window.scrollTo(0, 0);
        }
      })
      .catch(function (response) {
        debugger;
        var error = response.response.data.data;
        if (error.errorType == "Server Error") {
          debugger;
          setMessageState({ message: "Something Went Wrong", type: "error" });
          setStudentObj(tempStudentObjectForReset);
          setBatches([{ id: 0, batchId: "None" }]);
          setCourses([{ id: 0, courseName: "None" }]);
          window.scrollTo(0, 0);
        }
        else {
          var error = response.response.data.data;
          if (error.errorType == "Client Error") {
            setMessageState({ message: error, type: "error" });
            setStudentObj(tempStudentObjectForReset);
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
        <PersonalDetails personalDetailsFields={personalDetailsFields} HandleChangeStudentDetails={HandleChangeStudentDetails} />
      </div>


      <h4>Course Details</h4>

      <hr />
      <div className="form-row">
        <CourseDetails courseDetailsFields={courseDetailsFields} HandleChangeStudentCourseDetails={HandleChangeStudentCourseDetails} coursesList={coursesList} studentSelectedCourse={studentSelectedCourse} />
        <BatchDetails batchDetailsFields={batchDetailsFields} HandleChangeStudentBatchDetails={HandleChangeStudentBatchDetails} batchesList={batchesList} studentSelectedBatch={studentSelectedBatch} />
      </div>


      <h4>Contact Details</h4>
      <hr />
      <div className="form-row">
        <ContactDetails contactDetailsFields={contactDetailsFields} HandleChangeContactDetails={HandleChangeContactDetails} />
      </div>
      <button type="submit" className="btn btn-primary btn-block" onClick={addStudent}>Add Student</button>
    </>
  );
}


// ------------------------------- COMPONENTS ----------------------------------------

const PersonalDetails = ({ personalDetailsFields, HandleChangeStudentDetails }) => {
  return (
    personalDetailsFields.map(field => {
      return (
        <div key={field.name} className="form-group col-md-6">
          <label htmlFor={field.label + "-input"}>{field.label}</label>
          <input type={field.type} id={field.label + "-input"} name={field.name} className="form-control" value={field.value} onChange={HandleChangeStudentDetails} />
        </div>
      )
    })
  )
}

const CourseDetails = ({ courseDetailsFields, HandleChangeStudentCourseDetails, coursesList, studentSelectedCourse }) => {
  return (
    <>
      <div key={courseDetailsFields.name} className="form-group col-md-6">
        <label htmlFor="coursesList">Choose a Course : </label>
        <select className="form-control" name={courseDetailsFields.name} id={courseDetailsFields.label + "-input"} onChange={HandleChangeStudentCourseDetails} value={studentSelectedCourse.selectedCourse}>
          {
            coursesList.map(item => {
              return (
                <option value={item.id}>{item.courseName}</option>
              )
            })
          }

        </select>
      </div>
    </>
  )
}

const BatchDetails = ({ batchDetailsFields, HandleChangeStudentBatchDetails, batchesList, studentSelectedBatch }) => {
  return (
    <>
      <div key={batchDetailsFields.id} className="form-group col-md-6">
        <label htmlFor="coursesList">Choose a Batch : </label>
        <select className="form-control" name={batchDetailsFields.name} id={batchDetailsFields.label + "-input"} onChange={HandleChangeStudentBatchDetails} value={studentSelectedBatch.BatchDetails}>
          {
            batchesList.map(item => {
              return (
                <option value={item.id}>{item.batchId}</option>
              )
            })
          }

        </select>
      </div>
    </>
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

export default CreateStudentComponent;