package com.mongodb;

import java.util.ArrayList;
import static com.mongodb.Helpers.printJson;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.model.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Filters.*;

public class ReplaceUpdate {
	public static void main(String [] args) {
		//replace();
		update();
	}

	private static void replace() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("test");
		
		coll.drop();
			
		for(int x=0; x<10; x++) {
			coll.insertOne(new Document()
								.append("_id", x)
								.append("x", x)
								.append("y", true));
		}
		Bson filter= new Document("x", 5);
		coll.replaceOne(filter, new Document("x",20).append("updated", true));
		
		for(Document cur : coll.find().into(new ArrayList<Document>())) {
			printJson(cur);
		}
	}

	private static void update() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("test");
		
		coll.drop();
		
		for(int x=0; x<10; x++) {
			coll.insertOne(new Document()
								.append("_id", x)
								.append("x", x)
								.append("y", true));
		}
		Bson filter= new Document("x", 1);
		coll.updateOne(filter, new Document("$set", new Document("x", 20)
															.append("updated", true)));
		
		coll.updateMany(Filters.gte("_id", 5), new Document("$set", new Document("x", 1)
																			.append("updated", true)));
				
		for(Document cur : coll.find().into(new ArrayList<Document>())) {
			printJson(cur);
		}
		
	}
}
