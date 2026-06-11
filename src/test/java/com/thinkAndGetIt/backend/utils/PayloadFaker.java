package com.thinkAndGetIt.backend.utils;

import com.github.javafaker.Faker;
import com.thinkAndGetIt.backend.payloads.RegisterPayload;

public class PayloadFaker {
    private static final Faker faker = new Faker();

    //Authentication
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }
    public static String generatePassword() {
        return faker.internet().password();
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

    //User management
    public static String generateStreet() {
        return faker.address().streetAddress();
    }
    public static String generateCity() {
        return faker.address().city();
    }
    public static String generateState() {
        return faker.address().state();
    }
    public static String generateCountry() {
        return faker.address().country();
    }
    public static String generatePostalCode() {
        return faker.address().zipCode();
    }

    //Categories
    public static String generateCategoryName(){
        return faker.commerce().department();
    }
    public static String generateCategoryDescription(){
      return faker.commerce().department();
    }


}
