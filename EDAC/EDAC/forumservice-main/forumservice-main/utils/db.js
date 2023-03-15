const dbURI = process.env.MONGODB_URL;

const { MongoClient } = require("mongodb");

const openConn = () => {
  client = new MongoClient(dbURI);
  return client;
}

module.exports = openConn;