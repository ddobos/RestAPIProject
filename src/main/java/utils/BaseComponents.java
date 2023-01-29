package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseComponents {
	
	@BeforeClass
	public void setup() {
//		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";
	}
	
	public static Response doPostRequest(String path, String body, int statusCode) {
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(body)
				.when()
					.post(path)
				.then()
					.statusCode(statusCode)
					.extract()
					.response();
		return response;
		
	}
	
	public static Response doPutRequest(String path, String body, int statusCode) {
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(body)
				.when()
					.put(path)
				.then()
					.statusCode(statusCode)
					.extract()
					.response();
		return response;
	}
	
	public static Response doGetRequest(String path, int statusCode) {
		Response response = 
				given()
					.contentType(ContentType.JSON)
				.when()
					.get(path)
				.then()
					.statusCode(statusCode)
					.extract()
					.response();
		
		return response;
		
	}
	
	public static Response doDeleterequest(String path, int statusCode) {
		Response response = 
				given()
					.contentType(ContentType.JSON)
				.when()
					.delete(path)
				.then()
					.statusCode(statusCode)
					.extract()
					.response();
		return response;
	}

}
