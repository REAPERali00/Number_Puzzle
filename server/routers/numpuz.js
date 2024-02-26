const express = require("express");
const router = express.Router();

router.get("/", (req, res) => {
  res.send("Hi! welcome to num puz server!");
});

router
  .get("/ranking", (req, res) => {
    res.send("Testing router");
  })
  .post((req, res) => {})
  .delete((req, res) => {});

module.exports = router;
