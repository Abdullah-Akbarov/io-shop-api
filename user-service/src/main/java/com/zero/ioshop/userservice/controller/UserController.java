package com.zero.ioshop.userservice.controller;

import com.zero.ioshop.userservice.dto.UserDto;
import com.zero.ioshop.userservice.dto.UserLoginDto;
import com.zero.ioshop.userservice.entity.User;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * All mapping returns data using ResponseModel
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper mapper;

    /**
     * lists all users data.
     */
    @GetMapping
    public ResponseModel listAll() {
        return userService.listAll();
    }

    /**
     * gets users by dividing data into pages.
     *
     * @param id page id.
     */
    @GetMapping("/page/{id}")
    public ResponseModel listByPage(@PathVariable Integer id) {
        return userService.listByPage(id);
    }

    /**
     * gets specific user by user id.
     *
     * @param id user id
     */
    @GetMapping("{id}")
    public ResponseModel getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * gets user data using unique username.
     *
     * @param username
     */
    @GetMapping("username/{username}")
    public ResponseModel getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    /**
     * creates new user data in database
     *
     * @param userDto object is used to get user info that needs to be inserted
     */
    @PostMapping("/register")
    public ResponseModel register(@RequestBody UserDto userDto) {
        // modelMapper userDto converts into User Entity, PropertyMapper is used to skip id field, otherwise
        // map autofill id field.
        TypeMap<UserDto, User> propertyMapper = mapper.createTypeMap(UserDto.class, User.class);
        propertyMapper.addMappings(mapper -> mapper.skip(User::setId));
        return userService.save(mapper.map(userDto, User.class));
    }

    /**
     * checks if user exist or not if exists gives permission to enter server
     *
     * @param userLoginDto object is used to get login details.
     */
    @PostMapping("/login")
    public ResponseModel login(@Valid @RequestBody UserLoginDto userLoginDto) {
        // modelMapper userLoginDto converts into User Entity, PropertyMapper is used to skip id field, otherwise
        // map autofill id field.
        TypeMap<UserLoginDto, User> propertyMapper = mapper.createTypeMap(UserLoginDto.class, User.class);
        propertyMapper.addMappings(mapper -> mapper.skip(User::setId));
        return userService.login(mapper.map(userLoginDto, User.class));
    }

    /**
     * updates user data
     *
     * @param userDto object is used to get user info that needs to be inserted
     */
    @PutMapping
    public ResponseModel update(@RequestBody UserDto userDto) {
        // modelMapper userDto converts into User Entity
        User user = this.mapper.map(userDto, User.class);
        return userService.save(user);
    }

    /**
     * deactivates user from database by user id.
     *
     * @param id user id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseModel deactivate(@PathVariable Long id) {
        return userService.deactivate(id);
    }
}
