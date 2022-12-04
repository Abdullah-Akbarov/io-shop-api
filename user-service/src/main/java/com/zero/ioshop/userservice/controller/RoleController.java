package com.zero.ioshop.userservice.controller;

import com.zero.ioshop.userservice.dto.RoleDto;
import com.zero.ioshop.userservice.entity.Role;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    /**
     * gets all user data
     */

    @GetMapping
    public ResponseModel listRoles() {
        return roleService.listAll();
    }

    /**
     * gets specific role by user id.
     *
     * @param id user id
     */
    @GetMapping("{id}")
    public ResponseModel getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    /**
     * creates new role in database
     */
    @PostMapping
    public ResponseModel save(@RequestBody RoleDto roleDto) {
        Role role = this.modelMapper.map(roleDto, Role.class);
        return roleService.save(role);
    }

    /**
     * updates role data
     */
    @PutMapping("{id}")
    public ResponseModel update(@PathVariable Long id) {
        return new ResponseModel();
    }

}