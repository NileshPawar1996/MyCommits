const { daoCaller, wrapData, wrapError } = require("../utils/responseEntity");
const express = require("express");
const router = express.Router();
const { addComment, deleteComment, handleCommentLike, getCommentsByPost, markCommentAsRead, getLikesByCommentId } = require("../daos/commentDao");

router.get("/", (req, res) => {
  res.send("hello from comments");
})

router.put("/add", async (req, res) => {
  const { content, userId, role, postId } = req.body;
  res.send(await daoCaller(
    addComment,
    { content, userId, role, postId }
  ))
})

router.put("/like", async (req, res) => {
  console.log("body", req.body);
  const { unlike, userId, commentId } = req.body;
  let response = await daoCaller(
    handleCommentLike,
    { commentId, userId, unlike }
  )

  res.statusCode = response.data.status;
  res.send(response)
})

router.get("/all/:postId", async (req, res) => {
  const { postId } = req.params;
  let response = await daoCaller(
    getCommentsByPost,
    { postId }
  )

  res.statusCode = response.data.status || 404;
  res.send(response)
})

router.delete("/delete/:commentId", async (req, res) => {
  const { commentId, userId, role } = req.params;
  let response = await daoCaller(
    deleteComment,
    { userId, role, commentId }
  )
  res.statusCode = response.data.status;
  res.send(response)
})

router.put("/markasread", async (req, res) => {
  const { commentId, userId, role } = req.body;
  let response = await daoCaller(
    markCommentAsRead,
    { commentId, userId, role }
  )

  res.statusCode = response.data.status;
  res.send(response);
})


router.get("/likes/:commentId", async (req, res) => {
  const { commentId } = req.params;

  let response = await daoCaller(
    getLikesByCommentId,
    { commentId }
  )

  res.statusCode = response.data.status || 200;
  res.send(response)
})

module.exports = router;