const express = require("express");
const router = express.Router();
const { writeRank } = require("../controller/ranking");

router.get("/", (req, res) => {
  res.send("Hi! welcome to num puz server!");
});

router.get("/rankings", (req, res) => {
  res.send("Testing router");
});

router.post("/submitRank", (req, res) => {
  const newRank = req.body;
  writeRank(newRank);
  res.json("ranking submitted successfully");
});

module.exports = router;
