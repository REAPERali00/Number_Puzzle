const express = require("express");
const app = express();
app.use(express.json());
const NumRouter = require("./routers/numpuz");
let port = 3000;

app.use("/numpuz", NumRouter);
app.get("/", (req, res) => res.send("accessing the server..."));
app.listen(port, () => {
  console.log(`Server is listening on http://127.0.0.1:${port}`);
});
