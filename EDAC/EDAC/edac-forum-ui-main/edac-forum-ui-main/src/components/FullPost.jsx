import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import AddComment from "./AddComment";
import Like from "./Like";
import Post from "./Post";
import { FORUM_SERVICE_URL } from "../config";
import { toast } from "react-toastify";

export default function FullPost({ currUser }) {
  const { state } = useLocation();
  const [post, setPost] = useState(state);
  const [refetch, setRefetch] = useState(true);

  const mytoast = ({ type, msg }) => {
    toast[type](msg, {
      position: toast.POSITION.TOP_RIGHT
    })
  }

  useEffect(() => {
    if (refetch)
      axios.get(`${FORUM_SERVICE_URL}/comment/all/${post._id}`).then(res => {
        console.log(res);
        setPost({ ...post, comments: res.data.data });
        setRefetch(false);
      })
  }, [refetch, post]);

  const deleteComment = (commentId, e) => {
    e.stopPropagation();
    mytoast({ type: "warning", msg: "comment deleted!" })
    console.log(commentId)
    axios.delete(`${FORUM_SERVICE_URL}/comment/delete/${commentId}`).then(res => {
      console.log(res);
      syncLocalPost(-1);
    })
  }

  const syncLocalPost = (count) => {
    setPost({ ...post, commentCount: (post.commentCount + count) });
    setRefetch(true);
  }

  console.log(post);

  return <Post post={post} currUser={sessionStorage.getItem('curr_user')} isOpen={true}>
    <div className="d-flex flex-column gap-2 mt-2">
      <AddComment postId={post._id} syncLocalPost={syncLocalPost} currUser={currUser} />
      <div className="d-flex flex-column comment-wrap">
        {post.comments?.map(comment => {
          return <div key={comment._id} className="mycomment" >
            <div className="d-flex justify-content-between gap-3" >
              <div>
                <article>{comment.content}</article>
                <article className="text-muted"><small>by user {comment.userId}</small></article>
              </div>
              <div className="d-flex gap-2">
                <Like currUser={currUser} id={comment._id} type={"comment"} />
                {comment.userId == currUser &&
                  <span
                    onClick={(e) => { deleteComment(comment._id, e) }}
                  >
                    <i className="bi bi-trash-fill" />
                  </span>}
              </div>
            </div>
          </div>
        })}
      </div>
    </div>
  </Post >
}