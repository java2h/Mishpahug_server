package application.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

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

    public static String genMAC() {

        int leftLimit = 48; // letter 'a'
        int rightLimit = 57; // letter 'z'
        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            for (int j = 0; j < 2; j++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            buffer.append(":");
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public static LocalDate genDate() {
        return LocalDate.of(1960 + (int)(random.nextFloat() * 60),1 + (int)(random.nextFloat() * 11),1 + (int)(random.nextFloat() * 28));
    }

    public static LocalDate between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        Date date = new Date(randomMillisSinceEpoch);
        return LocalDate.of(date.getYear(),date.getMonth(),date.getDay());
    }

    public static LocalTime genTime() {
        return LocalTime.of((int)(random.nextFloat() * 23),(int)(random.nextFloat() * 59),(int)(random.nextFloat() * 59));
    }

    public static String genIP(){
        return random.nextInt(255) + "." + random.nextInt(255) + "." + random.nextInt(255) + "." + random.nextInt(255);
    }
}
