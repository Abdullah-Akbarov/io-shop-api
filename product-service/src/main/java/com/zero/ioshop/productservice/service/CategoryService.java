package com.zero.ioshop.productservice.service;

import com.zero.ioshop.productservice.domain.Category;
import com.zero.ioshop.productservice.model.ResponseModel;

public interface CategoryService extends BaseService<Category>{
    ResponseModel findBySubCategoriesById(Long id);
}
