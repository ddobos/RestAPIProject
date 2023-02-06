package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utils.NumberChecker.numbersOnly;
import static utils.CargoCapacity.boatCargo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Homework37 {
	
//	@Test(priority = 1)
	public void homeworkTest() {
		Response response = 
				given()
					.get("https://swapi.dev/api/people/1/")
				.then()
					.extract()
					.response();
		
		JsonPath jsonPath = response.jsonPath();
		
		assertThat("Luke Skywalker", is(jsonPath.getString("name")));
		
		assertThat(jsonPath.getString("height"), is(greaterThan("171")));
		
		assertThat(jsonPath.getString("mass"), is(lessThan("80")));

		List<String> films = new ArrayList<String>(Arrays.asList(jsonPath.getString("hair_color"), jsonPath.getString("skin_color"), jsonPath.getString("eye_color")));
		assertThat(films,  containsInAnyOrder("fair","blue","blond"));
		
		assertThat(jsonPath.getString("birth_year"), is(not(numbersOnly())));
		
		List<String> species = jsonPath.getList("species");
		System.out.println(species);
		assertThat(species, is(emptyCollectionOf(String.class)));
		
		assertThat(jsonPath.getList("vehicles"), hasSize((jsonPath.getList("starships").toArray()).length));
		
		assertThat(jsonPath.getList("vehicles"), is(not(jsonPath.getList("starships"))));
		
	}
	
	@Test(priority = 2)
	public void homeworkTest2() {
		Response response = 
				given()
					.get("https://swapi.dev/api/starships/3/")
				.then()
					.extract()
					.response();
		
		JsonPath jsonPath = response.jsonPath();
		
		List<String> films = new ArrayList<>(jsonPath.getList("films"));
		
		System.out.println(films);
		String[] hasItemList = new String[] {"https://swapi.dev/api/films/2/", "https://swapi.dev/api/films/6/", "https://swapi.dev/api/films/5/"};
		System.out.println(Arrays.toString(hasItemList));
		
//		assertThat(films, hasItems("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/2/"));
		
		assertThat(films, either(hasItem("https://swapi.dev/api/films/4/")).or(hasItem("https://swapi.dev/api/films/6/")).or(hasItem("https://swapi.dev/api/films/5/")));
		
		List<String> pilots = jsonPath.getList("pilots");
		assertThat(pilots, is(emptyCollectionOf(String.class)));
		
		assertThat(films, is(not(emptyCollectionOf(String.class))));
		
		String model = jsonPath.getString("model");
		assertThat(model, both(containsString("Imperial")).and(containsString("Destroyer")));
		
		String cargo = jsonPath.getString("cargo_capacity");
		assertThat(cargo, is(boatCargo()));
		
	}

}
