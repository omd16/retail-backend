package com.retail.product_service.mapper;

import com.retail.product_service.dto.request.CategoryRequestDto;
import com.retail.product_service.dto.response.CategoryResponseDto;
import com.retail.product_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryResponseDto map(Category category);

  Category map(CategoryRequestDto dto);

  void update(@MappingTarget Category category, CategoryRequestDto dto);
}
