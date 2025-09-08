package com.retail.retail_gateway.exception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException {

  private final int status;

  public LoginException(String message, int status) {
    super(message);
    this.status = status;
  }
}
