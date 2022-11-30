package com.alexandru.springbootecommerce.service.impl;

import com.alexandru.springbootecommerce.dto.CategoryDto;
import com.alexandru.springbootecommerce.entity.Category;
import com.alexandru.springbootecommerce.exceptions.CategoryNotFoundException;
import com.alexandru.springbootecommerce.repository.CategoryRepository;
import com.alexandru.springbootecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll().stream()
                .map(CategoryDto::fromCategory)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Optional<Category> category = repository.findById(id);
        if(category.isEmpty()){
            throw new CategoryNotFoundException("Category with id " + id + " was not found");
        }
        return CategoryDto.fromCategory(category.get());
    }

    @Override
    public CategoryDto addCategory(Long id) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public CategoryDto updateCategory(Long id) {
        return null;
    }

}
