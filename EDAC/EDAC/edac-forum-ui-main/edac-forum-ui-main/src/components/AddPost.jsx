import { useMemo, useState } from "react";
import FormComp from "./FormComp";
import CustomModal from "./CustomModal";
import { FORUM_SERVICE_URL } from "../config";
import axios from "axios";

export default function AddPost({ setUpdated }) {
  console.log(sessionStorage.getItem('curr_user'));
  const [newPost, setNewPost] = useState({
    userId: sessionStorage.getItem('curr_user'),
    role: sessionStorage.getItem('role'),
  });

  const handleChange = (e) => {
    if (e.target.name == "tags") {
      let tags = e.target.value.split(",")
      newPost.tags = tags[0].length > 0 ? tags : [];
    }
    else
      newPost[e.target.name] = e.target.value;
    setNewPost({ ...newPost });
  }

  const handleSubmit = () => {
    axios.post(`${FORUM_SERVICE_URL}/post/add`, newPost)
      .then(res => {
        setUpdated(true);
        console.log(res.data.data);
        setNewPost({
          userId: sessionStorage.getItem('curr_user'),
          role: sessionStorage.getItem('role'),
        })
      })
  }

  const clearState = () => {
    console.log("clear post");
    setNewPost({
      userId: sessionStorage.getItem('curr_user'),
      role: sessionStorage.getItem('role'),
    })
  }

  const fields = useMemo(() => [
    { label: "Title", value: newPost.title, name: "title" },
    { label: "Content", type: "textbox", name: "content", value: newPost.content },
    { label: "Tags", name: "tags", value: newPost.tags },
  ], [newPost])

  console.log(fields)

  return <CustomModal btnText="New post" clearState={clearState} modalAttr={{
    title: "New post",
    body: <FormComp fields={fields} handleChange={handleChange} />
  }} handleSubmit={handleSubmit} />
}