package application.utils;

import java.util.Random;

public class RandomString {
	
	private static Random random = new Random();
	public static String genText(int leftLimit, int rightLimit, int size) {

		StringBuilder buffer = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

}
