package com.petstore;

import org.json.JSONObject;

public class World {
    private JSONObject petObject;

    public JSONObject getPetObject() {
        return petObject;
    }

    public void setPetObject(JSONObject petObject) {
        this.petObject = petObject;
    }
}
