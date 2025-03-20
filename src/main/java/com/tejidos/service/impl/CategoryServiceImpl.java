package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Category;
import com.tejidos.persistence.repository.CategoryRepository;
import com.tejidos.presentation.dto.response.CategoryResponse;
import com.tejidos.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream().map(
                c -> new CategoryResponse(c.getIdCategory(), c.getNameCategory())
        ).toList();
    }

    @Override
    public CategoryResponse findById(Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if(category.isEmpty()){
            throw new RuntimeException();
        }
        return new CategoryResponse(category.get().getIdCategory(), category.get().getNameCategory());
    }
}
