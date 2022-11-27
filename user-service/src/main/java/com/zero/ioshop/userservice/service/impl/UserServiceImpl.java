package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.User;
import com.zero.ioshop.userservice.model.MessageStatus;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.repository.UserRepository;
import com.zero.ioshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageStatus.SUCCESS, userRepository.findAll());
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseModel(MessageStatus.SUCCESS, user.get());
        }
        return new ResponseModel(MessageStatus.NOT_FOUND, null);
    }

    @Override
    public ResponseModel save(User user) {
        if (user.getId() == null){
                encodePassword(user);
                User save = userRepository.save(user);
                if (save.getId() != null) {
                    return new ResponseModel(MessageStatus.SUCCESS, save);
                }
            return new ResponseModel(MessageStatus.COULD_NOT_SAVE_RECORD);
        } else {
            Optional<User> byId = userRepository.findById(user.getId());
            if (byId.isPresent()){
                return new ResponseModel(MessageStatus.SUCCESS, userRepository.save(user));
            }
            return new ResponseModel(MessageStatus.NOT_EXIST);
        }

    }

    @Override
    public ResponseModel delete(Long id) {
        try{
            userRepository.deleteById(id);
            return new ResponseModel(MessageStatus.SUCCESS);
        } catch (EmptyResultDataAccessException e){
            return new ResponseModel(MessageStatus.NOT_EXIST);
        }
    }

    @Override
    public ResponseModel findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new ResponseModel(MessageStatus.SUCCESS, user);
        }
        return new ResponseModel(MessageStatus.NOT_FOUND);
    }

    @Override
    public ResponseModel listByPage(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 2);
        return new ResponseModel(MessageStatus.SUCCESS, userRepository.findAll(pageable).get());
    }

    private void encodePassword(User user){
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
