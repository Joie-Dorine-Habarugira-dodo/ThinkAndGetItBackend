package com.thinkAndGetIt.backend.tests.UserManagement;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.UserFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddAddressTest {
    @Test
    public void addAddress() throws IOException {
        Response response = new UserFlow().addAddress();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.ADDRESS_ADDED);
    }
}
