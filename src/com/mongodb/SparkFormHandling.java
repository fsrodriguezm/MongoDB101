package com.mongodb;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {
	public static void main(String [] args){
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				SparkFormHandling.class, "/");
		
		Spark.get("/", new Route() {
			@Override
			public Object handle(final Request request, 
								final Response response){
				StringWriter writer = new StringWriter();

				try{
					Template fruitPickerTemplate = configuration.getTemplate("/resources/fruitPicker.ftl");
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
					fruitPickerTemplate.process(fruitsMap, writer);
					
					System.out.println(writer);
					
				} catch (Exception e){
					e.printStackTrace();
				}
				return writer;
			}
		});
		
		Spark.post("favorite_fruit", new Route() {
			@Override
			public Object handle(final Request request, final Response response){
					final String fruit = request.queryParams("fruit");
					if(fruit == null){
						return "Why don't you pick one?";
					}
					else{
						return "Your favorite fruit is: "+ fruit;
					}
			}
		});
	}
}
