import axios from "axios";
import { useEffect, useState } from "react";
import { FORUM_SERVICE_URL } from "../config"
import { toast } from "react-toastify";

export default function AddComment({ postId, currUser, syncLocalPost }) {
  const [added, setAdded] = useState(false);
  const [newComment, setNewComment] = useState({
    postId: postId,
    userId: currUser,
    role: "S",
    content: "",
  });

  const mytoast = ({ type, msg }) => {
    toast[type](msg, {
      position: toast.POSITION.TOP_RIGHT
    })
  }

  const addComment = () => {
    const cmt = newComment.content;
    setNewComment({ ...newComment, content: "" })
    if (cmt.length != 0) {
      mytoast({ type: "success", msg: `Commented "${cmt}"` })
      axios.put(`${FORUM_SERVICE_URL}/comment/add`, newComment)
        .then(data => {
          console.log(data);
          setAdded(true)
        })
    } else {
      mytoast({ type: "error", msg: "Please write something" })
    }
  }

  useEffect(() => {
    if (added) {
      syncLocalPost(1);
      setAdded(false);
    }
  }, [added, syncLocalPost])

  return <div className="input-group">
    <input type="text" className="form-control" placeholder="add a comment..."
      value={newComment.content}
      onChange={(e) => { setNewComment({ ...newComment, content: e.target.value }) }} />
    <input type="button" value="add" className="btn btn-primary" onClick={addComment} />
  </div>
} 