const { MongoClient } = require("mongodb");

class MongoBackend {
  constructor(uri, dbName) {
    this.uri = uri;
    this.dbName = dbName;
    this.client = new MongoClient(this.uri, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
  }

  async connect() {
    await this.client.connect();
    console.log("Connected to MongoDB");
    this.db = this.client.db(this.dbName);
    this.collection = this.db.collection("ranking");
  }

  async disconnect() {
    await this.client.close();
    console.log("Disconnected from MongoDB");
  }

  async insertRanking(name, time) {
    const result = await this.collection.insertOne({ name, time });
    console.log(`New ranking inserted with the id ${result.insertedId}`);
  }

  async getRankings() {
    return await this.collection.find({}).toArray();
  }

  async addOrUpdateRanking(name, time) {
    const result = await this.collection.updateOne(
      { name: name },
      { $set: { time: time } },
      { upsert: true }
    );
    return result;
  }
}

module.exports = MongoBackend;
