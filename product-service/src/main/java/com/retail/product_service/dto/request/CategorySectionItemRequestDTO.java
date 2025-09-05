package com.retail.product_service.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySectionItemRequestDTO {
  private Long categoryId;
  private String title;
  private String iconUrl;
  private Boolean isActive;
}
