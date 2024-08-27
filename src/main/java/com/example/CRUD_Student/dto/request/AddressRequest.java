package com.example.CRUD_Student.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(includeFieldNames = false)
@AllArgsConstructor
public class AddressRequest {
    @NotNull(message = "Id not null")
    private Long id;

    private String city;
    private String province;
}
