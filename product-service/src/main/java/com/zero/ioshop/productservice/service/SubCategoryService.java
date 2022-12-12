package com.zero.ioshop.productservice.service;

import com.zero.ioshop.productservice.domain.SubCategory;
import com.zero.ioshop.productservice.model.ResponseModel;

public interface SubCategoryService extends BaseService<SubCategory>{
    ResponseModel findByCategoryId(Long id);
}
