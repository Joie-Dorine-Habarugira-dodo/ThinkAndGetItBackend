package com.thinkAndGetIt.backend.tokenManager;

import com.thinkAndGetIt.backend.routes.Routes;
import com.thinkAndGetIt.backend.statuscodes.StatusCodes;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {
    private static String authToken;
    private static String refreshToken;

    public static void generateTokens() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(
                        new java.util.HashMap<String, Object>() {{
                            put("email", ConfigLoader.get("email"));
                            put("password", ConfigLoader.get("password"));
                        }}
                )
                .post(Routes.LOGIN)
                .then()
                .statusCode(StatusCodes.OK.code())
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
        refreshToken = response.jsonPath().getString("data.refreshToken");
    }

    public static String getAuthToken() {
        if (authToken == null) {
            generateTokens();
        }
        return authToken;
    }

    public static String getRefreshToken() {
        if (refreshToken == null) {
            generateTokens();
        }
        return refreshToken;
    }

    public static void refreshAuthToken() {
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
