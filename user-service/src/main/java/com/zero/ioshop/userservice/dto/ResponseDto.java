package com.zero.ioshop.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// ResponseDto is for returning data from rest.
public class ResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Long companyId;
}
