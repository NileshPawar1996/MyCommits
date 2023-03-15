import axios from "axios";
import { useEffect, useState } from "react"
import { FORUM_SERVICE_URL } from "../config"
import { toast } from "react-toastify";

export default function Like({ currUser, id, type }) {
  const [likedBy, setLikedBy] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isDirty, setIsDirty] = useState(true);

  const mytoast = ({ type, msg }) => {
    toast[type](msg, {
      position: toast.POSITION.TOP_RIGHT,
      // closeOnClick: true,
    })
  }

  useEffect(() => {
    if (isDirty) {
      setIsLoading(true);
      axios.get(`${FORUM_SERVICE_URL}/${type}/likes/${id}`)
        .then(res => {
          setLikedBy(res.data.data.likedBy)
          setIsDirty(false);
          setIsLoading(false);
        })
    }
  }, [isDirty, id])

  const handleLike = (e) => {
    e.stopPropagation();
    mytoast({ type: "success", msg: `${likedBy?.includes(currUser) ? "Unliked" : "Liked"} ${type}` });
    axios.put(`${FORUM_SERVICE_URL}/${type}/like`, {
      [`${type}Id`]: id,
      "userId": currUser,
      "unlike": likedBy?.includes(currUser)
    }).then(res => {
      console.log(res.data.data);
      setIsDirty(true);
    })
  }

  return <span className="text-danger d-flex align-items-center" style={{ cursor: "pointer" }}>
    {!isDirty && !isLoading > 0
      ? <i className={`me-2 ${likedBy?.includes(currUser) ? "bi-suit-heart-fill" : "bi-suit-heart"}`}
        onClick={(e) => { handleLike(e) }}
      />
      : <div className="spinner-border me-2 text-danger loader" role="status">
        <span className="visually-hidden">Loading...</span>
      </div>
    }
    <span>{likedBy?.length || 0}</span>
  </span>
}