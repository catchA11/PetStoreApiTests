package com.petstore.steps;

import static org.assertj.core.api.Assertions.assertThat;
import com.petstore.World;
import com.petstore.clients.PetStoreApiClient;
import com.petstore.enums.PetStatus;
import com.petstore.enums.ResponseCodes;
import com.petstore.utils.JsonFilters;
import com.petstore.verifications.ResponseVerifications;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

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
        JSONObject expectedPet = world.getPetObject();
        JSONObject actualPet = petStoreApiClient.getPetById((Integer) expectedPet.get("id"));

        assertThat(actualPet.toString()).withFailMessage("Expected pet object: "
            + expectedPet.toString() + " Actual pet object: " + actualPet)
            .isEqualTo(expectedPet.toString());
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

    @Then("the pet is updated wih a tag with id (.*) and name (.*)")
    public void verifyPetHasTag(String tagId, String tagName) {
        JSONObject actualPetObject = petStoreApiClient.getPetById((Integer) world.getPetObject().get("id"));
        boolean isTagFound = jsonFilters.isTagListedInPet(tagId, tagName, actualPetObject);
        assertThat(isTagFound).withFailMessage("Tag with id: " + tagId + " and name: " + tagName
                + " was not found in pet with id " + world.getPetObject().get("id")).isTrue();
    }
}
