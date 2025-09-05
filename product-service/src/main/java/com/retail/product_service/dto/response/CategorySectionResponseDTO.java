package com.retail.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySectionResponseDTO {
    private Long id;
    private String title;
    private List<CategorySectionItemResponseDTO> items;
}
