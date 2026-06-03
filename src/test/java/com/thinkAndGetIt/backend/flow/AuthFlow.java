package com.thinkAndGetIt.backend.flow;

import com.thinkAndGetIt.backend.base.RestResource;
import com.thinkAndGetIt.backend.payloads.LoginPayload;
import com.thinkAndGetIt.backend.payloads.RegisterPayload;
import com.thinkAndGetIt.backend.routes.Routes;
import com.thinkAndGetIt.backend.statuscodes.StatusCodes;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AuthFlow {
    private RequestSpecification requestSpecification;

    public static Response login(String email, String password) throws IOException {
        LoginPayload payload=new LoginPayload();
        payload.setEmail(email);
        payload.setPassword(password);

        return RestResource.post(Routes.LOGIN, payload);
    }
    public static Response login() throws IOException {
        return login(
                ConfigLoader.get("email"),
                ConfigLoader.get("password")
        );
    }

    public Response getCurrentUser() throws IOException {
        String token= login().jsonPath().getString("data.token");
        return RestResource.getUser(Routes.ME,token);
    }

    public static Response register(
            String email,
            String password,
            String firstName,
            String lastName,
            String phone) throws IOException {

        RegisterPayload payload = new RegisterPayload();
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setPhone(phone);

        return RestResource.post(Routes.REGISTER, payload);
    }

    public static Response register() throws IOException {
        return register(
                PayloadFaker.generateEmail(),
                ConfigLoader.get("user_password"),
                PayloadFaker.generateFirstName(),
                PayloadFaker.generateLastName(),
                PayloadFaker.generatePhone()
        );
    }

    public Response verifyEmail() throws IOException {
        String verificationToken = register().jsonPath().getString("data.verificationToken");

        return RestResource.get(Routes.VERIFY_EMAIL +"/"+verificationToken);
    }

    public Response forgotPassword() throws IOException {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", ConfigLoader.get("email"));

        return RestResource.get(Routes.FORGOT_PASSWORD);
    }

    public Response resetPassword() throws IOException {
        String resetToken = forgotPassword().jsonPath().getString("data.resetToken");

        Map<String, Object> payload = new HashMap<>();
        payload.put("password", ConfigLoader.get("user_new_password"));

        return RestResource.post(Routes.RESET_PASSWORD + "/" + resetToken, payload);
    }

    public Response getRefreshedToken() throws IOException {
        String refreshToken = login().jsonPath().getString("data.refreshToken");
        return RestResource.post(Routes.REFRESH, Map.of("refreshToken", refreshToken));
    }


}