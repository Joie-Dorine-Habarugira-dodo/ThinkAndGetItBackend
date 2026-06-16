package com.thinkAndGetIt.backend.utils;

import com.github.javafaker.Faker;
import com.thinkAndGetIt.backend.flow.ProductFlow;
import com.thinkAndGetIt.backend.payloads.RegisterPayload;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import java.util.UUID;

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
    public static String generateCategoryName() {
        return faker.commerce().department();
    }

    public static String generateCategoryDescription() {
        return faker.commerce().department();
    }

    //Products
    public static String getProductId() throws IOException {

        Response response = ProductFlow.createProduct();

        return response.jsonPath().getString("data.id");
    }

    public static String generateProductName() {
        return faker.commerce().productName();
    }

    public static String getDescription() {
        return faker.lorem().paragraph();
    }

    public static Float getPrice() {
        return Float.parseFloat(faker.commerce().price(10.0, 500.0));
    }

    public static Float getComparePrice() {
        return Float.parseFloat(faker.commerce().price(50.0, 700.0));
    }

    public static List<String> getTags() {
        return Arrays.asList(
                faker.commerce().department(),
                faker.commerce().material(),
                faker.commerce().promotionCode()
        );
    }

    public static boolean isFeatured() {
        return faker.bool().bool();
    }

    public static boolean isFlashSale() {
        return faker.bool().bool();
    }

    public static Float getFlashSalePrice() {
        return Float.parseFloat(faker.commerce().price(5.0, 5000.0));
    }

    public static String getSize() {
        String[] sizes = {"XS", "S", "M", "L", "XL", "XXL"};
        return sizes[faker.random().nextInt(sizes.length)];
    }

    public static String getColor() {
        String[] colors = {"Black", "White", "Red", "Blue", "Green", "Pink", "Beige", "Navy"};
        return colors[faker.random().nextInt(colors.length)];
    }

    public static String getColorHex() {
        return String.format("#%06X", faker.number().numberBetween(0, 0xFFFFFF + 1));
    }

    public static String getSku() {
        return "SKU-" + faker.number().digits(8);
    }

    public static int getStock() {
        return faker.number().numberBetween(1, 1000);
    }

    public static Float getVariantPrice() {
        return Float.parseFloat(faker.commerce().price(10.0, 500.0));
    }

    public static File getProductImage(){
        String[] images= {
                "src/test/resources/ProductImages/product1.jpg",
                "src/test/resources/ProductImages/product2.jpg",
                "src/test/resources/ProductImages/product3.jpg",
                "src/test/resources/ProductImages/product4.jpg",
                "src/test/resources/ProductImages/product5.jpg",
        };

        return new File(images[faker.number().numberBetween(0, images.length-1)]);
    }

}


