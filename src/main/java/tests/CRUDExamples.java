package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CRUDExamples {

	String id;
	JSONObject body;

	@org.testng.annotations.BeforeClass
	public void setup() {

		
		body = new JSONObject();
		Faker faker = new Faker();
		body.put("title", faker.lordOfTheRings().character());
		body.put("body", faker.chuckNorris().fact());

	}

	@Test(priority = 1)
	public void createTodo() {

		Response response = given().contentType(ContentType.JSON).body(body.toJSONString()).post("api/save").then().statusCode(200)
				.body("info", is(equalTo("Todo saved! Nice job!"))).body("id", is(anything())).log().all().extract()
				.response();

		id = response.jsonPath().getString("id");
	}
	
	@Test(priority = 2)
	public void getAllToDo() {
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.get("api")
				.then()
					.statusCode(200)
					.log()
					.all()
					.extract()
					.response();
		System.out.println("--------------------");
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 3)
	public void getSingleToDo() {
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.get("api/"+id)
				.then()
					.statusCode(200)
					.log().all()
					.extract()
					.response();
		System.out.println("-----------------------");
		System.out.println(response.asPrettyString());
		assertEquals(id, response.jsonPath().getString("_id"));
	}
	
	@Test(priority = 4)
	public void updateToDo() {
		body.put("body", "tri lu li lu");
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(body.toJSONString())
					.put("api/todos/" + id)
				.then()
					.statusCode(201)
					.extract()
					.response();
		System.out.println("---------- UPDATE methode --------------");
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 5)
	public void deleteTodo() {
		given()
			.contentType(ContentType.JSON)
			.delete("api/delete/" + id)
		.then()
			.statusCode(200)
			.body("msg", is(equals("Event deleted.")))
			.log()
			.all();
	}
}
