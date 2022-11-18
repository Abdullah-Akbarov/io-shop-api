package com.zero.ioshop.userservice.service;

import com.zero.ioshop.userservice.entity.User;

public interface UserService extends BaseService<User> {
    User findByUsername(String username);

    User findByEmail(String email);

}
