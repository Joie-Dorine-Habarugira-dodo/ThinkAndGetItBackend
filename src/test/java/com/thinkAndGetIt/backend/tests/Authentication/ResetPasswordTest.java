package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.base.BaseTest;
import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void resetPassword() throws IOException {
        Response response = new AuthFlow().resetPassword();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.PASSWORD_RESET_SUCCESS);
    }
}
