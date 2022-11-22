package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.User;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.repository.UserRepository;
import com.zero.ioshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResponseModel responseModel;

    @Override
    public ResponseModel listAll() {
        return responseModel.success(userRepository.findAll());
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return responseModel.success(user.get());
        }
        return responseModel.notFound();
    }

    @Override
    public ResponseModel save(User user) {
        User save = userRepository.save(user);
        if (save.getId() != null) {
            return responseModel.success(save);
        }
        return new ResponseModel(409, "couldn't save the data", null);
    }

    @Override
    public ResponseModel findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return responseModel.success(user);
        }
        return responseModel.notFound();
    }

    @Override
    public ResponseModel findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return responseModel.success(user);
        }
        return responseModel.notFound();
    }
}
