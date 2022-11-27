package com.zero.ioshop.userservice.service;

import com.zero.ioshop.userservice.model.ResponseModel;

public interface BaseService<T> {
    ResponseModel listAll();

    ResponseModel findById(Long id);

    ResponseModel save(T t);
    ResponseModel delete(Long id);
}
