package com.zero.ioshop.productservice.dto;

import com.zero.ioshop.productservice.domain.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;
/**
 * Creates DTO(Data Transfer Object) class
 * using CategoryDto we receive/send category data through RestController.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank(message = "name should not be empty")
    private String name;
    private Set<SubCategory> subCategories;
}

