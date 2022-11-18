package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.Role;
import com.zero.ioshop.userservice.repository.RoleRepository;
import com.zero.ioshop.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public Role save(Role role) {
        return null;
    }

}
