package com.zero.ioshop.userservice.controller;

import com.zero.ioshop.userservice.dto.RoleDto;
import com.zero.ioshop.userservice.entity.Role;
import com.zero.ioshop.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    @GetMapping
    public List<Role> listRoles() {
        return roleService.listAll();
    }

    @GetMapping("{id}")
    public Role getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping
    public Role save(@RequestBody RoleDto roleDto) {
        Role role = this.modelMapper.map(roleDto, Role.class);
        return roleService.save(role);
    }
}