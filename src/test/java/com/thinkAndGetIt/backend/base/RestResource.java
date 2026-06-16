package com.thinkAndGetIt.backend.base;
import com.thinkAndGetIt.backend.filters.CustomLoggingFilter;
import com.thinkAndGetIt.backend.tokenManager.TokenManager;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
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

    public static Response postAvatar(String token, String path, Object file) throws IOException {
        return given()
                .baseUri(ConfigLoader.get("base_url"))
                .header("Authorization", "Bearer " + token)
                .multiPart("avatar", file)
                .log().all()
                .when()
                .post(path)
                .then().log().all()
                .extract().response();
    }

    public static Response post(String token, String path, Object payload) throws IOException {
        return given().spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(path)
                .then().log().all()
                .extract().response();
    }

    public static Response get(String path, String token) throws IOException {
        return given(getRequestSpec())
                .header("Authorization", "Bearer "+ token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, String key, String value) throws IOException {
        return given(getRequestSpec())
                .pathParam(key, value)
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

    public static Response put(String token, String path, Object payload) throws IOException {
        return given().spec(getRequestSpec())
                .header("Authorization", "Bearer "+ token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(path)
                .then().log().all()
                .extract().response();
    }

    public static Response put(String token, String path,String key, String value, Object payload) throws IOException {
        return given().spec(getRequestSpec())
                .header("Authorization", "Bearer "+ token)
                .contentType(ContentType.JSON)
                .pathParam(key, value)
                .body(payload)
                .when()
                .put(path)
                .then().log().all()
                .extract().response();
    }

    public static Response delete(String token, String endpoint, String key, String value) throws IOException {
        return given().spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .pathParam(key, value)
                .delete(endpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response uploadImage(String endpoint, String key, String value, File image) throws IOException {
        return given()
                .baseUri(ConfigLoader.get("base_url"))
                .header("Authorization", "Bearer " + TokenManager.getAdminAuthToken())
                .multiPart("images", image)
                .pathParam(key, value)
                .log().ifValidationFails()
                .when()
                .post(endpoint)
                .then().log().all()
                .extract().response();
    }

}

