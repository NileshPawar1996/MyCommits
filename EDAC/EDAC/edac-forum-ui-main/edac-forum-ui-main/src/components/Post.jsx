import axios from "axios";
import moment from "moment/moment";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import Like from "./Like";
import { FORUM_SERVICE_URL } from "../config";

export default function Post({ post, currUser, children, isOpen, setUpdated }) {
  const navigate = useNavigate();
  const openPost = (postid) => {
    if (!isOpen)
      navigate("/forum/post/" + postid, { state: post });
  }

  const mytoast = ({ type, msg }) => {
    toast[type](msg, {
      position: toast.POSITION.TOP_RIGHT
    })
  }

  const deletePost = (e) => {
    e.stopPropagation();
    console.log("deleteing post")
    mytoast({ type: "warning", msg: "post deleted!" })
    axios.delete(`${FORUM_SERVICE_URL}/post/delete/${post._id}`)
      .then(res => {
        console.log(res.data.data);
        if (!isOpen)
          setUpdated(true);
        else
          navigate("/forum")
      })
  }

  return <><div className="mycard d-flex gap-2 flex-column"
    onClick={() => { openPost(post._id) }} style={{ cursor: isOpen ? "auto" : "pointer" }}>
    <div>
      <div className="d-flex justify-content-between mb-2">
        <span className="d-flex align-items-center gap-2">
          <img src="https://static.vecteezy.com/system/resources/thumbnails/005/545/335/small/user-sign-icon-person-symbol-human-avatar-isolated-on-white-backogrund-vector.jpg" style={{ maxWidth: "100%" }} alt="profile-pic" width={28}
            className="post-prof" />
          <span> user {post.role}:{post.userId}</span>
        </span>
        <span className="text-muted">{moment(post.createdOn).format("ddd, DD MMM - hh:mm")}</span>
      </div>
      <div className="card-body d-flex flex-column gap-2">
        <h5 className="card-title m-0 text-capitalize"
        >{post.title || "untitled"}</h5>
        <article className="card-text">{post.content}</article>
        {
          post.tags.length > 0 &&
          <div className="d-flex gap-2">
            {post.tags.map(tag => <span className="mytag">{tag}</span>)}
          </div>
        }
      </div>
    </div>
    <div className="d-flex gap-3">
      <Like currUser={currUser} id={post._id} type={"post"} />
      <span className="d-flex align-items-center">
        <i className="me-2 bi-chat-left" />
        {post.commentCount}
      </span>
      {post.userId == currUser &&
        <button
          className="ms-auto btn btn-sm btn-danger"
          onClick={(e) => { deletePost(e) }}
        >
          <i className="bi bi-trash-fill" />
        </button>}
    </div>
  </div >
    {children}
  </>
}