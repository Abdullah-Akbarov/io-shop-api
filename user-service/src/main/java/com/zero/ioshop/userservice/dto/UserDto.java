package com.zero.ioshop.userservice.dto;

import com.zero.ioshop.userservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Role> roles;
}
