package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.base.BaseTest;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.statuscodes.StatusCodes;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void resetPassword() throws IOException {
        Response response = new AuthFlow().resetPassword();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Password reset successful");
    }


}
