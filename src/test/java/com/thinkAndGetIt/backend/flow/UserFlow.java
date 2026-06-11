package com.thinkAndGetIt.backend.flow;

import com.thinkAndGetIt.backend.base.RestResource;
import com.thinkAndGetIt.backend.constants.Routes;
import com.thinkAndGetIt.backend.tokenManager.TokenManager;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UserFlow {
    public Response updateProfile() throws IOException {
        String token=TokenManager.getAuthToken();
        Map<String, Object> payload = Map.of(
                "firstName", PayloadFaker.generateFirstName(),
                "lastName", PayloadFaker.generateLastName(),
                "phone", PayloadFaker.generatePhone()
        );

        return RestResource.put(token, Routes.PROFILE, payload);
    }

    public Response uploadAvatar() throws IOException {
        File avatarFile = new File("src/test/resources/avatar.jpg");

        return RestResource.postAvatar(TokenManager.getAuthToken(), Routes.AVATAR, avatarFile);
    }

    public Response changePassword(String currentPassword, String newPassword) throws IOException {
        Map<String, Object> payload = Map.of(
                "currentPassword", currentPassword,
                "newPassword", newPassword
        );

        return RestResource.put(TokenManager.getAuthToken(), Routes.CHANGE_PASSWORD, payload);
    }

    public Response getAddresses() throws IOException {
        return RestResource.get(Routes.ADDRESSES, TokenManager.getAuthToken());
    }

    public Response addAddress() throws IOException {
        Map<String, Object> payload = Map.of(
                "label", ConfigLoader.get("label"),
                "firstName", ConfigLoader.get("first_name"),
                "lastName", ConfigLoader.get("last_name"),
                "phone", ConfigLoader.get("phone"),
                "street", PayloadFaker.generateStreet(),
                "city", PayloadFaker.generateCity(),
                "state", PayloadFaker.generateState(),
                "country", PayloadFaker.generateCountry(),
                "postalCode", PayloadFaker.generatePostalCode(),
                "isDefault", true
        );

        return RestResource.post(TokenManager.getAuthToken(), Routes.ADDRESSES, payload);
    }
}
