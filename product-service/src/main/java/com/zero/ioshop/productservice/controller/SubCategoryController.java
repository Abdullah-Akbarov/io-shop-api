package com.zero.ioshop.productservice.controller;

import com.zero.ioshop.productservice.domain.SubCategory;
import com.zero.ioshop.productservice.dto.SubCategoryDto;
import com.zero.ioshop.productservice.model.ResponseModel;
import com.zero.ioshop.productservice.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subcategory")
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    private final ModelMapper mapper;

    /**
     * gets all category data from service;
     *
     * @return List of SubCategory
     */
    @GetMapping
    public ResponseModel listAll() {
        return subCategoryService.listAll();
    }

    /**
     * gets SubCategory
     *
     * @param id SubCategory id
     * @return SubCategory entity
     */
    @GetMapping("{id}")
    public ResponseModel getById(@PathVariable Long id) {
        return subCategoryService.findById(id);
    }

    /**
     * saves new SubCategory
     *
     * @param subCategoryDto
     * @return saved SubCategory entity
     */
    @PostMapping
    public ResponseModel addSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        TypeMap<SubCategoryDto, SubCategory> propertyMapper = mapper.createTypeMap(SubCategoryDto.class, SubCategory.class);
        propertyMapper.addMappings(mapper -> mapper.skip(SubCategory::setId));
        return subCategoryService.save(mapper.map(subCategoryDto, SubCategory.class));
    }

    /**
     * gets updated subcategory object
     *
     * @param subCategoryDto
     * @return updated subcategory
     */
    @PutMapping
    public ResponseModel update(@RequestBody SubCategoryDto subCategoryDto) {
        return subCategoryService.save(mapper.map(subCategoryDto, SubCategory.class));
    }
}
