package com.thinkAndGetIt.backend.tests.Categories;

import com.thinkAndGetIt.backend.constants.ResponseMessages;
import com.thinkAndGetIt.backend.constants.ResponsePaths;
import com.thinkAndGetIt.backend.constants.StatusCodes;
import com.thinkAndGetIt.backend.flow.CategoriesFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetCategoryTests {
    @Test
    public void testGetCategory() throws IOException {
        String slug= "electronics";
        Response response= new CategoriesFlow().getCategoryBySlug(slug);

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.CATEGORY_SLUG), slug);
    }

    //Negative tests
    @Test
    public void testGetCategoryNotFound() throws IOException {
        String slug= "water";
        Response response= new CategoriesFlow().getCategoryBySlug(slug);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.NOT_FOUND.code());
        Assert.assertFalse(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.CATEGORY_NOT_FOUND);
    }
}
