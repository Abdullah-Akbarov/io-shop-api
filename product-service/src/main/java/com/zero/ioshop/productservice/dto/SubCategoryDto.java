package com.zero.ioshop.productservice.dto;

import com.zero.ioshop.productservice.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDto {
    private String name;
    private Category category;
}
