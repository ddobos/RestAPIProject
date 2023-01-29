package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Homework35 {

	int id;
	JSONObject body;

	@org.testng.annotations.BeforeClass
	public void setup() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss.SSS'Z'");
		String stringDate = sdf.format(new Date());

		body = new JSONObject();
		Faker faker = new Faker();
		id = (faker.random().nextInt(4));
		body.put("title", faker.lordOfTheRings().character());
		body.put("description", faker.chuckNorris().fact());
		body.put("id", id);
		body.put("pageCount", faker.random().nextInt(1));
		body.put("excerpt", faker.harryPotter().character());
		body.put("publishDate", stringDate);
		System.out.println(body.toJSONString());
	}
	
	@Test(priority = 1)
	public void createhomewrok35() {

		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(body.toJSONString())
					.post("https://fakerestapi.azurewebsites.net/api/v1/Books")
				.then()
					.statusCode(200)
					.body("id", is(equalTo(id)))
					.extract()
					.response();
		assertEquals(String.valueOf(id), response.jsonPath().getString("id"));
	}
}
