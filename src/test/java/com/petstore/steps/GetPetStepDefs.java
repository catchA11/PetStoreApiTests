package com.petstore.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import com.petstore.clients.PetStoreApiClient;
import com.petstore.enums.PetStatus;
import com.petstore.utils.JsonFilters;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPetStepDefs {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
    JsonFilters jsonFilters = new JsonFilters();
    JSONArray pets;

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
}
