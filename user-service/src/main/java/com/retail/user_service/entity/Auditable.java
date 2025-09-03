package com.retail.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class Auditable<U> {

  @CreatedDate
  @Column(name = "created_on", updatable = false)
  protected LocalDateTime createdOn;

  @LastModifiedDate
  @Column(name = "updated_on")
  protected LocalDateTime updatedOn;
}
