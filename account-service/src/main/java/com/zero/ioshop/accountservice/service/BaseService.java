package com.zero.ioshop.accountservice.service;

import com.zero.ioshop.accountservice.model.ResponseModel;

public interface BaseService<T> {
    ResponseModel listAll();

    ResponseModel findById(Long id);

    ResponseModel save(T t);
}
