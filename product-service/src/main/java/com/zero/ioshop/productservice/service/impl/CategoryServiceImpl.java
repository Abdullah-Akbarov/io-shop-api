package com.zero.ioshop.productservice.service.impl;

import com.zero.ioshop.productservice.domain.Category;
import com.zero.ioshop.productservice.model.MessageModel;
import com.zero.ioshop.productservice.model.ResponseModel;
import com.zero.ioshop.productservice.repository.CategoryRepository;
import com.zero.ioshop.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageModel.SUCCESS, repository.findAll());
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<Category> byId = repository.findById(id);
        if (byId.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, byId.get());
        }
        return new ResponseModel(MessageModel.NOT_FOUND, null);
    }

    @Override
    public ResponseModel save(Category category) {
        if (category.getId() == null) {
            Category save = repository.save(category);
            if (save.getId() == null) {
                return new ResponseModel(MessageModel.SUCCESS, save);
            }
            return new ResponseModel(MessageModel.COULD_NOT_SAVE_RECORD);
        } else {
            Optional<Category> byId = repository.findById(category.getId());
            if (byId.isPresent()) {
                return new ResponseModel(MessageModel.SUCCESS, repository.save(category));
            }
            return new ResponseModel(MessageModel.NOT_EXIST);
        }
    }

    @Override
    public ResponseModel deactivate(Long id) {
        return null;
    }
}
