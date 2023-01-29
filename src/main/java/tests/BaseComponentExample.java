package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponents;
import utils.DataBuilder;

public class BaseComponentExample extends BaseComponents {

	String id;

	@Test(priority = 1)
	public void baseTest() {

		JSONObject body = DataBuilder.buildToDo();

		Response response = doPostRequest("api/save", body.toJSONString(), 200);
		id = response.jsonPath().getString("id");
		String succesMsg = response.jsonPath().getString("info");
		assertThat(succesMsg, is(equalTo("Todo saved! Nice job!")));
		
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 2)
	public void updateTAToDo() {
		Response response = doPutRequest("api/todos/" + id, DataBuilder.buildUser().toJSONString(), 201);
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 3)
	public void getAToDo() {
		Response response = doGetRequest("api/"+id, 200);
		System.out.println(response.asPrettyString());
		assertThat(id, is(equalTo(response.jsonPath().getString("_id"))));
	}
	
	@Test(priority = 4)
	public void delteAToDo() {
		Response response = doDeleterequest("api/delete/"+id, 200);
		System.out.println(response.asPrettyString());
		
		assertThat("Event deleted.", is(equalTo(response.jsonPath().getString("msg"))));
	}

}
