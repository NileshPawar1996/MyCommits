const { daoCaller } = require("../utils/responseEntity");
const express = require("express");
const router = express.Router();
const { insertPost, deletePost, handlePostLike, getAllPosts, getLikesByPostId } = require("../daos/postDao");

router.get("/", (req, res) => {
  res.send("hello from posts");
})

router.post("/add", async (req, res) => {
  const { title, content, userId, role, tags } = req.body;
  res.send(await daoCaller(
    insertPost,
    { title, content, userId, role, tags }
  ))
})

router.put("/like", async (req, res) => {
  const { unlike, userId, postId } = req.body;
  res.send(await daoCaller(
    handlePostLike,
    { unlike, userId, postId }
  ))
})

router.get("/all", async (req, res) => {
  let { skip, limit, tags, userId, role } = req.query;
  tags = tags != undefined ? tags.split(",") : undefined;
  console.log(tags);

  let response = await daoCaller(
    getAllPosts,
    { skip, limit, tags, userId, role }
  )

  res.statusCode = response.data.status || 500;
  res.send(response)
})

router.delete("/delete/:postId", async (req, res) => {
  const { postId } = req.params;

  let response = await daoCaller(
    deletePost,
    { postId }
  )

  res.statusCode = response.data.status;
  res.send(response)
})

router.get("/likes/:postId", async (req, res) => {
  const { postId } = req.params;

  let response = await daoCaller(
    getLikesByPostId,
    { postId }
  )
  res.statusCode = response.data.status || 500;
  res.send(response)
})

module.exports = router;