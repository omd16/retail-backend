package com.retail.user_service.exception;

public class CredentialNotValidException extends RuntimeException {

  public CredentialNotValidException(String message) {
    super(message);
  }
}
