package com.zero.ioshop.productservice.service.impl;

import com.zero.ioshop.productservice.domain.Category;
import com.zero.ioshop.productservice.domain.SubCategory;
import com.zero.ioshop.productservice.model.MessageModel;
import com.zero.ioshop.productservice.model.ResponseModel;
import com.zero.ioshop.productservice.repository.CategoryRepository;
import com.zero.ioshop.productservice.repository.SubCategoryRepository;
import com.zero.ioshop.productservice.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository repository;
    private final CategoryRepository categoryRepository;
    /**
     * gets all subcategories
     */
    @Override
    public ResponseModel listAll() {
        return new ResponseModel(MessageModel.SUCCESS, repository.findAll());
    }

    /**
     * finds specific subcategory by id
     * @param id
     */
    @Override
    public ResponseModel findById(Long id) {
        Optional<SubCategory> byId = repository.findById(id);
        if (byId.isPresent()) {
            return new ResponseModel(MessageModel.SUCCESS, byId.get());
        }
        return new ResponseModel(MessageModel.NOT_FOUND, null);
    }

    /**
     * gets all subcategories of category
     * @param id
     */
    @Override
    public ResponseModel findByCategoryId(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (!byId.isPresent()){
            return new ResponseModel(MessageModel.NOT_EXIST);
        }
        List<SubCategory> byCategory = repository.findByCategory(byId.get());
        if(byCategory.isEmpty()){
            return new ResponseModel(MessageModel.NOT_FOUND);
        }
        return new ResponseModel(MessageModel.SUCCESS, byCategory);
    }
    /**
     * saves subcategory data or updates it
     * @param subCategory
     * @return saved or updated data
     */
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

    /**
     * turns isActive into false
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
