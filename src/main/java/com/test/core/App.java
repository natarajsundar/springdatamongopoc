package com.test.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.test.config.SpringMongoConfig;
import com.test.user.ProductGroup;

public class App {

	public static void main(String[] args) {
		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		// Case1 - insert a user, put "tableA" as collection name
		System.out.println("Case 1...");
		ProductGroup productGroupA = new ProductGroup("1000", "Cellphone", new Date());
		mongoOperation.save(productGroupA, "tableA");

		// find
		Query findUserQuery = new Query();
		findUserQuery.addCriteria(Criteria.where("ic").is("1000"));
		ProductGroup productGroupA1 = mongoOperation.findOne(findUserQuery, ProductGroup.class, "tableA");
		System.out.println(productGroupA1);

		// Case2 - insert a user, put entity as collection name
		System.out.println("Case 2...");
		ProductGroup productGroupB = new ProductGroup("2000", "SmartPhone", new Date());
		mongoOperation.save(productGroupB);

		// find
		ProductGroup productGroupB1 = mongoOperation.findOne(new Query(Criteria.where("ic").is("2000")), ProductGroup.class);
		System.out.println(productGroupB1);

		// Case3 - insert a list of productGroups
		System.out.println("Case 3...");
		ProductGroup productGroupC = new ProductGroup("3000", "IOSphones", new Date());
		ProductGroup productGroupD = new ProductGroup("4000", "AndroidPhones", new Date());
		ProductGroup productGroupE = new ProductGroup("5000", "Notepads", new Date());
		List<ProductGroup> productGroupList = new ArrayList<ProductGroup>();
		productGroupList.add(productGroupC);
		productGroupList.add(productGroupD);
		productGroupList.add(productGroupE);
		mongoOperation.insert(productGroupList, ProductGroup.class);

		// find
		List<ProductGroup> productGroups = mongoOperation.find(new Query(Criteria.where("ic").is("5000")),
				ProductGroup.class);

		for (ProductGroup temp : productGroups) {
			System.out.println(temp);
		}
		
		//save vs insert
		System.out.println("Case 4...");
		ProductGroup productGroupD1 = mongoOperation.findOne(new Query(Criteria.where("ic").is("5000")), ProductGroup.class);
		productGroupD1.setProductGroupName("Updated name");

		//E11000 duplicate key error index, _id existed
		//mongoOperation.insert(productGroupD1);
		mongoOperation.save(productGroupD1);
		ProductGroup productGroupE1 = mongoOperation.findOne(new Query(Criteria.where("ic").is("5000")), ProductGroup.class);
		System.out.println(productGroupE1);
	}

}
