package utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseComponent2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setup() {
//		requestSpec = new RequestSpecBuilder()
//				.setBaseUri("https://keytrcrud.herokuapp.com/")
//				.setBasePath("api/users/")
//				.setContentType(ContentType.JSON)
//				.addHeader("accept", "application/json")
//				.build();
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode((is(200)))
						.build();
	}
	
	public static Response doPost(String body) {
		Response response = given()
					.spec(requestSpec)
					.body(body)
				.when()
					.post("save/")
				.then()
					.spec(responseSpec)
//					.statusCode(201)
					.extract()
					.response();
		return response;
	}
	
	public static Response doGetAll() {
		Response response = given()
					.spec(requestSpec)
				.when()
					.get()
				.then()
					.spec(responseSpec)
					.extract()
					.response();
		return response;
	}
	
	public static Response doGet(String id) {
		Response response = given()
					.spec(requestSpec)
				.when()
					.get(id)
				.then()
					.spec(responseSpec)
					.extract()
					.response();
		return response;
	}
	
	public static Response doDelete(String id) {
		Response response = given()
					.spec(requestSpec)
				.when()
					.delete("/delete/"+id)
				.then()
					.spec(responseSpec)
					.extract()
					.response();
		return response;
	}

}
