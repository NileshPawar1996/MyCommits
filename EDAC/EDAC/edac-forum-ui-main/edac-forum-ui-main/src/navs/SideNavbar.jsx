import { Link, useNavigate } from "react-router-dom";
import NavItem from "../components/NavItem";
import UserCard from "../components/UserCard";

export default function SideNavbar({ paths }) {
  const navigate = useNavigate();

  const logout = () => {
    navigate("/login");
  }

  return <div className="nav-wrap sticky-top navbar-expand-sm">
    <nav className="navbar navbar-light bg-light sidemenu">
      <Link to="/profile"><UserCard /></Link>
      {/* <div className="collapse navbar-collapse" id="navbarSupportedContent"> */}
      <div class="accordian" id="navAccordian">
        <ul className="navbar-nav d-flex flex-column gap-2 justify-content-stretch">
          {paths.map(path => {
            return <NavItem path={path} parent={"navAccordian"} />
          })}
        </ul>
      </div>

      <button className="btn btn-danger mt-auto" onClick={logout}>Logout</button>
    </nav>
  </div>
}