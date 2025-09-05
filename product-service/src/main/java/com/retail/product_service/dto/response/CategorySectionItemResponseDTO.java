package com.retail.product_service.dto.response;

import com.retail.product_service.dto.common.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySectionItemResponseDTO {
    private Long id;
    private String title;
    private String iconUrl;
    private CategoryDto category;
}
