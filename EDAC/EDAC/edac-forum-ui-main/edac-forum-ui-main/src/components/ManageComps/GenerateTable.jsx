import axios from 'axios';
import React, { useEffect, useState } from 'react'
import AddAndEditModalComponent from './AddAndEditModalComponent';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CreateTeacherComponent from './CreateTeacherComponent';

var key = 0;

export default function GenerateTable(props) {
    const add = props.AddComponent;

    // --------------------------------------- USE STATES -------------------------------------------
    const [tableHeading, setTableHeading] = useState([]);
    const [rows, setRows] = useState([]);
    const [editFlag, setEditFlag] = useState(false);
    const [editId, setEditId] = useState();
    const [deleteId, setDeleteId] = useState();
    // [key, setKey] = useState(0);
    var idToDelete = 0;
    // const [refreshData, setRefreshData] = useState(false);


    const [messageState, setMessageState] = useState({
        message: "",
        type: ""
    });

    useEffect(() => {
        getData();
    }, []);

    const refresh = () => {
        getData();
    }


    function handleEdit(idT) {

        setEditId(idT);
        setEditFlag(true);
        getData();
    }
    function confirmDeletion() {
        return window.confirm("Confirm Deletion");
    }

    function handleDelete(id) {
        debugger
        if (!confirmDeletion())
            return;
        idToDelete = id;
        axios.delete(props.deleteUrl + idToDelete, {
        })
            .then(function (response) {
                var success = response.data;
                setMessageState({ message: success.data, type: "success" });
                window.scrollTo(0, 0)
                getData();
            })
            .catch(function (response) {
                debugger
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
            })
    }

    function setEditFlagToFalse() {
        setEditFlag(false);
    }
    var role = sessionStorage.getItem("role");
    function getData() {
        axios.get(props.getUrl, {
        })
            .then(function (response) {
                debugger;
                var data = response.data.data;
                var keys = Object.keys(data[0]);

                keys.push("Edit");
                if (props.deleteUrl != null)
                    keys.push("Delete");
                setTableHeading(keys);
                var tempArr = [];
                async function iterate() {
                    data.map((oneRow) => {
                    
                        oneRow['Edit'] = <button className='btn btn-primary btn-block' onClick={() => handleEdit(oneRow['id'])}>Edit</button>;
                        if (props.deleteUrl != null)
                            oneRow['Delete'] = <button className='btn btn-danger btn-block' onClick={() => handleDelete(oneRow['id'])}>Delete</button>;
                        if (oneRow['course'])
                            oneRow['course'] = oneRow['course'].id;
                        tempArr.push(oneRow);
                    });
                }

                iterate();
                console.log(tempArr);
                setRows(tempArr);
            })
            .catch(function (response) {
                debugger;
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
                    };
                }
            });
    }

    function checkMessage() {
        if (messageState.message != "") {
            toast[messageState.type](messageState.message);
            setMessageState({ message: "" });
        }
    }

    return (
        <div>
            {checkMessage()}
            {editFlag ? <AddAndEditModalComponent show={true} value={"Edit"} handleClose={setEditFlagToFalse} id={editId} component={props.editComponent} refresh={refresh} /> : null}
            {/* <AddAndEditModalComponent value={"Edit"} id={editId} component={props.addComponent} refresh={refresh} /> */}
            <table className='table table-bordered'>
                <thead className="thead-dark">
                    <tr>
                        {
                            tableHeading.map((heading) => {
                                // {x!="contactDetails"?row[x]:null}4
                                {
                                    var temp = (heading != "contactDetails") ? <th scope="col">{heading}</th> : null
                                }
                                return temp;
                                // return {(heading != "contactDetails") ? <th scope="col">{heading}</th> : <></>} 
                            })
                        }
                    </tr>
                </thead>
                <tbody>
                    {
                        rows.map((row) => {
                            // debugger
                            return <tr>
                                {
                                    tableHeading.map((x) => {
                                        {
                                            var temp = (x != "contactDetails") ? <td>{row[x]}</td> : null
                                        }
                                        return temp;
                                    })
                                }

                            </tr>
                        })
                    }
                </tbody>
            </table>
            {/* <AddAndEditModalComponent value={"Add Teacher"} component={CreateTeacherComponent} /> */}
            <AddAndEditModalComponent value={"Edit"} id={editId} component={props.AddComponent} refresh={refresh} />
        </div>
    )
}
