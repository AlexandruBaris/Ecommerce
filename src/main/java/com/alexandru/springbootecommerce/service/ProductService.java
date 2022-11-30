package com.alexandru.springbootecommerce.service;

import com.alexandru.springbootecommerce.dto.ProductDto;
import com.alexandru.springbootecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto product);
    ProductDto getProductById(Long id);
    boolean deleteProductById(Long id);
    List<ProductDto> findAllProducts();
    ProductDto updateProduct(ProductDto product);

}
