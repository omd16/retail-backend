package com.retail.product_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class Auditable<U> {

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  protected U createdBy;

  @CreatedDate
  @Column(name = "created_on", updatable = false)
  protected LocalDateTime createdOn;

  @LastModifiedBy
  @Column(name = "updated_by")
  protected U updatedBy;

  @LastModifiedDate
  @Column(name = "updated_on")
  protected LocalDateTime updatedOn;
}
