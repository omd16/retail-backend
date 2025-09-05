package com.retail.product_service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMediaDto {

    private Long id;

    private String url;

    private String type;

    private String altText;

    private boolean isPrimary = false;

    private int sortOrder = 0;

    private boolean isActive;
}
