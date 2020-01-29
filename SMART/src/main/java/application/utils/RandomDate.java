package application.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class RandomDate {
	
	private static Random random = new Random();
	public static LocalDate genDate() {
		return LocalDate.of(1960 + (int)(random.nextFloat() * 60),1 + (int)(random.nextFloat() * 11),1 + (int)(random.nextFloat() * 28));
	}

	public static LocalTime genTime() {
		return LocalTime.of((int)(random.nextFloat() * 23),(int)(random.nextFloat() * 59),(int)(random.nextFloat() * 59));
	}

}
