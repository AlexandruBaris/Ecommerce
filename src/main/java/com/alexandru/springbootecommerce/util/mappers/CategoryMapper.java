package com.alexandru.springbootecommerce.util.mappers;

import com.alexandru.springbootecommerce.dto.CategoryDto;
import com.alexandru.springbootecommerce.entity.Category;

public class CategoryMapper {

    public static Category mapDtoToCategory(CategoryDto category){
        return Category.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .build();
    }
}
