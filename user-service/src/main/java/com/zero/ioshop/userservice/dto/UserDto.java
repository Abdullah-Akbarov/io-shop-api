package com.zero.ioshop.userservice.dto;

import com.zero.ioshop.userservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /**
     * Creates DTO(Data Transfer Object) class
     * using UserDto we receive/send role data through RestController.
     */
    @Size(min = 5, max = 32, message = "username must be minimum 5 maximum 32 characters long")
    private String username;
    @Size(min = 8, max = 64, message = "password must be minimum 8 maximum 64 characters long")
    private String password;
    @Email
    private String email;
    @NotEmpty(message = "firstName, cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastName, cannot be empty")
    private String lastName;
    private Set<Role> roles;
}
