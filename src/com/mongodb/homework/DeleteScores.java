package com.mongodb.homework;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Sorts.ascending;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DeleteScores {
	public static void main(String [] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("students");
		MongoCollection<Document> coll = db.getCollection("grades");
		
		
		/*Write a program in the language of your choice that will remove the grade of type "homework"
		 * with the lowest score for each student from the dataset in the handout. Since each document
		 *  is one grade, it should remove one document per student. This will use the same data set as
		 *   the last problem, but if you don't have it, you can download and re-import.
		 */
		
		//NOT CORRECT
	
		Bson filter = and(eq("type", "homework"));
		
		Bson sort = ascending("student_id", "score");		
		coll.deleteMany(filter);
			
		}
}
