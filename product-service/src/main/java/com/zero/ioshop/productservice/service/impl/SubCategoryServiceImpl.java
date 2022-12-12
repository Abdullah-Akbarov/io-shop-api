package com.zero.ioshop.productservice.service.impl;

import com.zero.ioshop.productservice.domain.SubCategory;
import com.zero.ioshop.productservice.model.MessageModel;
import com.zero.ioshop.productservice.model.ResponseModel;
import com.zero.ioshop.productservice.repository.SubCategoryRepository;
import com.zero.ioshop.productservice.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository repository;

    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageModel.SUCCESS, repository.findAll());
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<SubCategory> byId = repository.findById(id);
        if (byId.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, byId.get());
        }
        return new ResponseModel(MessageModel.NOT_FOUND, null);
    }

    @Override
    public ResponseModel save(SubCategory subCategory) {
        if (subCategory.getId() == null) {
            SubCategory save = repository.save(subCategory);
            if (save.getId() == null) {
                return new ResponseModel(MessageModel.SUCCESS, save);
            }
            return new ResponseModel(MessageModel.COULD_NOT_SAVE_RECORD);
        } else {
            Optional<SubCategory> byId = repository.findById(subCategory.getId());
            if (byId.isPresent()) {
                return new ResponseModel(MessageModel.SUCCESS, repository.save(subCategory));
            }
            return new ResponseModel(MessageModel.NOT_EXIST);
        }
    }

    @Override
    public ResponseModel deactivate(Long id) {
        return null;
    }
}
