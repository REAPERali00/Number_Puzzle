const express = require("express");
const router = express.Router();
const { writeRank, readRank } = require("../controller/ranking");

router.get("/", (req, res) => {
  res.send("Hi! welcome to num puz server!");
});

router.get("/rankings", (req, res) => {
  const ranking = readRank();
  res.json(ranking);
});

router.post("/submitRank", (req, res) => {
  const newRank = req.body;
  writeRank(newRank);
  res.json("ranking submitted successfully");
});

router.post("/submitRankDebug", (req, res) => {
  const newRank = req.body;
  writeRank(newRank);
  const ranking = readRank();
  res.json(ranking);
});
module.exports = router;
