package utils;

import java.util.Random;

public class UserGenerator {
    private static final Random random = new Random();

    public static String generateRandomName() {
        return "User_" + random.nextInt(100000);
    }

    public static String generateRandomEmail() {
        return "test_" + random.nextInt(100000) + "@example.com";
    }

    public static String generateValidPassword() {
        return "pass123";
    }

    public static String generateInvalidPassword() {
        return "12345";
    }
}
