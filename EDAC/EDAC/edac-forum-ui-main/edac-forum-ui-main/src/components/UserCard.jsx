import axios from "axios";
import { useEffect, useState } from "react"
import { MANAGEMENT_SERVICE_URL, STUDENT_URL } from "../config";
import ROLES from "../roles";

export default function UserCard() {
  const [user, setUser] = useState();

  useEffect(() => {
    if(sessionStorage.getItem("role") == ROLES.ADMIN){
      axios.get(`${MANAGEMENT_SERVICE_URL}/admin/get/${sessionStorage.getItem("curr_user")}`)
        .then((res) => {
          setUser(res.data.data)
        })
    }
    else{
      axios.get(`${STUDENT_URL}/student/${sessionStorage.getItem("curr_user")}`)
      .then((res) => {
        debugger
        setUser(res.data.data)
      }).catch(function(response){
        debugger;
      })
    }

  }, [])





  return <div className="user-card">

    <div className="prof-pic">
      <img src="https://i.pinimg.com/474x/ac/2b/a0/ac2ba07703b30e770ab497a7e57f783f--music.jpg" />
    </div>
    <div>
      <p className="name">{"🤓" + user?.firstName || "Ketan"} {user?.lastName || "Samrat"}</p>
      <p className="prn">{user?.contactDetails?.phoneNo}</p>
    </div>

  </div>
}