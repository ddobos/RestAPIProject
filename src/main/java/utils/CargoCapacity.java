package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacity extends TypeSafeMatcher<String>{

	public void describeTo(Description description) {
		description.appendText("expected only cargo more than 25'000'000 but : ");
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			return Integer.parseInt(item) > 25_000_000;
		}catch(NumberFormatException nfe){
			return false;
		}
	}
	
	public static Matcher<String> boatCargo(){
		return new CargoCapacity();
	}

}
