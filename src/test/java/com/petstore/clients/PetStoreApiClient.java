package com.petstore.clients;

import com.petstore.enums.PetStatus;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.petstore.utils.JsonFilters;
import com.petstore.utils.JsonParser;
import static io.restassured.RestAssured.given;

public class PetStoreApiClient {
    private static final Logger log = LoggerFactory.getLogger(PetStoreApiClient.class);
    private final static String uri = "https://petstore.swagger.io/v2/pet/findByStatus";

    public JSONArray getPetsByStatus(PetStatus status) {
        JSONArray jsonArray;
        Response response = given().param("status", status.getStatus()).when().get(uri);
        if (response.statusCode() != 200) {
            log.info("PetStore API request not successful. Response Code: " + response.statusCode());
            log.info("Response being generated from __files/pets.json ");
            JsonParser jsonParser = new JsonParser();
            JsonFilters jsonFilters = new JsonFilters();
            jsonArray = jsonFilters
                    .filterPetsByStatus(status.getStatus(), jsonParser.setJSON("__files/pets.json"));
        } else {
            jsonArray = new JSONArray(response.getBody().asString());
        }
        return jsonArray;
    }
}
