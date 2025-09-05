package com.retail.product_service.mapper;

import com.retail.product_service.dto.common.ProductMediaDto;
import com.retail.product_service.entity.Product;
import com.retail.product_service.entity.ProductMedia;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {JsonNullableMappingStrategy.class})
public interface MediaMapper {

    ProductMediaDto map(ProductMedia category);

    ProductMedia map(ProductMediaDto dto);

    @Mapping(target = "id", ignore = true)
    ProductMedia toEntity(ProductMediaDto dto, Product product);


    ProductMedia patch(@MappingTarget ProductMedia productMedia, ProductMediaDto dto);
}
