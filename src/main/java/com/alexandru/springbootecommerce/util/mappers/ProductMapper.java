package com.alexandru.springbootecommerce.util.mappers;

import com.alexandru.springbootecommerce.dto.CategoryDto;
import com.alexandru.springbootecommerce.dto.ProductDto;
import com.alexandru.springbootecommerce.entity.Product;

import java.util.stream.Collectors;

public class ProductMapper {

    public static Product mapDtoToProduct(ProductDto product){
        return Product.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categories(product.getCategories().stream()
                        .map(CategoryMapper::mapDtoToCategory)
                        .collect(Collectors.toSet()))
                .build();
    }
}
