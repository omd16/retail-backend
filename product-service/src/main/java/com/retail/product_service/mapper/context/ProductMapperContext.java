package com.retail.product_service.mapper.context;

import com.retail.product_service.entity.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

public class ProductMapperContext {

  private Product product;

  @AfterMapping
  public void addProduct(@MappingTarget Product product) {
    product.getMedia().forEach(media -> media.setProduct(this.product));
  }

  public Product getProduct() {
    return this.product;
  }

  @BeforeMapping
  public void setProduct(@MappingTarget Product product) {
    this.product = product;
  }
}
