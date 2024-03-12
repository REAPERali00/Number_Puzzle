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

  // async addOrUpdateRanking(name, time) {
  //   const result = await this.collection.updateOne(
  //     { name: name },
  //     { $set: { time: time } },
  //     { upsert: true }
  //   );
  //   return result;
  // }

  async addOrUpdateRanking(name, time) {
    // Find the current record
    const currentRank = await this.collection.findOne({ name: name });

    // If record exists, compare times
    if (currentRank && this.isNewTimeSmaller(currentRank.time, time)) {
      // Update if new time is smaller
      const result = await this.collection.updateOne(
        { name: name },
        { $set: { time: time } }
      );
      return result;
    } else if (!currentRank) {
      // Insert new record if not found
      return await this.collection.insertOne({ name, time });
    }
  }

  // Helper method to compare times
  isNewTimeSmaller(currentTime, newTime) {
    return newTime < currentTime;
  }
}

module.exports = MongoBackend;
