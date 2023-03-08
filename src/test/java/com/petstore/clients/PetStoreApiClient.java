package com.petstore.clients;

import com.petstore.enums.PetStatus;
import com.petstore.enums.ResponseCodes;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.petstore.utils.JsonFilters;
import com.petstore.utils.JsonParser;
import static io.restassured.RestAssured.given;

public class PetStoreApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(PetStoreApiClient.class);
    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";
    private final static String FIND_BY_STATUS = "/findByStatus";


    public JSONArray getPetsByStatus(PetStatus status) {
        JSONArray jsonArray;
        Response response = given().param("status", status.getStatus()).when().get(BASE_URL + FIND_BY_STATUS);
        if (response.statusCode() == ResponseCodes.OK.getCode()) {
            LOG.info("PetStore API request not successful. Response Code: " + response.statusCode());
            LOG.info("Response being generated from __files/pets.json ");
            JsonParser jsonParser = new JsonParser();
            JsonFilters jsonFilters = new JsonFilters();
            jsonArray = jsonFilters
                    .filterPetsByStatus(status.getStatus(), jsonParser.setJSON("__files/pets.json"));
        } else {
            jsonArray = new JSONArray(response.getBody().asString());
        }
        return jsonArray;
    }

    public JSONObject getPetById(Integer id) {
        Response response = given().when().get(BASE_URL + "/" + id);
        if (response.statusCode() != ResponseCodes.OK.getCode()) {
            throw new IllegalStateException("Get pet by id: " + id.toString() + " request failed. Response status: "
                    + response.getStatusLine());
        }
        return new JSONObject(response.getBody().asString());
    }

    public JSONObject postNewPet(String type, PetStatus status) {
        JsonParser jsonParser = new JsonParser();
        JSONObject newPet = jsonParser.getNewPetObjectFromTemplate(type, status.getStatus());
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(newPet.toString()).when()
                .post(BASE_URL);
        if (response.statusCode() != ResponseCodes.OK.getCode()) {
            throw new IllegalStateException("Post pet request failed. Response status: " + response.getStatusLine());
        }
        return newPet;
    }

    public void deletePetById(Object id) {
        Response response = given().when().delete(BASE_URL + "/" + id);
        if (response.statusCode() != ResponseCodes.OK.getCode()) {
            throw new IllegalStateException("Delete pet by id: " + id.toString() + " request failed. Response status: "
                    + response.getStatusLine());
        }
    }

    public Response getPetById(String id) {
        return given().when().get(BASE_URL + "/" + id);
    }

    public Response updatePetStatus(JSONObject pet, String newStatus) {
        JsonParser jsonParser = new JsonParser();
        JSONObject updatedPet = jsonParser.updateStatus(pet, newStatus);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(updatedPet.toString()).when()
                .post(BASE_URL);
        if (response.statusCode() != ResponseCodes.OK.getCode()) {
            throw new IllegalStateException("Put pet request failed. Response status: " + response.getStatusLine());
        }
        return response;
    }

    public Response addPetTag(JSONObject pet, String tagId, String tagName) {
        JsonParser jsonParser = new JsonParser();
        JSONObject updatedPet = jsonParser.addTag(pet, tagId, tagName);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(updatedPet.toString()).when()
                .post(BASE_URL);
        if (response.statusCode() != ResponseCodes.OK.getCode()) {
            throw new IllegalStateException("Put pet request failed. Response status: " + response.getStatusLine());
        }
        return response;
    }
}
