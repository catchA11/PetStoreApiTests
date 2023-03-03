package com.petstore.steps;

import com.petstore.World;
import com.petstore.clients.PetStoreApiClient;
import com.petstore.enums.PetStatus;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.json.JSONObject;

public class PostPetStepDefs {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
    private final World world;

    public PostPetStepDefs(World world) {
        this.world = world;
    }

    @When("a new pet of type (.*) with status (available|sold|pending) is posted")
    public void postNewPet(String petType, PetStatus status) {
        JSONObject expectedPetObject = petStoreApiClient.postNewPet(petType, status);
        world.setPetObject(expectedPetObject);
    }

    @Given("a pet exists in the pet store")
    public void postNewPet() {
        postNewPet("dog", PetStatus.AVAILABLE);
    }
}
