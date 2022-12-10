package com.zero.ioshop.accountservice.service.impl;

import com.zero.ioshop.accountservice.domain.Role;
import com.zero.ioshop.accountservice.model.MessageModel;
import com.zero.ioshop.accountservice.model.ResponseModel;
import com.zero.ioshop.accountservice.repository.RoleRepository;
import com.zero.ioshop.accountservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    /**
     * gets all roles from database.
     */
    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageModel.SUCCESS, roleRepository.findAll());
    }

    /**
     * gets user by id if found returns succ
     *
     * @param id
     * @return
     */
    @Override
    public ResponseModel findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, role.get());
        }
        return new ResponseModel(MessageModel.NOT_FOUND);
    }

    @Override
    public ResponseModel save(Role role) {
        try {
            Role save = roleRepository.save(role);
            if (save.getId() != null) {
                return new ResponseModel(MessageModel.SUCCESS, save);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseModel(409, e.getRootCause().getMessage());
        }
        return new ResponseModel(MessageModel.COULD_NOT_SAVE_RECORD);
    }
//    @Override
//    public ResponseModel delete(Long id) {
//        try{
//           roleRepository.deleteById(id);
//            return new ResponseModel(MessageStatus.SUCCESS);
//        } catch (EmptyResultDataAccessException e){
//            return new ResponseModel(MessageStatus.NOT_EXIST);
//        }
//    }
}
