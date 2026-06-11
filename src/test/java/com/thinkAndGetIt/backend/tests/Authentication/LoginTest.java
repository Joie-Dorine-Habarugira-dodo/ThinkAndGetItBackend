package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.base.BaseTest;
import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    String email= ConfigLoader.get("email");
    String password= ConfigLoader.get("password");
    String invalidPassword= ConfigLoader.get("invalid_password");


    @Test
    public void loginTest() throws IOException {
        Response response= new AuthFlow().login();
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.LOGIN_SUCCESS);
    }

    //Negative Tests
    @Test
    public void loginWithInvalidEmailTest() throws IOException {
        Response response= new AuthFlow().login(email, password);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void loginWithInvalidPasswordTest() throws IOException {
        Response response= new AuthFlow().login(email, invalidPassword);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    public void loginWithMissingCredentials() throws IOException {
        Response response= new AuthFlow().login(
                "",
                ""
        );
        Assert.assertEquals(response.getStatusCode(), StatusCodes.BAD_REQUEST.code());
    }
}

