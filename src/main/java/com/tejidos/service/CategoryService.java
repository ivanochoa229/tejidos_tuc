package com.tejidos.service;

import com.tejidos.presentation.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse findById(Long idCategory);
}
