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
    this.collection = this.db.collection("rankings");
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
}

// Use the class
(async () => {
  const mongoBackend = new MongoBackend("your-mongo-uri", "your-db-name");

  try {
    await mongoBackend.connect();
    await mongoBackend.insertRanking("John Doe", "00:02:34");
    const rankings = await mongoBackend.getRankings();
    console.log(rankings);
  } catch (e) {
    console.error(e);
  } finally {
    await mongoBackend.disconnect();
  }
})();
