package com.zero.ioshop.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    /**
     * Creates DTO(Data Transfer Object) class
     * using UserLoginDto we receive/send role data through RestController.
     */
    @Size(min = 5, max = 32, message = "username must be minimum 5 maximum 32 characters long")
    private String username;
    @Size(min = 8, max = 64, message = "password must be minimum 8 maximum 64 characters long")
    private String password;
}
