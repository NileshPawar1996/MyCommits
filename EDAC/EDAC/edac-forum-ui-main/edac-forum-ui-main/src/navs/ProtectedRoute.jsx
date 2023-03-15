import { useNavigate } from "react-router-dom";

export default function ProtectedRoute({ el }) {

  const navigate = useNavigate();
  const isLoggedIn = true;
  if (isLoggedIn)
    return el;


  else
    navigate("/login");
}
