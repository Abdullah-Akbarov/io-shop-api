package com.zero.ioshop.productservice.dto;

import com.zero.ioshop.productservice.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Creates DTO(Data Transfer Object) class
 * using SubCategoryDto we receive/send SubCategory data through RestController.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDto {
    private Long id;
    @NotBlank(message = "name should not be empty")
    private String name;
    @NotBlank(message = "CategoryId should not be empty")
    private Category category;
}
