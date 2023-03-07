package com.petstore.steps;

import com.petstore.World;
import com.petstore.clients.PetStoreApiClient;
import cucumber.api.java.After;

public class Hooks {
    PetStoreApiClient petStoreApiClient = new PetStoreApiClient();
    private final World world;

    public Hooks(World world) {
        this.world = world;
    }

    @After("@CleanUp")
    public void deletePetRecord() {
        petStoreApiClient.deletePetById(world.getPetObject().get("id"));
    }
}
