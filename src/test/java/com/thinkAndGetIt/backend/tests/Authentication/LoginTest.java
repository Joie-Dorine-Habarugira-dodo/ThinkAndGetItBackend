package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.base.BaseTest;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.statuscodes.StatusCodes;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginTest() throws IOException {
        Response response= new AuthFlow().login();
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Login successful");
    }

    @Test
    public void loginWithInvalidEmailTest() throws IOException {
        Response response= new AuthFlow().login(
                ConfigLoader.get("invalid_email"),
                ConfigLoader.get("password")
        );
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid email or password");
    }

    @Test
    public void loginWithInvalidPasswordTest() throws IOException {
        Response response= new AuthFlow().login(
                ConfigLoader.get("email"),
                ConfigLoader.get("invalid_password")
        );
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid email or password");
    }

    @Test
    public void loginWithMissingCredentials() throws IOException {
        Response response= new AuthFlow().login(
                "",
                ""
        );
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED.code());
    }
}

