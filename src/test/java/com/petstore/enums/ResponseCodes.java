package com.petstore.enums;

public enum ResponseCodes {
    OK(200, "Successful"),
    INVALID(400, "Invalid"),
    NOT_FOUND(404, "Not Found");

    private final int code;
    private final String statusMessage;

    ResponseCodes(int code, String statusMessage) {
        this.code = code;
        this.statusMessage = statusMessage;
    }

    public int getCode() { return code;}
    public String getStatusMessage() {return statusMessage;}
}
