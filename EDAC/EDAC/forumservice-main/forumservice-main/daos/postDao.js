const openConn = require("../utils/db");
const { ObjectId } = require("mongodb");

const insertPost = async ({ title, content, userId, role, tags: tags = [] }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);
    const doc = {
      title,
      content,
      userId,
      role,
      createdOn: new Date().toISOString(),
      tags
    }
    console.log(doc);

    const result = await posts.insertOne(doc);

    result.message = `new post added by user ${role}:${userId}`;

    return result;
  }
  finally {
    //client.close();
  }
}

const getLikesByPostId = async ({ postId }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const query = { _id: new ObjectId(postId) }
    const options = { projection: { likedBy: 1 } }

    const result = await posts.findOne(query, options);
    result.status = "200";

    return result;
  }
  finally {
    //client.close();
  }
}

const deletePost = async ({ postId }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const filter = { _id: new ObjectId(postId) }

    return await posts.deleteOne(filter);
  }
  finally {
    //client.close();
  }
}

const handlePostLike = async ({ postId, userId, unlike: unlike = false }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    const objId = new ObjectId(postId);
    const filter = { _id: objId }
    const update = unlike ? {
      $pull: {
        likedBy: userId
      }
    } : {
      $addToSet: {
        likedBy: userId
      }
    }

    console.log(update);
    const result = await posts.updateOne(filter, update);

    result.message = `user ${userId} ${unlike ? "unliked" : "liked"} post with id ${postId}`;
    if (result.matchedCount == 0) {
      result.message = `no post found with ${postId}`;
    } else if (result.modifiedCount == 0) {
      result.message = `post ${postId} already ${unlike ? "unliked" : "liked"} by user ${userId}`;
    }

    return result;
  } finally {
    //client.close();
  }
}

const getAllPosts = async ({ userId, role, skip, limit, tags }) => {
  try {
    const client = openConn();
    const db = client.db(process.env.DB);
    const posts = db.collection(process.env.COLLECTION);

    console.log(tags, userId, role, limit, skip);

    const match = {};

    if (userId != undefined && role != undefined) {
      match.userId = parseInt(userId);
      match.role = role;
    }

    if (tags != undefined && tags.length > 0) {
      match.tags = { $elemMatch: { $in: tags } }
    }

    console.log(match);

    const pipeline = [
      { $match: match },
      {
        $addFields: {
          commentCount: {
            $cond: {
              if: { $isArray: "$comments", },
              then: { $size: "$comments" },
              else: 0
            }
          },
          likeCount: {
            $cond: {
              if: { $isArray: "$likedBy" },
              then: { $size: "$likedBy" },
              else: 0
            }
          }
        }
      },
      { $project: { comments: 0 } },
    ]

    if (skip != undefined)
      pipeline.unshift({ $skip: parseInt(skip) });

    if (limit != undefined)
      pipeline.unshift({ $limit: parseInt(limit) + 1 });

    pipeline.unshift({ $sort: { 'createdOn': -1 } })

    const aggCursor = await posts.aggregate(pipeline);

    const result = {};
    // result.count = await aggCursor.count;
    result.posts = await aggCursor.toArray();

    result.posts.map((p, i) => console.log(i, p._id));

    console.log(result.posts.length, limit);
    console.log(result.posts.length > limit);

    result.hasMore = result.posts.length > limit;

    if (result.posts.length == 0) {
      result.status = "404";
    }

    return result;
  }
  finally {
    //client.close();
  }
}

module.exports = { insertPost, handlePostLike, getAllPosts, getLikesByPostId, deletePost };