package com.zero.ioshop.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    /**
     * Creates DTO(Data Transfer Object) class
     * using RoleDto we receive/send role data through RestController.
     */
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "name cannot be empty")
    private String description;
}
