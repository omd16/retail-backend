package com.retail.product_service.dto.common;

import java.time.LocalDateTime;

public abstract class AuditableDto<U> {

    protected U createdBy;

    protected LocalDateTime createdOn;

    protected U updatedBy;

    protected LocalDateTime updatedOn;
}
