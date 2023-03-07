package com.petstore.steps;

import com.petstore.World;
import com.petstore.clients.PetStoreApiClient;
import cucumber.api.java.en.When;
import org.json.JSONObject;

public class PutPetStepDefs {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
    private final World world;

    public PutPetStepDefs(World world) {
        this.world = world;
    }

    @When("the pet status is updated to (.*)")
    public void updatePetStatus(String newStatus) {
        JSONObject pet = world.getPetObject();
        world.setResponse(petStoreApiClient.updatePetStatus(pet, newStatus));
    }

    @When("a tag with id (.*) and name (.*) is added to the pet")
    public void addTagToPet(String tagId, String tagName) {
        JSONObject pet = world.getPetObject();
        world.setResponse(petStoreApiClient.addPetTag(pet, tagId, tagName));
    }
}
