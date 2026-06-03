package com.thinkAndGetIt.backend.tests.Authentication;

import com.thinkAndGetIt.backend.base.BaseTest;
import com.thinkAndGetIt.backend.flow.AuthFlow;
import com.thinkAndGetIt.backend.statuscodes.StatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetCurrentUser extends BaseTest {
    @Test
    public void getCurrentUser() throws IOException {
        Response response= new AuthFlow().getCurrentUser();
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertEquals(response.jsonPath().getString("data.user.firstName"), "Admin");
        Assert.assertEquals(response.jsonPath().getString("data.user.lastName"), "User");

    }
}
