package com.mongodb;

import static com.mongodb.Helpers.printJson;

import java.awt.List;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CRIMongoDB {
	public static void main(String [] args){
		try {
			//create();
			read();
			//insert();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	private static void read() throws Exception {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection coll = db.getCollection("insertTest");
		
		coll.drop();
		
		//insert 10 documents
		for (int i=0; i<10; i++){
			coll.insert(new BasicDBObject ("x", i));
		}
		
		System.out.println("Find One: ");
		System.out.println(coll.findOne());
		System.out.println("\n");
		
		System.out.println("Find all with iteration: ");
		DBCursor cur = coll.find();
		while (cur.hasNext()){
			System.out.println(cur.next());
		}
		cur.close();
		System.out.println("\n");
		
		System.out.println("Count: ");
		System.out.println(coll.find().count());
		
	}

	private static void insert() throws Exception {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection coll = db.getCollection("insertTest");
		
		coll.drop();
		
		DBObject smith = new BasicDBObject ("name", "smith")
				.append("age", 30)
				.append("profession", "programmer");
		
		DBObject jones = new BasicDBObject ("name", "jones")
				.append("age", 25)
				.append("profession", "hacker");
		
		ArrayList<DBObject> list = new ArrayList<DBObject>();
		list.add(smith);
		list.add(jones);		
		
		//Insert many
		coll.insert(list);
		
		//Inserting one
		//coll.insert(smith);
	}

	private static void create() {
		Document document = new Document()
				.append("str", "MongoDB, Hello")
				.append("int", 42);
		
		printJson(document);
	}
}
