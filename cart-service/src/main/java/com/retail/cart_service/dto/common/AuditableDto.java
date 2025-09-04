package com.retail.cart_service.dto.common;

import java.time.LocalDateTime;

public abstract class AuditableDto<U> {

  protected U createdBy;

  protected LocalDateTime createdOn;

  protected U updatedBy;

  protected LocalDateTime updatedOn;
}
