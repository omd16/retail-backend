package com.retail.user_service.context;

import java.util.UUID;

public class UserContext {

  private static final ThreadLocal<UUID> userIdHolder = new ThreadLocal<>();

  private UserContext() {
    // Prevent instantiation
  }

  // Get userId
  public static UUID getUserId() {
    return userIdHolder.get();
  }

  // Set userId (from header)
  public static void setUserId(UUID userId) {
    userIdHolder.set(userId);
  }

  // Clear after request
  public static void clear() {
    userIdHolder.remove();
  }
}
