package com.zero.ioshop.productservice.controller;

import com.zero.ioshop.productservice.domain.Category;
import com.zero.ioshop.productservice.dto.CategoryDto;
import com.zero.ioshop.productservice.model.ResponseModel;
import com.zero.ioshop.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    /**
     * gets all category data from service;
     *
     * @return List of category
     */
    @GetMapping
    public ResponseModel listAll() {
        return categoryService.listAll();
    }

    /**
     * gets category
     *
     * @param id category id
     * @return category entity
     */
    @GetMapping("{id}")
    public ResponseModel getById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    /**
     * gets new category
     *
     * @param categoryDto
     * @return saved category entity
     */
    @PostMapping
    public ResponseModel addCategory(@RequestBody CategoryDto categoryDto) {
        TypeMap<CategoryDto, Category> propertyMapper = mapper.createTypeMap(CategoryDto.class, Category.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Category::setId));
        return categoryService.save(mapper.map(categoryDto, Category.class));
    }
    /**
     * gets updated category object
     * @param categoryDto
     * @return updated category
     */
    @PutMapping
    public ResponseModel update(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(mapper.map(categoryDto, Category.class));
    }
    /**
     * gets id from api
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public ResponseModel deactivate(@PathVariable Long id){
        return categoryService.deactivate(id);
    }
}
