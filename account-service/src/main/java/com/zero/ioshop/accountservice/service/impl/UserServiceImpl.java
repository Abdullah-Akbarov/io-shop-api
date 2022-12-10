package com.zero.ioshop.accountservice.service.impl;

import com.zero.ioshop.accountservice.dto.ResponseDto;
import com.zero.ioshop.accountservice.domain.User;
import com.zero.ioshop.accountservice.model.MessageModel;
import com.zero.ioshop.accountservice.model.ResponseModel;
import com.zero.ioshop.accountservice.repository.UserRepository;
import com.zero.ioshop.accountservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    /**
     * gets all users from database in ascending order.
     */
    @Override
    public ResponseModel listAll() {
        List<User> users = userRepository.findAllByAndIsActiveIsTrue(Sort.by("firstName").ascending());
        List<ResponseDto> collect = users.stream().map(user -> mapper.map(user, ResponseDto.class)).collect(Collectors.toList());
        return new ResponseModel(MessageModel.SUCCESS, collect);
    }

    /**
     * get users from database using paginations
     *
     * @param page number
     */
    @Override
    public ResponseModel listByPage(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 2);
        List<User> users = userRepository.findAllByAndIsActiveIsTrue(pageable);
        List<ResponseDto> collect = users.stream().map(user -> mapper.map(user, ResponseDto.class)).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return new ResponseModel(MessageModel.NOT_FOUND);
        }
        return new ResponseModel(MessageModel.SUCCESS, collect);
    }

    /**
     * gets if user info from database using unique id if not found return not found message.
     *
     * @param id user id
     */
    @Override
    public ResponseModel findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, mapper.map(user.get(), ResponseDto.class));
        }
        return new ResponseModel(MessageModel.NOT_FOUND, null);
    }

    /**
     * gets user by keywords
     *
     * @param keyword username, firstName and last name
     * @return
     */
    @Override
    public ResponseModel findAllByKeyword(String keyword) {
        List<User> allByKeyword = userRepository.findAllByKeyword(keyword);
        if (!allByKeyword.isEmpty()) {
            List<ResponseDto> collect = allByKeyword.stream().map(user -> mapper.map(user, ResponseDto.class)).collect(Collectors.toList());
            return new ResponseModel(MessageModel.SUCCESS, collect);
        }
        return new ResponseModel(MessageModel.NOT_FOUND, null);
    }


    /**
     * Login method gets user by id and checks if user exist and passwords
     * if false returns error message
     *
     * @param user User entity
     */
    @Override
    public ResponseModel login(User user) {
        Optional<User> username = userRepository.findByUsername(user.getUsername());
        if (username.isPresent() && encoder.matches(user.getPassword(), username.get().getPassword())) {
            return new ResponseModel(MessageModel.SUCCESS);
        }
        return new ResponseModel(404, "incorrect username or password");
    }

    /**
     * save method is used for creating the new user data and updating the existing one.
     *
     * @param user User entity
     */
    @Override
    public ResponseModel save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseModel(401, "username is exist");
        }
        if (user.getId() == null) {
            encodePassword(user);
            User save = userRepository.save(user);
            if (save.getId() != null) {
                return new ResponseModel(MessageModel.SUCCESS, mapper.map(save, ResponseDto.class));
            }
            return new ResponseModel(MessageModel.COULD_NOT_SAVE_RECORD);
        } else {
            Optional<User> byId = userRepository.findById(user.getId());
            if (byId.isPresent()) {
                return new ResponseModel(MessageModel.SUCCESS, mapper.map(userRepository.save(user), ResponseDto.class));
            }
            return new ResponseModel(MessageModel.NOT_EXIST);
        }

    }

    /**
     * gets user by username
     *
     * @param username
     */
    @Override
    public ResponseModel findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, mapper.map(user, ResponseDto.class));
        }
        return new ResponseModel(MessageModel.NOT_FOUND);
    }

    /**
     * method deactivate makes isActive false.
     *
     * @param id User id
     */
    @Override
    @Transactional
    public ResponseModel deactivate(Long id) {
        if (userRepository.deactivateById(id) == 1) {
            return new ResponseModel(MessageModel.SUCCESS);
        }
        return new ResponseModel(MessageModel.COULD_NOT_UPDATE_RECORD);
    }

    /**
     * encodes user password.
     *
     * @param user User entity
     */
    private void encodePassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
