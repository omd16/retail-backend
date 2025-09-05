package com.retail.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

  private Long id;

  private String name;

  private String description;

  private String urlSlug;

  private Boolean isActive;
}
