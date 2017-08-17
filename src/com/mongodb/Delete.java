package com.mongodb;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.Helpers.printJson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Delete {
public static void main(String [] args) {
	MongoClient client = new MongoClient();
	MongoDatabase db = client.getDatabase("course");
	MongoCollection<Document> coll = db.getCollection("test");
	
	coll.drop();
	
	for(int x=0; x<8; x++) {
		coll.insertOne(new Document().append("_id", x));
	}
	
	coll.deleteOne(new Document("_id", 2));
	
	Bson filter = Filters.lt("_id", 4);
	
	coll.deleteMany(filter);
	
	for(Document  cur : coll.find().into(new ArrayList<Document>())) {
		printJson(cur);
	}
}
}
