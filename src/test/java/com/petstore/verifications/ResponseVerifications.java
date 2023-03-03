package com.petstore.verifications;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

public class ResponseVerifications {
    public void verifyResponseCodeAndStatus(Response response, int expectedCode, String expectedStatus){
        SoftAssertions softly = new SoftAssertions();
        int actualCode = response.getStatusCode();
        String actualStatus = response.getStatusLine();
        softly.assertThat(actualCode).withFailMessage("Expected response status code" + expectedCode
                + " Actual response code: " + actualCode).isEqualTo(expectedCode);
        softly.assertThat(actualStatus)
                .withFailMessage("Expected response message to contain " + expectedStatus
                        + " Actual response message: " + actualStatus).contains(expectedStatus);
        softly.assertAll();
    }
}
