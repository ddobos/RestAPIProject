package utils;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberChecker extends TypeSafeMatcher<String>{

	@Override
	public void describeTo(Description description) {
		description.appendText("expected only numbers but : ");
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			Integer.parseInt(item);
			return true;
		}catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	public static Matcher<String> numbersOnly(){
		return new NumberChecker();
	}

}
