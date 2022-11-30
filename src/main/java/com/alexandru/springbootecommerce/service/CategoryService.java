package com.alexandru.springbootecommerce.service;

import com.alexandru.springbootecommerce.dto.CategoryDto;
import com.alexandru.springbootecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto addCategory(Long id);
    void deleteCategory(Long id);
    CategoryDto updateCategory(Long id);
}
