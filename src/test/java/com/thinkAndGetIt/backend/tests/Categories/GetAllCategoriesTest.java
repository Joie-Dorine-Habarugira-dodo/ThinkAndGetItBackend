package com.thinkAndGetIt.backend.tests.Categories;

import com.thinkAndGetIt.backend.base.BaseTest;
import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.CategoriesFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllCategoriesTest extends BaseTest {
    @Test
    public void getAllCategoriesTest() throws IOException {
        Response response = new CategoriesFlow().getAllCategories();

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.SUCCESS);
    }

}
