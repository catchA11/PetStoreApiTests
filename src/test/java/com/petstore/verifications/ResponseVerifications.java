package com.petstore.verifications;

import com.petstore.enums.ResponseCodes;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

public class ResponseVerifications {

    public void verifyResponseCodeAndStatus(Response response, ResponseCodes expectedResponse){
        SoftAssertions softly = new SoftAssertions();
        int actualCode = response.getStatusCode();
        String actualStatus = response.getStatusLine();
        softly.assertThat(actualCode).withFailMessage("Expected response status code"
                + expectedResponse.getCode()
                + " Actual response code: " + actualCode).isEqualTo(expectedResponse.getCode());
        softly.assertThat(actualStatus).withFailMessage("Expected response message to contain "
                + expectedResponse.getStatusMessage() + " Actual response message: "
                + actualStatus).contains(expectedResponse.getStatusMessage());
        softly.assertAll();
    }
}
