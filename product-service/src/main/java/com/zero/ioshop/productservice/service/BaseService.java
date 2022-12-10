package com.zero.ioshop.productservice.service;

import com.zero.ioshop.productservice.model.ResponseModel;

public interface BaseService<T> {
    ResponseModel listAll();

    ResponseModel findById(Long id);

    ResponseModel save(T t);
}
