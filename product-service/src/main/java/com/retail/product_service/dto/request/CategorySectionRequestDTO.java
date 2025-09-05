package com.retail.product_service.dto.request;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySectionRequestDTO {
  private String title;
  private List<CategorySectionItemRequestDTO> items;
}
