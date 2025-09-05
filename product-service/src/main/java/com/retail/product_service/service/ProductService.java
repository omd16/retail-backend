package com.retail.product_service.service;

import com.retail.product_service.dto.common.ProductSearchParameter;
import com.retail.product_service.dto.request.ProductRequestDto;
import com.retail.product_service.dto.response.ProductResponseDto;
import com.retail.product_service.entity.Product;
import com.retail.product_service.exception.NotFoundException;
import com.retail.product_service.mapper.ProductMapper;
import com.retail.product_service.mapper.context.ProductMapperContext;
import com.retail.product_service.repository.CategoryRepository;
import com.retail.product_service.repository.ProductRepository;
import com.retail.product_service.repository.specs.ProductSpecMapper;
import com.retail.product_service.util.UrlStringfyUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;
  private final ProductSpecMapper productSpecMapper;

  public ProductService(
      ProductRepository productRepository,
      CategoryRepository categoryRepository,
      ProductMapper productMapper,
      ProductSpecMapper productSpecMapper) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.productMapper = productMapper;
    this.productSpecMapper = productSpecMapper;
  }

  public ProductResponseDto getProduct(Long id) {
    return productRepository
        .findById(id)
        .map(productMapper::map)
        .orElseThrow(() -> new NotFoundException("Could not found product"));
  }

  public Page<ProductResponseDto> getAll(ProductSearchParameter parameter, Pageable page) {
    var spec = productSpecMapper.map(parameter);
    return productRepository.findAll(spec, page).map(productMapper::map);
  }

  public ProductResponseDto save(ProductRequestDto dto) {
    var product = productMapper.map(dto, new ProductMapperContext());
    setCategories(dto.getCategory_ids(), product);
    setUrlSlug(product);
    return productMapper.map(productRepository.save(product));
  }

  public List<ProductResponseDto> saveAll(List<ProductRequestDto> dtos) {
    List<Product> products = new ArrayList<>();
    for (var dto : dtos) {
      var product = productMapper.map(dto, new ProductMapperContext());
      setCategories(dto.getCategory_ids(), product);
      setUrlSlug(product);
      products.add(product);
    }
    return productMapper.mapList(productRepository.saveAll(products));
  }

  public ProductResponseDto update(ProductRequestDto dto, Long id) {
    var product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("No product found for given id"));
    productMapper.update(product, dto, new ProductMapperContext());
    setCategories(dto.getCategory_ids(), product);

    return productMapper.map(productRepository.save(product));
  }

  public ProductResponseDto patch(ProductRequestDto dto, Long id) {
    var product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("No product found for given id"));
    productMapper.patch(product, dto, product);
    setCategories(dto.getCategory_ids(), product);

    return productMapper.map(productRepository.save(product));
  }

  public void softDelete(Long id) {
    var product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("No product found for given id"));
    product.setIsActive(false);
    productRepository.save(product);
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  private void setCategories(List<Long> categoryIds, Product product) {
    if (categoryIds != null) {
      product.setCategories(categoryRepository.findAllById(categoryIds));
    }
  }

  private void setUrlSlug(Product product) {
    product.setUrlSlug(UrlStringfyUtil.toUrlSlug(product.getName(), 40));
  }
}
