package com.petstore.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.World;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import com.petstore.clients.PetStoreApiClient;
import com.petstore.enums.PetStatus;
import com.petstore.utils.JsonFilters;
import org.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPetStepDefs {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
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
    public void verifyPetIsListedInPetStore() throws JsonProcessingException {
        JSONObject expectedPetObject = world.getPetObject();
        JSONObject actualPetObject = petStoreApiClient.getPetById((Integer) expectedPetObject.get("id"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedPet = objectMapper.readTree(expectedPetObject.toString());
        JsonNode actualPet = objectMapper.readTree(actualPetObject.toString());
        assertThat(actualPet).withFailMessage("Expected pet object: " + expectedPetObject.toString()
        + " Actual pet object: " + actualPetObject).isEqualTo(expectedPet);
    }
}
