package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String img;
    private Integer availableQuantity;
    private Set<CategoryDto> categories = new HashSet<>();

    public static ProductDto fromProduct(Product product){
        ProductDto productDto = ProductDto.builder()
                .id(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .img(product.getImg())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .build();

        if(Objects.nonNull(product.getCategories())){
            productDto.setCategories(product.getCategories().stream()
                    .map(CategoryDto::fromCategory)
                    .collect(Collectors.toSet()));
        }

        return productDto;
    }

}
