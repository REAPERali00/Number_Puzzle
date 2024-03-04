const express = require("express");
const cors = require("cors");
require("dotenv").config();
const app = express();

app.use(express.json());
app.use(cors());
const NumRouter = require("./routers/numpuz");
const port = process.env.PORT || 3000;

app.use("/numpuz", NumRouter);
app.get("/", (req, res) => res.send("accessing the server..."));
app.listen(port, () => {
  console.log(`Server is listening on http://127.0.0.1:${port}`);
});
