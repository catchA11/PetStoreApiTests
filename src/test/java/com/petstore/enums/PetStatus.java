package com.petstore.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum  PetStatus {
    AVAILABLE("available"),
    SOLD("sold"),
    PENDING("pending");

    private final String description;

    PetStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    private static final Map<String, PetStatus> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(PetStatus.values()),
            bookingFormat -> bookingFormat != null ? bookingFormat.getDescription() : null
    );

    public static PetStatus fromDescription(String description) {
        return LOOKUP.get(description);
    }
}
