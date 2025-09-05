package com.retail.product_service.dto.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySectionRequestDTO {
    private String title;
    private List<CategorySectionItemRequestDTO> items;
}
