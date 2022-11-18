package com.zero.ioshop.userservice.service;

import java.util.List;

public interface BaseService<T> {
    List<T> listAll();

    T findById(Long id);

    T save(T t);
}
