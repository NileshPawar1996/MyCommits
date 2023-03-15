
import axios from "axios";
import { useEffect, useState } from "react";

import { getAllModules, getAttendanceBySessionIdStudentId, getAttendanceByStudentId, getSessionByModuleId } from "../../config";
import {
  MDBBadge,
  MDBTable,
  MDBTableHead,
  MDBTableBody,
} from "mdb-react-ui-kit";
import AttendanceRow from "../../components/StudentComps/AttendanceRow";

const AttendanceTest = () => {


  //-----------------------useState---------------------------

  const [allAttendance, setAllAttendance] = useState([]);
  const [studentId, setStudentId] = useState();
  //-----------------------useEffect--------------------------

  const loadStudentId = () => {

    setStudentId(sessionStorage.getItem("curr_user"));
  }

  const loadAllAttendance = () => {
    debugger
    const url = `${getAttendanceByStudentId}/${sessionStorage.getItem("curr_user")}`;
    axios.get(url).then((response) => {
      debugger
      console.log(response.data.data);
      setAllAttendance(response.data.data);
    })

  }

  useEffect(() => {
    loadStudentId();
    loadAllAttendance();
  }, []);

  return (

    <div>

      <table class="table align-middle mb-0 bg-white">
        <thead class="bg-light">
          <tr>
            <th>Date</th>
            <th>SessionId</th>
            <th>Duration</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {
            allAttendance.map((att) => {

              return <AttendanceRow key={att.attendanceId} att={att} />
            })
          }
        </tbody>
      </table>

    </div>

  )

}
export default AttendanceTest;
