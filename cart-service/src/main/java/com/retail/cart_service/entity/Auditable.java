package com.retail.cart_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable<U> {

    @CreatedDate
    @Column(name = "created_on", updatable = false)
    protected LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "updated_on")
    protected LocalDateTime updatedOn;

}
