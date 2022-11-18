package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.Role;
import com.zero.ioshop.userservice.repository.RoleRepository;
import com.zero.ioshop.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Role> byId = roleRepository.findById(id);
        return byId.get();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

}
