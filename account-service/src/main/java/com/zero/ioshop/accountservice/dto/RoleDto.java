package com.zero.ioshop.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * Creates DTO(Data Transfer Object) class
 * using RoleDto we receive/send role data through RestController.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "name cannot be empty")
    private String description;
}
