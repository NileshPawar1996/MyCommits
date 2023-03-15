import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { getAllBatches, getAllBatchesByCourseId, getAllCourses, getAllModules, getAllModulesByCourseId, getAllSessionByBatchIdModuleId, uploadAttendanceFile } from '../../config';
import { toast } from "react-toastify";
const InsertAttendence = () => {


    //-------------------------------------------States-------------------------------



    const [file, setFile] = useState(null);
    const [batch, setBatch] = useState();
    const [course, setCourse] = useState("");
    const [module, setModule] = useState();
    const [session, setSession] = useState();
    const [allBatches, setAllBatches] = useState([]);
    const [allCourses, setAllCources] = useState([]);
    const [allModules, setAllModules] = useState([]);
    const [allSessions, setAllSessions] = useState([]);




    // ---------------------------------------handle changes------------------------


    const handleChange = (event) => {
        debugger
        setFile(event.target.value);
        console.log(file);
    };

    const body = {
        file: file
    }

    const handleSelectCourse = (event) => {

        setCourse(event.target.value);
    }

    const handleSelectBatch = (event) => {

        setBatch(event.target.value);
        console.log(event.target);
        debugger
    }
    const handleSelectModule = (event) => {

        setModule(event.target.value);
    }
    const handleSelectSession = (event) => {

        setSession(event.target.value);
    }


    //--------------------------------------Axios calls ---------------------------------




    const loadAllCourses = () => {
        // debugger
        toast.warning("Loading All the courses");
        const url = `${getAllCourses}`;
        axios.get(url).then((response) => {
            console.log(response.data.data);
            setAllCources(response.data.data);
        });

    }

    const loadAllBatchesByCourse = () => {
        // debugger
        console.log(course);
        const url = `${getAllBatchesByCourseId}${course}`;
        axios.get(url).then((response) => {
            setAllBatches(response.data.data);
        });
    }

    const loadAllModulesByCourse = () => {
        // debugger
        const url = `${getAllModulesByCourseId}${course}`;
        axios.get(url).then((response) => {
            setAllModules(response.data.data);
        });
    }

    const loadAllSessionsByBatchAndModule = () => {
        // debugger
        const url = `${getAllSessionByBatchIdModuleId}${batch}/${module}`;
        axios.get(url).then((response) => {
            setAllSessions(response.data.data);
        });
    }

    const uploadFile = () => {
        debugger
        // const formData = new FormData();
        // formData.append('file', file);


        const url = `${uploadAttendanceFile}/${session}`;
        axios.post(url, body).then((response) => {
            const result = response.data;
            if (result.status == "success") {
                alert("added successfully");
                toast.success(result["data"]);
            } else {
                alert("adding failed");
                toast.error(result["status"]);
            }
        })
    }


    //-----------------------------------styles---------------------------


    //---------------------------------UseEffects---------------------------


    useEffect(() => {

        loadAllCourses();
        // loadAllBatchesByCourse();
    }, []);

    useEffect(() => {
        loadAllBatchesByCourse();
        loadAllModulesByCourse();
    }, [course]);


    useEffect(() => {
        loadAllSessionsByBatchAndModule();
    }, [module]);


    //---------------------------------Return---------------------------


    return (

        <div className='container'>


            <table class="table table-hover">
                <tbody>
                    <tr>
                        <td>
                            <h2>Upload Attendance</h2>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select defaultValue="select Course" className="form-select  bg-primary-subtle " onChange={handleSelectCourse} aria-label="Default select example">
                                <option defaultValue>Select Course</option>
                                {
                                    allCourses?.map((ck) => {
                                        return <option value={ck.id}>{ck.id}</option>
                                    })
                                }
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select className="form-select bg-danger-subtle" onChange={handleSelectBatch} aria-label="Default select example">
                                <option defaultValue>Select Batch</option>
                                {
                                    allBatches?.map((ck) => {
                                        return <option value={ck.id}>{ck.batchId}</option>
                                    })
                                }
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select className="form-select bg-warning-subtle" onChange={handleSelectModule} aria-label="Default select example">
                                <option defaultValue>Select Module</option>
                                {
                                    allModules?.map((ck) => {
                                        return <option value={ck.id}>{ck.moduleName}</option>
                                    })
                                }
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select className="form-select bg-danger-subtle" onChange={handleSelectSession} aria-label="Default select example">
                                <option defaultValue>Select Session</option>
                                {
                                    allSessions?.map((ck) => {
                                        return <option value={ck.id}>{ck.date}{" : "}{ck.startTime}{" -> "}{ck.endTime}  </option>
                                    })
                                }
                            </select>
                        </td>
                    </tr>

                </tbody>
            </table>

            <div className="container-sm " padi>Please Download the Attendance file from the Meet plugin and upload here </div>

            <form className="input-group mb-3" enctype="multipart/form-data">
                <input accept=".csv" type="file" className="form-control" id="inputGroupFile02" onChange={handleChange} />
                <label className="input-group-text" onClick={uploadFile}>Upload</label>
            </form>

        </div>
    )

}
export default InsertAttendence;