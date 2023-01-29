package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestExample {

//	@Test
	public void restExample() {
		Response response = given().header("Content-Type", "application/json").header("Accept", "application/json")
				.body("{\"title\":\"Titlu\",\"body\":\"corpul mesajului\"}").when()
				.post("https://keytodorestapi.herokuapp.com/api/save").then().assertThat().statusCode(200).extract()
				.response();

		String info = response.jsonPath().getString("info");

		assertEquals(info, "Todo saved! Nice job!");

		System.out.println(info);

		System.out.println(response.asPrettyString());
		System.out.println(response.asString());
	}

//	@Test
	public void restExample2() {
		JSONObject requestPayload = new JSONObject();
		requestPayload.put("title", "TITLU");
		requestPayload.put("body", "JSON object body");

		Response response = given().header("Content-Type", "application/json").header("Accept", "application/json")
				.body(requestPayload.toJSONString()).when().post("https://keytodorestapi.herokuapp.com/api/save").then()
				.assertThat().statusCode(200).extract().response();
		String info = response.jsonPath().getString("info");

		assertEquals(info, "Todo saved! Nice job!");

		System.out.println(info);

		System.out.println(response.asPrettyString());
		System.out.println(response.asString());
	}
	
	@Test
	public void restExample3() {
		File file = new File("data.json");

		Response response = 
				given()
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.body(file)
				.when()
					.post("https://keytodorestapi.herokuapp.com/api/save").then()
					.assertThat().statusCode(200)
				.extract().response();
		String info = response.jsonPath().getString("info");

		assertEquals(info, "Todo saved! Nice job!");

		System.out.println(info);

		System.out.println(response.asPrettyString());
		System.out.println(response.asString());
	}

}
