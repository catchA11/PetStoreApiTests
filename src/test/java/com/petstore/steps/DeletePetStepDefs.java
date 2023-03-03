package com.petstore.steps;

import com.petstore.World;
import com.petstore.clients.PetStoreApiClient;
import cucumber.api.java.en.When;

public class DeletePetStepDefs {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
    private final World world;

    public DeletePetStepDefs(World world) {
        this.world = world;
    }

    @When("the pet is deleted by id")
    public void deletePetById() {
        petStoreApiClient.deletePetById(world.getPetObject().get("id"));
    }
}
