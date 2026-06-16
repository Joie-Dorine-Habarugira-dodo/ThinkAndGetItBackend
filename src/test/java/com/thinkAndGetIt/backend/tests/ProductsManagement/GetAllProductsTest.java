package com.thinkAndGetIt.backend.tests.ProductsManagement;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.ProductFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllProductsTest {
    @Test
    public void getAllProducts() throws IOException {
        Response response = new ProductFlow().getAllProducts();
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.MESSAGE);
    }
}
