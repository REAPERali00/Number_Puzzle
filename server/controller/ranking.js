const fs = require("fs");
const path = require("path");
const rankPath = path.join("./controller", "ranks.json");
function readRank() {}
function writeRank(rankings) {
  try {
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
