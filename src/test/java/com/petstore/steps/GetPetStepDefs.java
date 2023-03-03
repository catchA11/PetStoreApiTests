package com.petstore.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.World;
import com.petstore.enums.ResponseCodes;
import com.petstore.verifications.ResponseVerifications;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.JSONArray;
import com.petstore.clients.PetStoreApiClient;
import com.petstore.enums.PetStatus;
import com.petstore.utils.JsonFilters;
import org.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPetStepDefs {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
    ResponseVerifications responseVerifications = new ResponseVerifications();
    JsonFilters jsonFilters = new JsonFilters();
    JSONArray pets;
    private final World world;

    public GetPetStepDefs(World world) {
        this.world = world;
    }

    @When("^a request is sent to the pet store of (available|sold|pending) pets$")
    public void getNumberOfPets(String status) {
        pets = petStoreApiClient.getPetsByStatus(PetStatus.fromDescription(status));
    }

    @Then("^the number of pets of type (.*) found is (\\d+)$")
    public void verifyNumberOfPetsOfType(String type, int expectedNumber) {
        int actualNumber = jsonFilters.filterPetsByType(type, pets).length();
        assertThat(actualNumber)
                .withFailMessage("Expected number of pets of type " + type + " is: " + expectedNumber +
                        ", Actual number found was " + actualNumber)
                .isEqualTo(expectedNumber);
    }

    @Then("the new pet is correctly listed in the pet store")
    public void verifyPetIsListedInPetStore() {
        JSONObject expectedPetObject = world.getPetObject();
        JSONObject actualPetObject = petStoreApiClient.getPetById((Integer) expectedPetObject.get("id"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedPet;
        JsonNode actualPet;
        try {
            expectedPet = objectMapper.readTree(expectedPetObject.toString());
            actualPet = objectMapper.readTree(actualPetObject.toString());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("");
        }

        assertThat(actualPet).withFailMessage("Expected pet object: " + expectedPetObject.toString()
                + " Actual pet object: " + actualPetObject).isEqualTo(expectedPet);
    }

    @Then("the pet is not listed in the pet store")
    public void verifyPetIsNotListedInThePetStore() {
        Response response = petStoreApiClient.getPetById(world.getPetObject().get("id").toString());
        responseVerifications.verifyResponseCodeAndStatus(response, ResponseCodes.NOT_FOUND);
    }

    @Then("the new pet status is (.*)")
    public void verifyPetStatus(PetStatus expectedPetStatus) {
        JSONObject actualPetObject = petStoreApiClient.getPetById((Integer) world.getPetObject().get("id"));
        assertThat(actualPetObject.get("status"))
                .withFailMessage("Expected pet status: " + expectedPetStatus.getStatus()
                + " Actual pet status: " + actualPetObject.get("status")).isEqualTo(expectedPetStatus.getStatus());
    }
}
