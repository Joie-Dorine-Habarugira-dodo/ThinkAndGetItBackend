package com.thinkAndGetIt.backend.tokenManager;

import com.thinkAndGetIt.backend.base.SpecBuilder;
import com.thinkAndGetIt.backend.constants.Routes;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class TokenManager {
    private static String authToken;
    private static String refreshToken;

    public static void generateTokens() throws IOException {
        System.out.println("Base URL: " + ConfigLoader.get("base_url"));
        System.out.println("Route: " + Routes.LOGIN);

        Response response = AuthFlow.login();

        authToken = response.jsonPath().getString("data.token");
        refreshToken = response.jsonPath().getString("data.refreshToken");
    }

    public static String getAuthToken() throws IOException {
        if (authToken == null) {
            generateTokens();
        }
        return authToken;
    }

    public static String getRefreshToken() throws IOException {
        if (refreshToken == null) {
            generateTokens();
        }
        return refreshToken;
    }

    public static void refreshAuthToken() throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(
                        new java.util.HashMap<String, Object>() {{
                            put("refreshToken", getRefreshToken());
                        }}
                )
                .post(Routes.REFRESH)
                .then()
                .statusCode(StatusCodes.OK.code())
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
    }
}
