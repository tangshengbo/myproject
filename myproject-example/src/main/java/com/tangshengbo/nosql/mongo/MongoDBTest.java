package com.tangshengbo.nosql.mongo;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.util.JSON;
import com.tangshengbo.json.Account;
import org.apache.commons.io.IOUtils;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.StopWatch;

import java.util.*;

import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * Created by Tangshengbo on 2018/4/20.
 */
public class MongoDBTest {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    private MongoClient mongo;

    private MongoDatabase db;

    private MongoCollection<Document> collection;

    private MongoTemplate mongoTemplate;

    private GridFsTemplate gridFsTemplate;

    @Before
    public void init() {
        mongo = new MongoClient("127.0.0.1", 27017);
        db = mongo.getDatabase("myproject_portal");
        collection = db.getCollection("Account");
        mongoTemplate = new MongoTemplate(mongo, "myproject_portal");
        gridFsTemplate = createGridTemplate();
    }

    private GridFsTemplate createGridTemplate() {
        MongoDbFactory dbFactory = new SimpleMongoDbFactory(mongo, "myproject_portal");
        MongoMappingContext mongoMappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(dbFactory);
        return new GridFsTemplate(dbFactory, new MappingMongoConverter(dbRefResolver, mongoMappingContext));
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
        int size = 0;
        for (Document document : documents) {
            logger.info("{}", document.toJson());
            size++;
        }
        logger.info("Size:{}", size);
    }

    @Test
    public void testAddMongoTemp3late() {
        Account a = new Account();
        a.setMoney(23.2);
        a.setBirthday(new Date());
        a.setName("xxxx");
        Gson gson = new Gson();
        Object obj = JSON.parse(gson.toJson(a));
        for (int i = 3; i < 200; i++) {
            Account account = new Account();
//            account.setName("Mongo-32244" + i);
            account.setBirthday(new Date());
            account.setId(i);
//            account.setMoney(12.22);
            account.setAge(i);
            List<String> titleList = new ArrayList<>();
            titleList.add("tang");
            titleList.add("sheng");
            titleList.add("bo");
            account.setObject(obj);
            account.setTitleList(titleList);
            Map<String, Object> commentMap = new HashMap<>();
            commentMap.put("user" + i, "YES");
            commentMap.put("user" + (i + 1), "NO");
            account.setCommentMap(commentMap);
            mongoTemplate.save(account);
        }
        logger.info("OK");
    }

    @Test
    public void testQueryMongoTemplate() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < 10; i++) {
            Page<Account> accounts = paginationQuery(i);
            logger.info("{}", accounts.getContent().get(0).getId());
        }
        watch.stop();
        logger.info("{}", watch.prettyPrint());
        Criteria criteria = Criteria.where("name").is("Mongo-1").and("money").is(50.11);
        Account account = mongoTemplate.findOne(new Query(criteria), Account.class);
        logger.info("{}", account);
        account = new Account();
        account.setId(1);

        account = mongoTemplate.findById(account.getId(), Account.class);
        logger.info("{}", account);
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

    @Test
    public void testGridFsTemplate() throws Exception {
        gridFsTemplate.store(MongoDBTest.class.getResourceAsStream("/log4j.properties"), "唐.txt");
        GridFSDBFile fsdbFile = gridFsTemplate.findOne(Query.query(whereFilename().is("唐.txt")));
        logger.info("{}", IOUtils.toString(fsdbFile.getInputStream(), "UTF-8"));
    }

    private Page<Account> paginationQuery(Integer pageNum) {

        SpringDataPageable pageable = new SpringDataPageable();
        Query query = new Query();
//        List<Account> orders = new ArrayList<>();  //排序
//        Sort sort = new Sort();

        // 开始页
        pageable.setPagenumber(pageNum);
        // 每页条数
        pageable.setPagesize(10);
        // 排序
//        pageable.setSort(sort);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, Account.class);
        // 查询
        List<Account> list = mongoTemplate.find(query.with(pageable), Account.class);
        // 将集合与分页结果封装
        return new PageImpl<>(list, pageable, count);
    }
}
