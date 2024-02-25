const express = require("express");
const router = express.Router();

router
  .get("/ranking", (req, res) => {
    res.send("Testing router");
  })
  .post((req, res) => {})
  .delete((req, res) => {});

module.exports = router;
