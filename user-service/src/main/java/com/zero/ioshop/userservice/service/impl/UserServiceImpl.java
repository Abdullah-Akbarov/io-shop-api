package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.User;
import com.zero.ioshop.userservice.repository.UserRepository;
import com.zero.ioshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
