package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ForgotPasswordTest {

    @Test
    public void forgotPassword() throws IOException {
        Response response = new AuthFlow().forgotPassword();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.RESET_EMAIL_SENT);
    }
}
