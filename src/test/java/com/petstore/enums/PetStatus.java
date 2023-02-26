package com.petstore.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum  PetStatus {
    AVAILABLE("available"),
    SOLD("sold"),
    PENDING("pending");

    private final String status;
    PetStatus(String description) {
        this.status = description;
    }
    public String getStatus() {
        return status;
    }

    private static final Map<String, PetStatus> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(PetStatus.values()),
            petStatus -> petStatus != null ? petStatus.getStatus() : null
    );

    public static PetStatus fromDescription(String description) {
        return LOOKUP.get(description);
    }
}
