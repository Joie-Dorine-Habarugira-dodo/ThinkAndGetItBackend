package com.thinkAndGetIt.backend.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.PrintStream;

public class CustomLoggingFilter implements Filter {

    private PrintStream stream;

    public CustomLoggingFilter(PrintStream stream) {
        this.stream = stream;
    }

    private void log(String message) {
        stream.println(message);
        System.out.println(message);
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {

        log("*********** REQUEST ***********");
        log("URI: " + requestSpec.getURI());
        log("Method: " + requestSpec.getMethod());
        log("Headers: " + requestSpec.getHeaders());
        log("Body: " + requestSpec.getBody());
        log("\n\n");

        Response response = context.next(requestSpec, responseSpec);

        log("*********** RESPONSE ***********");
        log("Status Code: " + response.getStatusCode());
        log("Response Time: " + response.getTime() + " ms");
        log("Body:\n" + response.getBody().asPrettyString());

        return response;
    }
}
