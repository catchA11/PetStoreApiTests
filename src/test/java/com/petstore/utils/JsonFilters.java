package com.petstore.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonFilters {
    private static final Logger log = LoggerFactory.getLogger(JsonFilters.class);

    public JSONArray filterPetsByType(String name, JSONArray jsonArray) {
        return getFilteredArray("name", name, jsonArray);
    }

    public JSONArray filterPetsByStatus(String status, JSONArray jsonArray) {
        return getFilteredArray("status", status, jsonArray);
    }

    private JSONArray getFilteredArray(String field, String value, JSONArray jsonArray) {
        JSONArray filteredArray = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject petObject = jsonArray.getJSONObject(i);
            try {
                String petFieldValue = petObject.getString(field);
                if (petFieldValue.equals(value)) {
                    filteredArray.put(petObject);
                }
            } catch (JSONException e) {
                log.warn("field :" + field + " not found for pet: " + petObject.toString());
            }
        }
        return filteredArray;
    }
}
