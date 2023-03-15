const openConn = require("../utils/db");
const { ObjectId } = require("mongodb");

const addComment = async ({ content, userId, role, postId }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const filter = { _id: new ObjectId(postId) };

    const newId = new ObjectId();

    const update = {
      $addToSet: {
        comments: {
          _id: newId,
          userId,
          content,
          role,
          createdOn: new Date().toISOString(),
          read: false
        }
      }
    }

    console.log(update);
    const result = await posts.updateOne(filter, update);

    result.message = `added comment with id ${newId} to post ${postId}`;
    if (result.matchedCount == 0) {
      result.message = `no post found with id ${postId}`;
    }

    return result;
  }
  finally {
    //client.close();
  }
}

const deleteComment = async ({ userId, role, commentId }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    // const filter = { _id: new ObjectId(postId) }
    const filter = { "comments._id": new ObjectId(commentId) };

    const update = {
      $pull: {
        comments: { _id: new ObjectId(commentId) }
        // comments: { _id: new ObjectId(commentId), userId, role }
      }
    }

    console.log(update);

    const result = await posts.updateOne(filter, update);

    result.message = `deleted comment with id ${commentId}`;
    result.status = "200";
    if (result.matchedCount == 0) {
      result.status = "401";
      result.message = `no comment found with id ${commentId}`;
    } else if (result.modifiedCount == 0) {
      result.status = "405";
      result.message = `user ${role}:${userId} cannot delete comment ${commentId}`;
    }

    return result;
  }
  finally {
    //client.close();
  }
}

const handleCommentLike = async ({ commentId, userId, unlike: unlike = false }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    // const postObjId = new ObjectId(postId);
    const commentObjId = new ObjectId(commentId);

    const filter = { "comments._id": commentObjId };

    const update = unlike ? {
      $pull: {
        "comments.$[filter].likedBy": userId,
      }
    } : {
      $addToSet: {
        "comments.$[filter].likedBy": userId,
      }
    }

    const options = {
      arrayFilters: [{
        "filter._id": commentObjId
      }]
    }

    console.log(update);
    const result = await posts.updateOne(filter, update, options);

    result.message = `user ${userId} ${unlike ? "unliked" : "liked"} comment with id ${commentId}`;
    if (result.matchedCount == 0) {
      result.status = "400";
      result.message = `no comment found with ${commentId}`;
    } else if (result.modifiedCount == 0) {
      result.status = "405";
      result.message = `comment ${commentId} already ${unlike ? "unliked" : "liked"} by user ${userId}`;
    }

    return result;

  } finally {
    //client.close();
  }
}

const getCommentsByPost = async ({ postId }) => {
  //add skip and limit
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const pipeline = [
      { $match: { _id: new ObjectId(postId) } },
      { $project: { comments: 1, _id: 0 } },
      { $unwind: "$comments" },
      {
        $addFields: {
          "comments.likeCount": {
            $cond: {
              if: { $isArray: "$comments.likedBy" },
              then: { $size: "$comments.likedBy" },
              else: 0
            }
          }
        }
      },
      { $sort: { 'comments.createdOn': -1 } },
      { $replaceRoot: { newRoot: "$comments" } }
    ]

    const aggCursor = await posts.aggregate(pipeline);

    // add checks and msg for post not found
    // and post has no comments

    return await aggCursor.toArray();
  }
  finally {
    //client.close();
  }
}

const markCommentAsRead = async ({ commentId, userId, role }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const filter = {
      "comments._id": new ObjectId(commentId),
      "comments.userId": userId,
      "comments.role": role
    };

    const update = {
      $set: {
        "comments.$[filter].read": true,
      }
    }

    const options = {
      arrayFilters: [{
        "filter._id": new ObjectId(commentId),
      }]
    }

    console.log(update);
    const result = await posts.updateOne(filter, update, options);
    result.message = `comment ${commentId} marked as read`;

    if (result.matchedCount == 0) {
      result.status = "400";
      result.message = `comment ${commentId} by user ${role}:${userId} not found`;
    }
    if (result.modifiedCount == 0) {
      result.status = "405";
      result.message = `comment ${commentId} already read`;
    }

    return result;
  }
  finally {
    //client.close();
  }
}

const getLikesByCommentId = async ({ commentId }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const pipeline = [
      { $match: { "comments._id": new ObjectId(commentId) } },
      {
        $project: {
          comments: {
            $map: {
              input: {
                $filter: {
                  input: "$comments",
                  as: "com",
                  cond: { $eq: ["$$com._id", new ObjectId(commentId)] }
                }
              },
              as: "single",
              in: { _id: "$$single._id", likedBy: "$$single.likedBy" }
            }
          }
        }
      },
      { $unwind: "$comments" },
      { $replaceRoot: { newRoot: "$comments" } }
    ]

    const aggCursor = await posts.aggregate(pipeline);
    const entries = await aggCursor.toArray();

    if (entries.length == 0)
      return { status: "404", message: `no comment found with id ${commentId}` }

    return entries[0];
  }
  finally {
    //client.close();
  }
}

module.exports = { addComment, deleteComment, handleCommentLike, getCommentsByPost, markCommentAsRead, getLikesByCommentId };