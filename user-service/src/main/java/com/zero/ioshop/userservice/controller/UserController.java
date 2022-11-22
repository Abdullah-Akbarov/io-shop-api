package com.zero.ioshop.userservice.controller;

import com.zero.ioshop.userservice.dto.UserDto;
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

    @GetMapping()
    public ResponseModel listAll() {
        return userService.listAll();
    }

    @GetMapping("{id}")
    public ResponseModel getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("username/{username}")
    public ResponseModel getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("mail/{email}")
    public ResponseModel getByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping()
    public ResponseModel save(@RequestBody UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return userService.save(user);
    }

}
