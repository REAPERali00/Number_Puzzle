const express = require("express");
const app = express();
const NumRouter = require("./routers/numpuz");
app.use("/numpuz", NumRouter);
app.listen(3000);
