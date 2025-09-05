package com.retail.product_service.dto.response;

import com.retail.product_service.dto.common.ProductMediaDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {

  private Long id;
  private String name;
  private String description;
  private BigDecimal originalPrice;
  private BigDecimal salePrice;
  private Integer stock;
  private Boolean isActive;
  private LocalDateTime createdOn;
  private String urlSlug;
  private List<CategoryResponseDto> categories;
  private List<ProductMediaDto> media;
}
