package com.tangshengbo.nosql.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tangshengbo on 2018/4/20.
 */
public class MongoDBTest {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    private MongoClient mongo;

    private MongoDatabase db;

    private MongoCollection<Document> collection;

    @Before
    public void init() {
        mongo = new MongoClient("127.0.0.1", 27017);
        db = mongo.getDatabase("myproject_portal");
        collection = db.getCollection("Account");
    }


    @Test
    public void testAdd() {
        for (int i = 2; i < 10000; i++) {
            Document document = new Document("title", "MongoDB")
                    .append("id", i)
                    .append("description", "database")
                    .append("likes", 100 + i)
                    .append("url", "http://www.tutorialspoint.com/mongodb/")
                    .append("by", "tutorials point");
            collection.insertOne(document);
        }
        logger.info("OK");
    }

    @Test
    public void testQuery() {
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            logger.info("{}", document.toJson());
        }
    }
}
