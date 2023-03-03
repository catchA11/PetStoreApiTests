package com.petstore;

import io.restassured.response.Response;
import org.json.JSONObject;

public class World {
    private JSONObject petObject;
    private Response response;

    public JSONObject getPetObject() {
        return petObject;
    }

    public void setPetObject(JSONObject petObject) {
        this.petObject = petObject;
    }

    public Response getResponse() {return response;}

    public void setResponse(Response response) {
        this.response = response;
    }
}
