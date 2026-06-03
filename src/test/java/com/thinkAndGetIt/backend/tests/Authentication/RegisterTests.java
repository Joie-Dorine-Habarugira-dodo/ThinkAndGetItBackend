package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.statuscodes.StatusCodes;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterTests {
    @Test
    public void registerTest() throws IOException {
        Response response = new AuthFlow().register();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Registration successful. Check your email to verify.");
    }

    @Test
    public void registerWithExistingEmail() throws IOException {

        Response response = AuthFlow.register(
                ConfigLoader.get("email"),
                ConfigLoader.get("user_password"),
                PayloadFaker.generateFirstName(),
                PayloadFaker.generateLastName(),
                PayloadFaker.generatePhone()
        );

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CONFLICT.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Email already registered");
    }

    @Test
    public void registerWithInvalidEmail() throws IOException {

        Response response = AuthFlow.register(
                ConfigLoader.get("invalid_email"),
                ConfigLoader.get("user_password"),
                PayloadFaker.generateFirstName(),
                PayloadFaker.generateLastName(),
                PayloadFaker.generatePhone()
        );

        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid email");
    }

    @Test
    public void registerWithMissingPassword() throws IOException {

        Response response = AuthFlow.register(
                PayloadFaker.generateEmail(),
                "",
                PayloadFaker.generateFirstName(),
                PayloadFaker.generateLastName(),
                PayloadFaker.generatePhone()
        );

        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid password");
    }

    @Test
    public void registerWithMissingCredentials() throws IOException {
        Response response = AuthFlow.register(
           "",
           "",
                "",
                "",
                ""
        );
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid credentials");
    }
}
