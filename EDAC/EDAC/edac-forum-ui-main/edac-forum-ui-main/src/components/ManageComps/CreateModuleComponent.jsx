import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { CREATE_BATCH, CREATE_COURSE, CREATE_MODULE, GET_ALL_COURSES, MANAGEMENT_URL } from '../../config';

export default function CreateModuleComponent() {

    // --------------------------------------- USE STATE ----------------------------------------------

    const [course, setCourse] = useState({ id: "", courseName: "" });
    const [module, setModule] = useState({ moduleName: "", durationHrs: "", course: { id: "" } });
    const [messageState, setMessageState] = useState({ message: "", type: "" });
    const [courseList, setCourseList] = useState([{ id: 'Invalid', courseName: "None" }]);
    const [selectedCourse, setSelectedCourse] = useState({ selectedCourse: "None" });

    var resetModule = { moduleName: "", durationHrs: "", course: { id: "" } };

    // ---------------------------------------- USE EFFECT -------------------------------------------

    useEffect(() => {
        getCourses();
    }, []);

    // --------------------------------------- HANDLE CHANGE ------------------------------------------

    function HandleFields(event) {
        var tempModule = { ...module };
        tempModule[event.target.name] = event.target.value;
        setModule(tempModule);
    }

    function HandleCourse(event) {
        setSelectedCourse({ id: event.target.value });
    }

    function checkMessage() {
        if (messageState.message != "") {
            window.scrollTo(0, 0);
            toast[messageState.type](messageState.message);
            setMessageState({ message: "" });
        }
    }

    // ------------------------------------- GET ALL COURSES ----------------------------------------

    function getCourses() {
        axios.get(MANAGEMENT_URL + GET_ALL_COURSES, {
        })
            .then(function (response) {
                debugger;
                let data = response.data.data;
                var tempCourseList = [];
                tempCourseList.push(...courseList);
                tempCourseList.push(...data);
                setCourseList(tempCourseList);
            })
            .catch(function (response) {
                debugger;
            });
    };

    // -------------------------------------- ADD module FUNCTION --------------------------------------
    function addModule() {
        debugger
        module.course = selectedCourse;
        axios.post(MANAGEMENT_URL + CREATE_MODULE, {
            ...module
        })
            .then(function (response) {
                debugger;
                setModule(resetModule);
                setSelectedCourse({ selectedCourse: "None" });
                setMessageState({ message: "Module Added Successfully", type: "success" });

            })
            .catch(function (response) {
                debugger;
                setModule(resetModule);
                setSelectedCourse({ selectedCourse: "None" });
                setMessageState({ message: "Something Went Wrong", type: "error" });
            })
    }


    return (
        <>
            {checkMessage()}

            <label htmlFor="coursesList">Choose a Course : </label>
            <select className="form-control" name={selectedCourse.selectedCourse} id={selectedCourse.selectedCourse + "-input"} onChange={HandleCourse} value={selectedCourse.selectedCourse}>
                {
                    courseList.map(item => {
                        return (
                            <option value={item.id}>{item.courseName}</option>
                        )
                    })
                }
            </select>


            <div className="form-row">
                <label htmlFor="moduleName">Enter Module Name : </label>
                <input type="input" className="form-control" id="moduleName" name="moduleName" value={module.moduleName} onChange={HandleFields} />

                <label htmlFor="duration">Enter Duration In Hours : </label>
                <input type="number" className="form-control" id="durationHrs" name="durationHrs" value={module.durationHrs} onChange={HandleFields} />

            </div>
            <br />
            <input type="submit" value="Add module" className='btn btn-primary btn-block' onClick={addModule}></input>
        </>
    )
}
