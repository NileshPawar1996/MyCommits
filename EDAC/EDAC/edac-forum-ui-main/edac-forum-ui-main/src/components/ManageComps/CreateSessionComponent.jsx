import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { MANAGEMENT_URL, GET_ALL_COURSES, GET_ALL_BATCHES_BY_COURSE, GET_ALL_TEACHERS, ADD_SESSION, GET_MODULES_BY_COURSE } from '../../config'

export default function CreateSessionComponent() {

    // ---------------------------------------- STATES --------------------------------------------

    const [sessionObj, setSessionObj] = useState({
        batch: {
            id: null
        },
        module: {
            id: null,
        },
        staff: {
            id: null,
        },
        date: "",
        startTime: "",
        endTime: ""
    });

    const resetSessionObj = {
        batch: {
            id: null
        },
        module: {
            id: null,
        },
        staff: {
            id: null,
        },
        date: "",
        startTime: "",
        endTime: ""
    };

    const [selectedBatches, setSelectedBatches] = useState({
        id: "",
        batchId: ""
    });

    const [selectedCourses, setSelectedCourse] = useState({
        id: "invalid",
        courseName: ""
    });

    const [selectedTeacher, setSelectedTeacher] = useState({
        id: "",
        teacherName: ""
    });

    const [selectedModule, setSelectedModule] = useState({
        id: "",
        moduleName: ""
    });

    const [coursesList, setCoursesList] = useState([{ id: "", courseName: "None" }]);
    const [batchesList, setBatchesList] = useState([{ id: "", batchId: "None" }]);
    const [moduleList, setModuleList] = useState([{ id: "", moduleName: "None" }]);
    const [teacherList, setTeacherList] = useState([{ id: "", firstName: "None", lastName: "" }]);
    const [messageState, setMessageState] = useState({ message: "", type: "" });

    // ------------------------------------- USE EFFECT FUNCTIONS --------------------------------

    useEffect(() => {
        getAllCourses();
        getAllTeachers();
    }, []);

    useEffect(() => {
        getAllBatchesByCourse();
        getModules();
    }, [selectedCourses]);

    useEffect(() => {
        getAllCourses();
        // getAllBatchesByCourses();
    }, [messageState])

    // ------------------------------------ GET MODULES USING COURSES -------------------------

    function getModules() {
        if (selectedCourses.id != "invalid")
            axios.get(MANAGEMENT_URL + GET_MODULES_BY_COURSE + selectedCourses.id, {
            })
                .then(function (response) {

                    var tempModuleList = [{ id: "invalid", moduleName: "None" }];
                    tempModuleList.push(...response.data.data);
                    setModuleList(tempModuleList);
                })
                .catch(function (response) {

                    setMessageState({ message: "Something Went Wrong", type: "error" });
                });
    }

    // ------------------------------------- GET ALL TEACHERS ----------------------------------

    function getAllTeachers() {
        axios.get(MANAGEMENT_URL + GET_ALL_TEACHERS, {
        })
            .then(function (response) {
                var tempTeachersList = [{ id: "", firstName: "None", lastName: "  " }];
                tempTeachersList.push(...response.data.data);
                setTeacherList(tempTeachersList);
            })
            .catch(function (response) {

                setMessageState({ message: "Something Went Wrong", type: "error" });
            });
    }

    // -------------------------------------- GET ALL COURSES -----------------------------------

    function getAllCourses() {
        axios.get(MANAGEMENT_URL + GET_ALL_COURSES, {
        })
            .then(function (response) {
                var tempCourseList = [{ id: "", courseName: "None" }];
                tempCourseList.push(...response.data.data);
                setCoursesList(tempCourseList);
            })
            .catch(function (response) {

                setMessageState({ message: "Something Went Wrong", type: "error" });
            });
    }

    // -------------------------------------- GET ALL BATCHES BY COURSES -----------------------

    function getAllBatchesByCourse() {
        if (selectedCourses.id != "invalid") {
            axios.get(MANAGEMENT_URL + GET_ALL_BATCHES_BY_COURSE + selectedCourses.id, {
            })
                .then(function (response) {

                    var batchesList = [{ id: "", batchId: "None" }];
                    batchesList.push(...response.data.data);
                    setBatchesList(batchesList);
                })
                .catch(function (response) {

                    setMessageState({ message: "Something Went Wrong", type: "error" });
                });
        }
    }

    // -------------------------------------- SUBMIT SESSION ------------------------------------

    function submitSession() {
        debugger;
        sessionObj.batch.id = selectedBatches.id;
        sessionObj.module.id = selectedModule.id;
        sessionObj.staff.id = selectedTeacher.id;
        // sessionObj.cou
        axios.post(MANAGEMENT_URL + ADD_SESSION, {
            ...sessionObj
        })
            .then(function (response) {
                setSessionObj(resetSessionObj);
                setMessageState({ message: response.data.data, type: "success" });
            })
            .catch(function (response) {
                setMessageState({ message: "Something Went Wrong", type: "error" });
            });
    }

    // -------------------------------------- ERROR MESSAGE FUNCTION -----------------------------

    function checkMessage() {
        if (messageState.message != "") {
            window.scrollTo(0, 0);
            toast[messageState.type](messageState.message);
            setMessageState({ message: "" });
        }
    }

    // --------------------------------- HANDLE CHANGE FUNCTIONS -----------------------------------------
    function HandleCourse(event) {
        setSelectedCourse({
            id: event.target.value,
        });
    }

    function HandleBatch(event) {
        setSelectedBatches({
            id: event.target.value,
        });
    }

    function HandleTeacher(event) {
        setSelectedTeacher({
            id: event.target.value,
        });
    }

    function HandleModule(event) {
        debugger;
        setSelectedModule({
            id: event.target.value,
        });
    }

    function HandleFields(event) {
        debugger;
        var sessionObjTemp = { ...sessionObj }
        sessionObjTemp[event.target.name] = event.target.value;
        setSessionObj(sessionObjTemp);
    }

    return (
        <>
            {checkMessage()}

            <div className="form-row">
                <label htmlFor="coursesList">Select Course : </label>
                <select className="form-control" id="coursesList" value={selectedCourses.courseName} onChange={HandleCourse}>
                    {
                        coursesList.map(item => {
                            return (
                                <option value={item.id}>{item.courseName} </option>
                            )
                        })
                    }
                </select>

                <label htmlFor="batchesList">Select Batch : </label>
                <select className="form-control" id="batchesList" value={selectedBatches.batchId} onChange={HandleBatch}>
                    {
                        batchesList.map(item => {
                            return (
                                <option value={item.id}>{item.batchId} </option>
                            )
                        })
                    }
                </select>

                <label htmlFor="moduleList">Select Module : </label>
                <select className="form-control" id="moduleList" value={selectedModule.id} onChange={HandleModule}>
                    {
                        moduleList.map(item => {
                            return (
                                <option value={item.id}>{item.moduleName} </option>
                            )
                        })
                    }
                </select>

                <label htmlFor="teachersList">Select Teacher : </label>
                <select className="form-control" id="teachersList" value={selectedTeacher.teacherName} onChange={HandleTeacher}>
                    {
                        teacherList.map(item => {
                            return (
                                <option value={item.id}>{item.firstName} {item.lastName} </option>
                            )
                        })
                    }
                </select>
            </div>
            <br />
            <hr />
            <div className="form-row">

                <label htmlFor="date">Select Date : </label>
                <input type="date" className="form-control" id="date" name="date" value={sessionObj.date} onChange={HandleFields} />

                <label htmlFor="start_time">Select Start Time : </label>
                <input type="time" className="form-control" id="start_time" name="startTime" value={sessionObj.startTime} onChange={HandleFields} />

                <label htmlFor="end_time">Select End Time : </label>
                <input type="time" className="form-control" id="end_time" name="endTime" value={sessionObj.endTime} onChange={HandleFields} />

            </div>
            <br></br>
            <input type='submit' value="Add Session" onClick={submitSession} className="btn btn-primary btn-block" />
        </>
    )
}
