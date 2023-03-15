require('dotenv').config();

const PORT = process.env.PORT;

const express = require("express"),
  cors = require("cors"),
  app = express();
app.use(cors("*"));
app.use(express.json());

const postRouter = require("./routes/post");
const commentRouter = require("./routes/comment");

app.get("/", (req, res) => {
  res.send("hello dac");
});

app.use("/post", postRouter);
app.use("/comment", commentRouter);

app.listen(PORT, () => {
  console.log(`listening @ ${PORT}`);
});