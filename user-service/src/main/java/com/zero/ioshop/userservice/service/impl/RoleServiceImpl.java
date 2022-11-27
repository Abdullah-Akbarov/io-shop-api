package com.zero.ioshop.userservice.service.impl;

import com.zero.ioshop.userservice.entity.Role;
import com.zero.ioshop.userservice.model.MessageStatus;
import com.zero.ioshop.userservice.model.ResponseModel;
import com.zero.ioshop.userservice.repository.RoleRepository;
import com.zero.ioshop.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageStatus.SUCCESS, roleRepository.findAll());
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return new ResponseModel(MessageStatus.SUCCESS, role.get());
        }
        return new ResponseModel(MessageStatus.NOT_FOUND);
    }
    @Override
    public ResponseModel save(Role role) {
        try{
            Role save = roleRepository.save(role);
            if (save.getId() != null) {
                return new ResponseModel(MessageStatus.SUCCESS, save);
            }
        } catch (DataIntegrityViolationException e){
            return new ResponseModel(409, e.getRootCause().getMessage());
        }
        return new ResponseModel(MessageStatus.COULD_NOT_SAVE_RECORD);
    }
    @Override
    public ResponseModel delete(Long id) {
        try{
           roleRepository.deleteById(id);
            return new ResponseModel(MessageStatus.SUCCESS);
        } catch (EmptyResultDataAccessException e){
            return new ResponseModel(MessageStatus.NOT_EXIST);
        }
    }
}
