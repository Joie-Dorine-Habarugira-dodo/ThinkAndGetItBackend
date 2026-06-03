package com.thinkAndGetIt.backend.utils;

import com.github.javafaker.Faker;
import com.thinkAndGetIt.backend.payloads.RegisterPayload;

public class PayloadFaker {
    private static final Faker faker = new Faker();

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }
    public static String generateFirstName() {
        return faker.name().firstName();
    }
    public static String generateLastName() {
        return faker.name().lastName();
    }
    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }
}
