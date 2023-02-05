package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class BaseComponentExample2 extends BaseComponent2 {
	
	String id;
	
	@Test(priority = 1)
	public void getAllUsers() {
		Response response = doGetAll();
		System.out.println(response.asString());
	}
	
	@Test(priority = 2)
	public void postUser() {
		Response response = doPost(DataBuilder.buildUser().toJSONString());
		id = response.jsonPath().getString("result._id");
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 3)
	public void getSingleUser() {
		Response response = doGet(id);
		System.out.println(response.asPrettyString());
	}

}
