package tests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class Homework38 extends BaseComponent2 {
	
	@Test(priority = 1)
	public void homeworkTest01(){
		
		Response response = given()
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.get("https://fakerestapi.azurewebsites.net/api/v1/Books")
				.then()
					.assertThat()
					.statusCode(200)
					.extract()
					.response();
		
		JsonPath json = response.jsonPath();
		List<String> listId =json.getList("findAll{it.pageCount>1000 & it.pageCount<2000}.id");
		System.out.println(listId.size());
		
		assertThat(listId, hasSize(is(listId.size())));
	}
	
	@Test(priority = 2)
	public void homeworkTest02() {
		JSONObject body = DataBuilder.buildToDo();
		saveJSON(body.toJSONString());
		Response response = doPost(body.toJSONString());
		System.out.println(response.jsonPath().getString("info"));
		assertThat("Todo saved! Nice job!", containsString(response.jsonPath().getString("info")));
		
	}
	
	@Test(priority = 3)
	public void homeworkTest03() {
		JsonPath json = readJSON();
		Response response = doGetAll();
		String title = json.getString("title");
		System.out.println(title);
		System.out.println(response.jsonPath().getList("title"));
		response.jsonPath().getList("title");
		String id = response.jsonPath().getString("find{it.title=='"+title+"'}._id");
		System.out.println(id);
		response = doDelete(id);
		assertThat("Event deleted.", containsString(response.jsonPath().getString("msg")));
		
	}
	
	public void saveJSON(String jsonString) {
		FileWriter file;
		try {
			file = new FileWriter("todo.json");
			file.write(jsonString);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JsonPath readJSON() {
		File jsonFile = new File("todo.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		return jsonPath;
//		StringBuilder sb = new StringBuilder();
//		try {
//			File file = new File("todo.json");
//			Scanner scan = new Scanner(file);
//			while(scan.hasNext()) {
//				sb.append(scan.next());
//			}
//			scan.close();
//		}catch(IOException e) {
//			
//		}
//		return sb.toString();
	}

}
