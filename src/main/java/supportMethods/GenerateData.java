package supportMethods;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateData {
    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomAlphaNumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String generateStringWithAllowedSplChars(int length, String allowdSplChrs) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" +   //alphabets
                "1234567890" +   //numbers
                allowdSplChrs;
        return RandomStringUtils.random(length, allowedChars);
    }

    public String generateEmail(int length) {
        return "fox" + generateRandomNumber(length) + "@gmail.com";
    }
}

