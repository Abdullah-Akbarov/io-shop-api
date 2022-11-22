package com.zero.ioshop.userservice.service;

import com.zero.ioshop.userservice.entity.User;
import com.zero.ioshop.userservice.model.ResponseModel;

public interface UserService extends BaseService<User> {
    ResponseModel findByUsername(String username);

    ResponseModel findByEmail(String email);

}
