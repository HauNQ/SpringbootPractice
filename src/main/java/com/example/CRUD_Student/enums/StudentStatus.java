package com.example.CRUD_Student.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StudentStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("inactive")
    INACTIVE,
    @JsonProperty("none")
    NONE
}
