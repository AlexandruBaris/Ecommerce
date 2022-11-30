package com.alexandru.springbootecommerce.service.impl;

import com.alexandru.springbootecommerce.dto.CategoryDto;
import com.alexandru.springbootecommerce.dto.ProductDto;
import com.alexandru.springbootecommerce.entity.Category;
import com.alexandru.springbootecommerce.entity.Product;
import com.alexandru.springbootecommerce.exceptions.CategoryNotFoundException;
import com.alexandru.springbootecommerce.exceptions.ProductNotFound;
import com.alexandru.springbootecommerce.repository.CategoryRepository;
import com.alexandru.springbootecommerce.repository.ProductRepository;
import com.alexandru.springbootecommerce.service.ProductService;
import com.alexandru.springbootecommerce.util.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private static final Long DEFAULT_CATEGORY = 15L;

    @Transactional
    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Set<Category> categories = getCategories(productDto);
        if(categories.isEmpty()){
            Category category = categoryRepository.findById(DEFAULT_CATEGORY).orElseThrow(()-> new CategoryNotFoundException("Category with id " + DEFAULT_CATEGORY + " was not found"));
            categories.add(category);
        }

        Product product = buildProduct(productDto);
        addCategories(categories,product);

        return ProductDto.fromProduct(productRepository.save(product));
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductDto.fromProduct(productRepository.findById(id).orElseThrow(()-> new ProductNotFound("Product with id " + id + " was not found")));
    }

    @Transactional
    @Override
    public boolean deleteProductById(Long id) {
        getProductById(id);
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDto::fromProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto product) {
        Product updatedProduct = buildProduct(product);
        Set<Category> categories = getCategories(product);
        addCategories(categories, updatedProduct);
        return ProductDto.fromProduct(productRepository.save(updatedProduct));
    }

    private Product buildProduct(ProductDto request){
        return Product.builder()
                .productId(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .availableQuantity(request.getAvailableQuantity())
                .build();
    }

    private Set<Category> getCategories(ProductDto productDto){
        return productDto.getCategories().stream()
                .map(CategoryMapper::mapDtoToCategory)
                .collect(Collectors.toSet());
    }

    private void addCategories(Set<Category> categories, Product product){
        for(Category category : categories){
            product.addCategory(category);
        }
    }
}
