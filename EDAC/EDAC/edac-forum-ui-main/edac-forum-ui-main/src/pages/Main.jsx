import { Outlet } from "react-router-dom";
// import { sessionStorage.getItem('role') } from "../config";
import AdminNav from "../navs/AdminNav";
import StudentNav from "../navs/StudentNav";
import ROLES from "../roles";

export default function Main({ title }) {
  return <div className="main-wrapper">
    {sessionStorage.getItem('role') == ROLES.STUDENT && <StudentNav />}
    {sessionStorage.getItem('role') == ROLES.ADMIN && <AdminNav />}
    <main>
      <h1>{title}</h1>
      <Outlet />
    </main>
  </div>
}