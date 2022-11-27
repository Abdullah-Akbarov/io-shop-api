package com.zero.ioshop.userservice.controller;

import com.zero.ioshop.userservice.dto.UserDto;
import com.zero.ioshop.userservice.dto.UserLoginDto;
import com.zero.ioshop.userservice.dto.UserUpdateDto;
import com.zero.ioshop.userservice.entity.User;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseModel listAll() {
        return userService.listAll();
    }
    @GetMapping("/page/{id}")
    public ResponseModel listByPage(@PathVariable Integer id){
        return userService.listByPage(id);
    }

    @GetMapping("{id}")
    public ResponseModel getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("username/{username}")
    public ResponseModel getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/register")
    public ResponseModel register(@RequestBody UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
            return userService.save(user);
    }
    @PostMapping("/login")
    public ResponseModel login(@RequestBody UserLoginDto userLoginDto){
        return new ResponseModel();
    }
    @DeleteMapping("{id}")
    public ResponseModel delete(@PathVariable Long id){
        return userService.delete(id);
    }
    @PutMapping
    public ResponseModel update(@RequestBody UserUpdateDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return userService.save(user);
    }
}
