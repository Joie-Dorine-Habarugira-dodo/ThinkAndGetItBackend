package com.thinkAndGetIt.backend.base;
import io.restassured.response.Response;

import java.io.IOException;


import static com.thinkAndGetIt.backend.base.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Object payload) throws IOException {
        return given(getRequestSpec())
                .body(payload)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response getUser(String path, String token) throws IOException {
        return given(getRequestSpec())
                .header("Authorization", "Bearer "+ token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path) throws IOException {
        return given(getRequestSpec())
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

}

