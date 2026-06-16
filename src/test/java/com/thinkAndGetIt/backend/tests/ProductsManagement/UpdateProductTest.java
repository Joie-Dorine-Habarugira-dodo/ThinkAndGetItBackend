package com.thinkAndGetIt.backend.tests.ProductsManagement;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.ProductFlow;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.thinkAndGetIt.backend.base.SpecBuilder.getRequestSpec;

public class UpdateProductTest {

    @Test
    public void updateProduct() throws IOException {
        Float price= PayloadFaker.getPrice();
        Response response = new ProductFlow().updateProduct("price", 49.99);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.PRODUCT_UPDATED);
    }
}
