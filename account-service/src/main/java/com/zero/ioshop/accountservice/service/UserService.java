package com.zero.ioshop.accountservice.service;

import com.zero.ioshop.accountservice.domain.User;
import com.zero.ioshop.accountservice.model.ResponseModel;

public interface UserService extends BaseService<User> {
    ResponseModel findByUsername(String username);

    ResponseModel listByPage(Integer page);

    ResponseModel login(User user);

    ResponseModel deactivate(Long id);

    ResponseModel findAllByKeyword(String keyword);
   
}
