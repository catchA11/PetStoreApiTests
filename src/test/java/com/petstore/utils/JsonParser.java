package com.petstore.utils;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JsonParser {
    public JSONArray jsonArray;

    public JSONArray setJSON(String path) {
        File jsonFile = new File(System.getProperty("user.dir") + "/src/test/resources/" + path);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(jsonFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONTokener jsonTokener = null;
        if (inputStream != null) {
            jsonTokener = new JSONTokener(inputStream);
        }
        jsonArray = new JSONArray(jsonTokener);
        return jsonArray;
    }
}
