import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { CREATE_COURSE, MANAGEMENT_URL } from '../../config';

export default function CreateCourseComponent() {

    // --------------------------------------- USE EFFECT ----------------------------------------------

    const [course, setCourse] = useState({ id: "", courseName: "" });
    const [messageState, setMessageState] = useState({ message: "", type: "" });

    var resetCourse = { id: "", courseName: "" };

    // --------------------------------------- HANDLE CHANGE ------------------------------------------

    function HandleFields(event) {
        var tempCourse = { ...course };
        tempCourse[event.target.name] = event.target.value;
        setCourse(tempCourse);
    }

    function checkMessage() {
        if (messageState.message != "") {
            window.scrollTo(0, 0);
            toast[messageState.type](messageState.message);
            setMessageState({ message: "" });
        }
    }

    // -------------------------------------- ADD COURSE FUNCTION --------------------------------------
    function addCourse() {

        axios.post(MANAGEMENT_URL + CREATE_COURSE, {
            ...course
        })
            .then(function (response) {
                setMessageState({ message: "Course Added Successfully", type: "success" });
                setCourse(resetCourse);
            })
            .catch(function (response) {
                setMessageState({ message: "Something Went Wrong", type: "error" });
                setCourse(resetCourse);
            })
    }


    return (
        <>
            {checkMessage()}

            <div className="form-row">
                <label htmlFor="id">Enter Course Id (Upto 10 digit - DAC) : </label>
                <input type="text" className="form-control" id="id" name="id" value={course.id} onChange={HandleFields} required={true} maxLength={10} />

                <label htmlFor="courseName">Enter Course Name : </label>
                <input type="text" className="form-control" id="courseName" name="courseName" value={course.courseName} onChange={HandleFields} />

            </div>
            <br />
            <input type="submit" value="Add Course" className='btn btn-primary btn-block' onClick={addCourse}></input>
        </>
    )
}
