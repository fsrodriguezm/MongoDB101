package com.mongodb;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class MongoQuering {
	public static void main(String [] args){
		queringWithFilter();
		queringWithProjection();
		sorting();
	}

	private static void sorting() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("findSortTest");
		
		for(int i=0; i<10; i++) {
			for(int x=0; x<10; x++) {
				coll.insertOne(new Document().append("i", i).append("x", x));
			}
		}
		
		Bson projection = Projections.excludeId();
		projection = Projections.include("i","x");
		
		//Sorting with document 
		Bson sort = new Document("i", 1).append("x", -1);
		
		//Sorting with builder 
		Bson sort2 = ascending("i");
		
		List<Document> all = coll.find()
								.projection(projection)
								.sort(sort)
								.into(new ArrayList<Document>());
		for(Document cur : all) {
			printJson(cur);
		}
		
	}

	private static void queringWithFilter() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("employees");
		
		coll.drop();
		
		for(int i=0; i<10; i++){
			coll.insertOne(new Document()
										.append("x", new Random().nextInt(2))
										.append("y", new Random().nextInt(100)));
		}
		
		Bson filter1 = new Document("x", 0);
		Bson filter2 = and(eq("x", 0), gt("y", 10), lt("y", 90));
		
		List<Document> all = coll.find(filter2).into(new ArrayList<Document>());
		
		for(Document cur : all) {
			printJson(cur);
		}
				
	}

	private static void queringWithProjection(){
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("employees");
		
		coll.drop();
		
		for(int i=0; i<10; i++){
			coll.insertOne(new Document()
										.append("x", new Random().nextInt(2))
										.append("y", new Random().nextInt(100))
										.append("i", i));
		}
		
		Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
		
		Bson projection = new Document("y", 1).append("i", 1);
		
		//Bson projection = Projections.exclude("x");
		
		List<Document> all = coll.find(filter)
								.projection(projection)
								.into(new ArrayList<Document>());
		
		for(Document cur : all) {
			printJson(cur);
		}
		
}
}
