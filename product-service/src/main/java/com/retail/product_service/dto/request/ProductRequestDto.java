package com.retail.product_service.dto.request;

import com.retail.product_service.dto.common.ProductMediaDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    public interface OnProductCreate {}
    public interface OnProductPatch {}

    private Long id;

    @NotNull(groups = OnProductCreate.class)
    private JsonNullable<String> name;

    private JsonNullable<String> description;

    private JsonNullable<BigDecimal> salePrice;

    private JsonNullable<BigDecimal> originalPrice;

    private JsonNullable<Integer> stock;

    private JsonNullable<Boolean> isActive;

    private List<Long> category_ids;

    private List<ProductMediaDto> media;

}