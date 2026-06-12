package com.thinkAndGetIt.backend.tests.Categories;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.CategoriesFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateCategoryTests {
    @Test
    public void createCategoryTest() throws IOException {
        Response response= new CategoriesFlow().createCategory();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.ADDRESS_ADDED);

    }
}
