package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;

    public static CategoryDto fromCategory(Category category){
        return CategoryDto.builder()
                .id(category.getCategoryId())
                .name(category.getName())
                .build();
    }
}
