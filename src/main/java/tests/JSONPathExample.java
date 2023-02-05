package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONPathExample extends BaseComponentExample2{

	@Test(priority = 1)
	public void getAllUsers() {
		
		Response response = doGetAll();
		System.out.println(response.asString());
		
		assertTrue(response.asString().contains("Caesar"));
		JsonPath jsonPath = response.jsonPath();
		
		System.out.println("get all users" + jsonPath.getString("user.email"));
		
		List<String> allMales = jsonPath.getList("users.findAll{it.gender == 'm'}.gender");
		System.out.println(allMales.size());
	}
}
