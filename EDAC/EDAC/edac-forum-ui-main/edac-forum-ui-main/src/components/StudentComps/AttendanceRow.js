import {
    MDBBadge,
    MDBTable,
    MDBTableHead,
    MDBTableBody,
} from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import { getSessionBySessionId } from "../../config";
import axios from "axios";

export default function AttendanceRow(prop) {
    const [session, setSession] = useState();

    const loadSession = () => {
        debugger;
        const url = `${getSessionBySessionId}/${prop.att.sessionId}`;
        axios.get(url).then((response) => {
            debugger;
            console.log(response.data.data);
            setSession(response.data.data);
        });
    };

    useEffect(() => {
        // debugger
        loadSession();
    }, []);

    var status = (prop.att.duration > 100) ? "Present" : "Absent";

    return (
        <tr>
            <td>
                <div class="d-flex align-items-center">
                    <p class="fw-bold mb-1">{session?.date}</p>
                </div>
            </td>
            <td>
                <p class="fw-normal mb-1">{session?.module.moduleName}</p>
            </td>
            <td>
                <p class="fw-normal mb-1">{prop.att.duration}</p>
            </td>
            <td>
                <p class="fw-normal mb-1">{status}</p>
            </td>
        </tr>
    )
}



