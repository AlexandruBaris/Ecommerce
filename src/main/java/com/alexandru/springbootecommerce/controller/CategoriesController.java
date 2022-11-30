package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.CategoryDto;
import com.alexandru.springbootecommerce.repository.CategoryRepository;
import com.alexandru.springbootecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> allCategories(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

}
