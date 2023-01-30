package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponents;
import utils.DataBuilder;

public class Homewrok36 extends BaseComponents {
	String id;
	String responseName;
	
	@Test(priority = 1)
	public void createPassenger() {
		JSONObject body = DataBuilder.buildPassenger(250, 1981, true);

		Response response = doPostRequest("v1/passenger/", body.toJSONString(), 200);
		id = response.jsonPath().getString("_id");
		responseName = response.jsonPath().getString("name");
		assertThat(responseName, is(equalTo(body.get("name"))));
		
	}
	
	@Test(priority = 2)
	public void getPassengerById() {
		String path = "v1/passenger/" + id;
		Response response = doGetRequest(path, 200);
		String airlineName = response.jsonPath().getString("airline[0].name");
		assertThat("Tarom", is(equalTo(airlineName)));
	}
	
	@Test(priority = 3)
	public void updatePassenger() {
		String path = "v1/passenger/" + id;
//		Response response = doGetRequest(path, 200);
		JSONObject body = DataBuilder.buildPassenger(300, 1, false);
//		body.put("name", responseName);
		Response response = doPutRequest(path, body.toJSONString(), 200);
		assertThat("Passenger data put successfully completed.", is(equalTo(response.jsonPath().getString("message"))));
	}
	
	@Test(priority = 4)
	public void checkedAirlineAndTrip() {
		String path = "v1/passenger/" + id;
		Response response = doGetRequest(path, 200);
		String airlineName = response.jsonPath().getString("airline[0].name");
		assertThat("Quatar Airways", is(equalTo(airlineName)));
		assertThat("300", is(equalTo(response.jsonPath().getString("trips"))));
		assertThat(responseName, is(equalTo(response.jsonPath().getString("name"))));
	}
	
	@Test(priority = 5)
	public void deletePassenger() {
		String path = "v1/passenger/" + id;
		Response response = doDeleterequest(path, 200);
		String deleteMsg = response.jsonPath().getString("message");
		assertThat("Passenger data deleted successfully.", is(equalTo(deleteMsg)));
	}

}
