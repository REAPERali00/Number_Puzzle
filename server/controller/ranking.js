const { json } = require("express");
const fs = require("fs");
const path = require("path");
const rankPath = path.join(__dirname, "ranks.json");

function readRank() {
  try {
    const data = fs.readFileSync(rankPath, "utf8");
    return JSON.parse(data);
  } catch (err) {
    console.log("couldn't read the ranking file: ", err);
    return [];
  }
}

function addRank(newRank) {
  const newData = readRank();
  newData.push(newRank);
  newData.sort((a, b) => a.userTime - b.userTime);
  return newData;
}

function writeRank(newRank) {
  try {
    const rankings = addRank(newRank);

    const data = JSON.stringify(rankings, null, 2);

    fs.writeFileSync(rankPath, data, "utf8");
  } catch (err) {
    console.log("Error writing user to file: " + err);
  }
}
const handleUser = (name, time) => {
  console.log(`handling user ${name} time: ${time}`);
};

module.exports = { writeRank, readRank, handleUser };
