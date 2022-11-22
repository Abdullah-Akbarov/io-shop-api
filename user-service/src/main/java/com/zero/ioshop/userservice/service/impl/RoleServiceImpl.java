package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.Role;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.repository.RoleRepository;
import com.zero.ioshop.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ResponseModel responseModel;


    @Override
    public ResponseModel listAll() {
        return responseModel.success(roleRepository.findAll());
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return responseModel.success(role.get());
        }
        return responseModel.notFound();
    }

    @Override
    public ResponseModel save(Role role) {
        Role save = roleRepository.save(role);
        if (save.getId() != null) {
            return responseModel.success(save);
        }
        return new ResponseModel(409, "couldn't save data", null);
    }

}
