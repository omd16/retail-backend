package com.retail.product_service.util;

import java.text.Normalizer;

public class UrlStringfyUtil {

  public static String toUrlSlug(String value, Integer maxLength) {
    if (value == null) return null;

    String slug =
        Normalizer.normalize(value, Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]+", "") // Remove accents
            .toLowerCase()
            .replaceAll("[^a-z0-9\\s-]", "") // Remove non-alphanumeric except space and hyphen
            .replaceAll("\\s+", "-") // Replace spaces with -
            .replaceAll("-+", "-") // Collapse multiple hyphens
            .replaceAll("^-|-$", "")
            .substring(0, maxLength);
    return slug;
  }
}
