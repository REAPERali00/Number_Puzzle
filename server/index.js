const express = require("express");
const cors = require("cors");
const path = require("path");
require("dotenv").config();
const app = express();

app.use(express.json());
app.use(express.static("public"));
app.use(cors());
const NumRouter = require("./routers/numpuz");
const port = process.env.PORT || 3000;

app.use("/numpuz", NumRouter);
app.get("/", (req, res) =>
  res.sendFile(path.join(__dirname, "public", "frontpage/index.html"))
);
app.listen(port, () => {
  console.log(`Server is listening on ${port}`);
});
