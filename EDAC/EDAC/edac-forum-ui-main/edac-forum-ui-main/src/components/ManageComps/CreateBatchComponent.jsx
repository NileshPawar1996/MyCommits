import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { CREATE_BATCH, CREATE_COURSE, GET_ALL_COURSES, MANAGEMENT_URL } from '../../config';

export default function CreateBatchComponent() {

    // --------------------------------------- USE STATE ----------------------------------------------

    const [course, setCourse] = useState({ id: "", courseName: "" });
    const [batch, setBatch] = useState({ batchId: "", maxStrength: "", course: { id: "" } });
    const [messageState, setMessageState] = useState({ message: "", type: "" });
    const [courseList, setCourseList] = useState([{ id: 'Invalid', courseName: "None" }]);
    const [selectedCourse, setSelectedCourse] = useState({ selectedCourse: "None" });

    var resetbatch = { batchId: "", maxStrength: "", course: { id: "" } };

    // ---------------------------------------- USE EFFECT -------------------------------------------

    useEffect(() => {
        getCourses();
    }, []);

    // --------------------------------------- HANDLE CHANGE ------------------------------------------

    function HandleFields(event) {
        var tempBatch = { ...batch };
        tempBatch[event.target.name] = event.target.value;
        setBatch(tempBatch);
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

    // -------------------------------------- ADD batch FUNCTION --------------------------------------
    function addBatch() {
        batch.course = selectedCourse;
        axios.post(MANAGEMENT_URL + CREATE_BATCH, {
            ...batch
        })
            .then(function (response) {
                debugger;
                setBatch(resetbatch);
                setSelectedCourse({ selectedCourse: "None" });
                setMessageState({ message: "batch Added Successfully", type: "success" });

            })
            .catch(function (response) {
                debugger;
                setBatch(resetbatch);
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
                <label htmlFor="batchId">Enter batchId (6 digit - SEP2022) : </label>
                <input type="input" maxLength={7} minLength={7} className="form-control" id="batchId" name="batchId" value={batch.batchId} onChange={HandleFields} />

                <label htmlFor="batchName">Enter Max Strength : </label>
                <input type="number" className="form-control" id="maxStrength" name="maxStrength" value={batch.maxStrength} onChange={HandleFields} />

            </div>
            <br />
            <input type="submit" value="Add batch" className='btn btn-primary btn-block' onClick={addBatch}></input>
        </>
    )
}
