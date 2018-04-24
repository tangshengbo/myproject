package com.tangshengbo.nosql.mongo;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tangshengbo.json.Account;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

/**
 * Created by Tangshengbo on 2018/4/20.
 */
public class MongoDBTest {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    private MongoClient mongo;

    private MongoDatabase db;

    private MongoCollection<Document> collection;

    private MongoTemplate mongoTemplate;

    @Before
    public void init() {
        mongo = new MongoClient("127.0.0.1", 27017);
        db = mongo.getDatabase("myproject_portal");
        collection = db.getCollection("Account");
        mongoTemplate = new MongoTemplate(mongo, "myproject_portal");
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

    @Test
    public void testAddMongoTemplate() {
        for (int i = 0; i < 100; i++) {
            Account account = new Account();
            account.setName("Mongo-" + i);
            account.setBirthday(new Date());
            account.setId(i);
            account.setMoney(12.22);
            mongoTemplate.insert(account);
        }

    }

    @Test
    public void testQueryMongoTemplate() {
        long count = mongoTemplate.count(new Query(), "account");
        logger.info("{}", count);
        Criteria criteria = Criteria.where("name").is("Mongo-1").and("money").is(50.11);
        Account account = mongoTemplate.findOne(new Query(criteria), Account.class);
        logger.info("{}", account);
//        WriteResult writeResult = mongoTemplate.upsert(new Query(criteria), new Update().set("money", 20.11), "account");
//        logger.info("{}", writeResult);
    }

    @Test
    public void testUpdateMongoTemplate() {
        Criteria criteria = Criteria.where("name").is("Mongo-1").and("money").is(50.11);
        Account account = mongoTemplate.findOne(new Query(criteria), Account.class);
        logger.info("{}", account);
        Update update = new Update();
        update.set("money", 20.11);
        WriteResult writeResult = mongoTemplate.upsert(new Query(criteria), update, "account");
        logger.info("{}", writeResult);
    }
}
