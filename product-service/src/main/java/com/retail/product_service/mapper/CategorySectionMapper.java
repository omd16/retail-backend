package com.retail.product_service.mapper;

import com.retail.product_service.dto.request.CategorySectionItemRequestDTO;
import com.retail.product_service.dto.request.CategorySectionRequestDTO;
import com.retail.product_service.dto.response.CategorySectionItemResponseDTO;
import com.retail.product_service.dto.response.CategorySectionResponseDTO;
import com.retail.product_service.entity.Category;
import com.retail.product_service.entity.CategorySection;
import com.retail.product_service.entity.CategorySectionItem;
import com.retail.product_service.mapper.context.CategorySectionMapperContext;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategorySectionMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "items", source = "items")
  CategorySection toEntity(
      CategorySectionRequestDTO dto,
      List<CategorySectionItem> items,
      @Context CategorySectionMapperContext ctx);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "isActive", source = "dto.isActive")
  CategorySectionItem toItemEntity(CategorySectionItemRequestDTO dto, Category category);

  CategorySectionResponseDTO toDto(CategorySection section);

  CategorySectionItemResponseDTO toItemDto(CategorySectionItem item);

  List<CategorySectionItemResponseDTO> toItemDtoList(List<CategorySectionItem> items);

  @Mapping(target = "items", source = "items")
  void updateSection(
      @MappingTarget CategorySection categorySection,
      CategorySectionRequestDTO dto,
      List<CategorySectionItem> items,
      @Context CategorySectionMapperContext ctx);
}
