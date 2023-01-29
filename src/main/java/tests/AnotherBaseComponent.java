package tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponents;
import utils.DataBuilder;

public class AnotherBaseComponent extends BaseComponents {

	String id;
	String email;

	@Test
	public void createAnewUser() {
		Response response = doPostRequest("api/users/", DataBuilder.buildUser().toJSONString(), 201);
		id = response.jsonPath().getString("result._id");
		email= response.jsonPath().getString("result.email");
		assertThat(response.jsonPath().getString("success"), is(equalTo("true")));
	}
}
