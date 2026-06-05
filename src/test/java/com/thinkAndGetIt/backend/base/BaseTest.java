package com.thinkAndGetIt.backend.base;

import io.restassured.RestAssured;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class BaseTest {
    @BeforeClass
    public void initConfig() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeSuite
    public void setupLogFile() throws FileNotFoundException {
        new PrintStream(new FileOutputStream("restAssured.log", false)).close();
    }
}
