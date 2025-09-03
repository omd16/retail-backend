package com.retail.user_service.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserAddress {

  @NotBlank(message = "Address type is required (e.g., HOME, WORK)")
  private String addressType;

  @NotBlank(message = "Full name is required")
  private String fullName;

  @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
  private String phoneNumber;

  @NotBlank(message = "Address Line 1 is required")
  private String addressLine1;

  private String addressLine2;

  @NotBlank private String city;

  @NotBlank private String state;

  @NotBlank private String postalCode;

  @NotBlank private String country;

  private boolean isDefault;
}
