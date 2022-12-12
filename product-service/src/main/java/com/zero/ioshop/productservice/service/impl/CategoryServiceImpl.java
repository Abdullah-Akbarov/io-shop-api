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

    /**
     * gets all subcategories
     */
    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageModel.SUCCESS, repository.findAll());
    }

    /**
     * finds specific category by id
     *
     * @param id
     */
    @Override
    public ResponseModel findById(Long id) {
        Optional<Category> byId = repository.findById(id);
        if (byId.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, byId.get());
        }
        return new ResponseModel(MessageModel.NOT_FOUND, null);
    }

    /**
     * saves category data or updates it
     *
     * @param category
     * @return saved or updated data
     */
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

    /**
     * turns isActive into false
     *
     * @param id subCategoryId
     */
    @Override
    public ResponseModel deactivate(Long id) {
        if (repository.findById(id).isPresent()){
            return new ResponseModel(MessageModel.NOT_EXIST);
        }
        if (repository.deactivateById(id) == 1) {
            return new ResponseModel(MessageModel.SUCCESS);
        }
        return new ResponseModel(MessageModel.COULD_NOT_DEACTIVATE_RECORD);
    }
}
