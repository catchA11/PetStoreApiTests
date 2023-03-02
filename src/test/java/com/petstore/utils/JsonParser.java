package com.petstore.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

public class JsonParser {
    public JSONArray jsonArray;
    private static final Logger log = LoggerFactory.getLogger(JsonFilters.class);

    public JSONArray setJSON(String path) {
        File jsonFile = new File(System.getProperty("user.dir") + "/src/test/resources/" + path);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(jsonFile);
        }
        catch (FileNotFoundException e) {
            log.error("pets.json file not found at " + jsonFile);
            e.printStackTrace();
        }
        JSONTokener jsonTokener = null;
        if (inputStream != null) {
            jsonTokener = new JSONTokener(inputStream);
        }
        jsonArray = new JSONArray(Objects.requireNonNull(jsonTokener));
        return jsonArray;
    }

    public JSONObject getPetObject(String type, String status) {
        File jsonFile = new File(System.getProperty("user.dir")
                + "/src/test/resources/__files/PetTemplate.json");
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(jsonFile);
        }
        catch (FileNotFoundException e) {
            log.error("pets.json file not found at " + jsonFile);
            e.printStackTrace();
        }
        JSONTokener jsonTokener = null;
        if (inputStream != null) {
            jsonTokener = new JSONTokener(inputStream);
        }
        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(jsonTokener));
        jsonObject.remove("status");
        jsonObject.put("status", status);
        jsonObject.remove("name");
        jsonObject.put("name", type);
        jsonObject.remove("id");
        Random random = new Random();
        int number = random.nextInt(999999);
        jsonObject.put("id", number);
        return jsonObject;
    }
}
