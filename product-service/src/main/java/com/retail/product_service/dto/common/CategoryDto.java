package com.retail.product_service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private Boolean isActive;

    private String urlSlug;
}
