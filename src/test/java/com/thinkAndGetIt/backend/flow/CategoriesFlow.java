package com.thinkAndGetIt.backend.flow;

import com.thinkAndGetIt.backend.base.RestResource;
import com.thinkAndGetIt.backend.constants.Routes;
import com.thinkAndGetIt.backend.tokenManager.TokenManager;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Map;

import static com.thinkAndGetIt.backend.base.SpecBuilder.getRequestSpec;
import static io.restassured.RestAssured.given;

public class CategoriesFlow {
    public Response getAllCategories() throws IOException {
        return RestResource.get(Routes.CATEGORIES);
    }

    public Response createCategory() throws IOException {
        Map<String, Object> payload = Map.of(
                "name", PayloadFaker.generateCategoryName(),
                "description", PayloadFaker.generateCategoryName(),
                "parentId", ConfigLoader.get("parent_id")
        );

        return RestResource.post(TokenManager.getAdminAuthToken(), Routes.CATEGORIES, payload);
    }

    public Response getCategoryBySlug(String slug) throws IOException {
        return given().spec(getRequestSpec())
                .pathParam("slug", slug)
                .get(Routes.CATEGORY)
                .then().log().all()
                .extract().response();
    }
}
