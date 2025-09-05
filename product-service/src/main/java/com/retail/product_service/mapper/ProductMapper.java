package com.retail.product_service.mapper;

import com.retail.product_service.dto.common.ProductMediaDto;
import com.retail.product_service.dto.request.ProductRequestDto;
import com.retail.product_service.dto.response.ProductResponseDto;
import com.retail.product_service.entity.Product;
import com.retail.product_service.entity.ProductMedia;
import com.retail.product_service.mapper.context.ProductMapperContext;
import java.util.List;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring",
    uses = {CategoryMapper.class, JsonNullableMappingStrategy.class})
public interface ProductMapper {

  MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

  ProductResponseDto map(Product product);

  List<ProductResponseDto> mapList(List<Product> product);

  Product map(ProductRequestDto dto, @Context ProductMapperContext productMapperContext);

  List<Product> mapList(
      List<ProductRequestDto> dto, @Context ProductMapperContext productMapperContext);

  @Mapping(target = "id", ignore = true)
  void update(
      @MappingTarget Product product,
      ProductRequestDto dto,
      @Context ProductMapperContext productMapperContext);

  @Mapping(target = "id", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "media", source = "media", qualifiedByName = "patchMediaList")
  void patch(
      @MappingTarget Product product, ProductRequestDto dto, @Context Product productContext);

  @Named(value = "patchMediaList")
  default List<ProductMedia> patchMediaList(
      List<ProductMediaDto> dtoList,
      @MappingTarget List<ProductMedia> entityList,
      @Context Product product) {
    if (dtoList == null) return entityList;

    GenericPatchMerger.mergeByKey(
        entityList,
        dtoList,
        ProductMedia::getId,
        ProductMediaDto::getId,
        mediaMapper::patch,
        dto -> mediaMapper.toEntity(dto, product));

    return entityList;
  }
}
