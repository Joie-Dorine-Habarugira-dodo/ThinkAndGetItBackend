package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterTests {
    String email= ConfigLoader.get("email");
    String password= ConfigLoader.get("user_password");
    String firstName= PayloadFaker.generateFirstName();
    String lastName= PayloadFaker.generateLastName();
    String phone= PayloadFaker.generatePhone();
    String invalidEmail= ConfigLoader.get("invalid_email");

    @Test
    public void registerTest() throws IOException {
        Response response = new AuthFlow().register();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.REGISTER_SUCCESS);
    }

    //Negative Tests
    @Test
    public void registerWithExistingEmail() throws IOException {

        Response response = AuthFlow.register(email, password, firstName, lastName, phone);

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CONFLICT.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.EMAIL_EXISTS);
    }

    @Test
    public void registerWithInvalidEmail() throws IOException {
        Response response = AuthFlow.register(invalidEmail, password, firstName, lastName, phone);

        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void registerWithMissingPassword() throws IOException {
        Response response = AuthFlow.register(email, "", firstName, lastName, phone);

        Assert.assertEquals(response.getStatusCode(), StatusCodes.BAD_REQUEST.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.INVALID_EMAIL_OR_PASSWORD);
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
        Assert.assertEquals(response.getStatusCode(), StatusCodes.BAD_REQUEST.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.INVALID_EMAIL_OR_PASSWORD);
    }
}
