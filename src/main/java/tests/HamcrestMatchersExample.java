package tests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static utils.NumberChecker.numbersOnly;


import java.util.ArrayList;
import java.util.List;



public class HamcrestMatchersExample {
	
	@Test
	public void hamcrestMatchers() {
		Response response = 
				given()
					.get("https://swapi.dev/api/planets/1/")
				.then()
					.extract()
					.response();
		System.out.println(response.asString());
		JsonPath jsonPath = response.jsonPath();
		
		String name = (jsonPath.getString("name"));
		assertEquals(name, "Tatooine");
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is(equalTo("Tatooine")));
		assertThat(name, is(("Tatooine")));
		assertThat(name, is(not("pamant")));
		assertThat(name, is(not(equalTo("pamant"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		
		assertThat(response.asString(), startsWith(("{\"name")));
		assertThat(response.asString(), startsWithIgnoringCase(("{\"NAME")));
		
		assertThat(response.asString(), endsWith(("api/planets/1/\"}")));
		assertThat(response.asString(), endsWithIgnoringCase(("API/planets/1/\"}")));
		
		assertThat(response.jsonPath().getString("created"), containsString(":"));
		assertThat(name, containsStringIgnoringCase("taTOOine"));
		
		//collections
		List<String> films = new ArrayList<>(jsonPath.getList("films"));
		System.out.println(films);
		
		assertThat(films,  contains("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/4/", "https://swapi.dev/api/films/5/", "https://swapi.dev/api/films/6/"));
		assertThat(films, containsInAnyOrder("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/4/", "https://swapi.dev/api/films/5/", "https://swapi.dev/api/films/6/"));
		
		assertThat(films, contains(
				startsWith("https"),
				endsWith("3/"),
				equalTo("https://swapi.dev/api/films/4/"),
				startsWith("https://swapi.dev"),
				endsWith("/films/6/")
				));
		assertThat(films, hasItem("https://swapi.dev/api/films/6/"));
		assertThat(films, hasItems("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/6/"));
		
		assertThat(films, hasItem(startsWith("https:")));
		assertThat(films, hasItems(startsWith("https:")));
		
		assertThat(films, hasSize(5));
		assertThat(films, hasSize(lessThanOrEqualTo(10)));
		assertThat(10, is(lessThanOrEqualTo(12)));
		
		assertThat(films, hasSize(is(greaterThan(3))));
		assertThat(films.get(0), containsString("1"));
		assertThat(films, hasToString(containsString("1")));
		assertThat(films, both(hasSize(lessThan(10))).and(hasToString(containsString("dev"))));
		
		films.clear();
		
		assertThat(films, hasSize(is(0)));
		assertThat(films, is(empty()));
		assertThat(films, is(emptyCollectionOf(String.class)));
		
		String[] myarr = {jsonPath.getString("rotation_period"), jsonPath.getString("orbital_period")};
		
//		assertThat(new String[0], (not(emptyArray())));
		
		String[] rray = {"https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/4/", "https://swapi.dev/api/films/5/", "https://swapi.dev/api/films/6/"};
		films = new ArrayList<>(jsonPath.getList("films"));
		
		assertThat(rray, equalTo(films.toArray()));
		
		assertThat(rray,arrayContaining("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/4/", "https://swapi.dev/api/films/5/", "https://swapi.dev/api/films/6/"));
		assertThat(rray,arrayContainingInAnyOrder("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/3/", "https://swapi.dev/api/films/4/", "https://swapi.dev/api/films/5/", "https://swapi.dev/api/films/6/"));
		
		assertThat(response.asString(), stringContainsInOrder("rotation_period", "diameter"));
		
		assertThat(name, both(containsString("T")).and(containsString("oo")));
		
		assertThat(name, either(is("Tatooine")).or(is("Tatooine3")));
		
		String population = jsonPath.getString("population");
		String diameter = jsonPath.getString("diameter");
		String gravity = jsonPath.getString("gravity");
		
		assertThat(population, is(numbersOnly()));
		
	}

}
