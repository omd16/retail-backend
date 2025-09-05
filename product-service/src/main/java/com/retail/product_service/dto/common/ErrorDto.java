package com.retail.product_service.dto.common;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

  private String code;

  private String message;

  private String details;
}
