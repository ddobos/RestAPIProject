package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {
	
	public static JSONObject buildToDo() {
		JSONObject bodyBilder = new JSONObject();
		Faker faker = new Faker();
		bodyBilder.put("title", faker.artist().name());
		bodyBilder.put("body", faker.chuckNorris().fact());
		return bodyBilder;
	}
	
	public static JSONObject buildUser() {
		JSONObject bodyUser= new JSONObject();
		Faker faker = new Faker();
		bodyUser.put("age", faker.number().numberBetween(5, 130));
		bodyUser.put("email", faker.internet().safeEmailAddress());
		bodyUser.put("name", faker.name().firstName());
		bodyUser.put("gender", "m");
		
		return bodyUser;
	}

}
