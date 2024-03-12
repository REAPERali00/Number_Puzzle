const express = require("express");
const router = express.Router();
const {
  addRanking,
  getRankings,
  addOrUpdateRanking,
} = require("../controller/ranking_database");

router.get("/", (req, res) => {
  res.send("Hi! welcome to num puz server!");
});

router.get("/rankings", async (req, res) => {
  try {
    const rankings = await getRankings();
    res.json(rankings);
  } catch (err) {
    res.status(500).send("An error occurred while getting the rankings.");
  }
});

router.post("/submitRank", async (req, res) => {
  try {
    const newRank = req.body;
    await addOrUpdateRanking(newRank.name, newRank.time);
    res.json({ message: "Ranking submitted successfully" });
  } catch (err) {
    res
      .status(500)
      .send("An error occurred while submitting the ranking." + err);
  }
});

module.exports = router;
