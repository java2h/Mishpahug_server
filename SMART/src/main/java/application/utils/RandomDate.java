package application.utils;

import java.util.Random;

public class RandomString {
	
	private static Random random = new Random();
	public static String genText(int a, int b) {

		int leftLimit = a; // letter 'a'
		int rightLimit = b; // letter 'z'
		int targetStringLength = 10;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

}
