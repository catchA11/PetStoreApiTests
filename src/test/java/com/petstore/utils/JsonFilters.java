package com.petstore.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonFilters {
    private static final Logger LOG = LoggerFactory.getLogger(JsonFilters.class);

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
                LOG.info("field :" + field + " not found for pet: " + petObject.toString());
            }
        }
        return filteredArray;
    }

    public boolean isTagListedInPet(String expectedTagId, String expectedTagName, JSONObject actualPetObject) {
        JSONArray actualTags =  actualPetObject.getJSONArray("tags");

        for (Object actualTag : actualTags) {
            JSONObject tag = (JSONObject) actualTag;
            String actualTagId = tag.get("id").toString();
            String actualTagName = tag.get("name").toString();
            if (actualTagId.equals(expectedTagId) && actualTagName.equals(expectedTagName)) {
                return true;
            }
        }
        return false;
    }
}
