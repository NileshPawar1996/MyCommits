import React from 'react';
import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from 'react-router-dom'
import {AUTH_SERVICE} from '../../config'
import jwt from 'jwt-decode' // import dependency
import { toast } from 'react-toastify';

function Login() {
    const navigate = useNavigate();
    var [user, setuser] = useState("");
    var [role, setrole] = useState("")
var Handlechange =(args)=>{
    var userdata ={...user}
    userdata[args.target.name] = args.target.value;
    setuser(userdata)
}

var Handlechangerole =(r)=>{
   setrole(r.target.value);
}

const PostEvent = () =>{
  debugger
    axios.post(AUTH_SERVICE+"/authenticate", {
      ...user
    },
    {
      // 'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJjb3Vyc2VfaWQiOm51bGwsInJvbGUiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImJhdGNoX2lkIjpudWxsLCJleHAiOjE2Nzg2MTM5NTksImlhdCI6MTY3ODUyNzU1OSwiZW1haWwiOiJzYWhpbDZAZ21haWwuY29tIn0.ckzLYYAY3icCXJWFU9eiiSbhuHKfBAmn2MTytkjTPD4', 
      'Content-Type': 'application/json'
    }
    )
    .then(function(response){
        debugger;
        var userObj = jwt(response.data.token);
        var role = userObj.role[0].authority;
        window.sessionStorage.setItem("role", role);
        window.sessionStorage.setItem("token", "Bearer "+ response.data.token);
        // window.localStorage.setItem("role", role);
        // window.localStorage.setItem("Authorization", "Bearer "+response.data);
        if(role == "ROLE_ADMIN" || role == "ROLE_TEACHER"){
          navigate("/manage/");
        }
        else if(role == "ROLE_STUDENT" )
          navigate("/manage/session/");
      })
      .catch((function(error){
        debugger;
        toast("Invalid Login");
        console.error(console.error()
      )}))
    }

  return (
    <div className='login-wrap'>
      <div className='mycard login-form'>
          <h1 className="">Welcome to EDAC</h1>
          <div className="form-group mb-3">
              <label for="exampleInputEmail1">Email address</label>
              <input type="email" onChange={Handlechange} name="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
              <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div className="form-group mb-3">
              <label for="exampleInputPassword1">Password</label>
              <input type="password" onChange={Handlechange} name="password" className="form-control" id="exampleInputPassword1" placeholder="Password" />
            </div>
            
            <button type="submit" onClick={PostEvent} value="login" className="btn btn-primary">Submit</button>
          </div>
      </div>

  );
}

export default Login;