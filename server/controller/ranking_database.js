require("dotenv").config();
const MongoBackend = require("../resources/database");
const mongoBackend = new MongoBackend(process.env.URI, process.env.DB);

async function addRanking(name, time) {
  try {
    await mongoBackend.connect();
    await mongoBackend.insertRanking(name, time);
    const rankings = await mongoBackend.getRankings();
    console.log(rankings);
  } catch (e) {
    console.error(e);
  } finally {
    await mongoBackend.disconnect();
  }
}

async function getRankings() {
  let rankings = [];
  try {
    await mongoBackend.connect();
    rankings = await mongoBackend.getRankings();
  } catch (e) {
    console.error(e);
  } finally {
    await mongoBackend.disconnect();
  }
  return rankings;
}

async function addOrUpdateRanking(name, time) {
  try {
    await mongoBackend.connect();
    const result = await mongoBackend.addOrUpdateRanking(name, time);
    console.log(result);
  } catch (e) {
    console.error(e);
  } finally {
    await mongoBackend.disconnect();
  }
}

module.exports = { addRanking, getRankings, addOrUpdateRanking };
