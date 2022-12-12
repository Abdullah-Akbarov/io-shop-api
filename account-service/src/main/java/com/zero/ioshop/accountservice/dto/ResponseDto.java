package com.zero.ioshop.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ResponseDto is for returning data from rest.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Long companyId;
}
