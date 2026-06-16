package com.thinkAndGetIt.backend.tests.ProductsManagement;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.ProductFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UploadProductImagesTest {
    @Test
    public void uploadProductImages() throws IOException {
        Response response = new ProductFlow().uploadProductImages();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.IMAGES_UPLOADED);
    }
}
