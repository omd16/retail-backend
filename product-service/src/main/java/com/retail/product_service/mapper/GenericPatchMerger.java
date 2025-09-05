package com.retail.product_service.mapper;

import java.util.*;
import java.util.function.*;

public class GenericPatchMerger {

  public static <KEY_TYPE, ENTITY, DTO> void mergeByKey(
      Collection<ENTITY> existingEntities,
      Collection<DTO> incomingDtos,
      Function<ENTITY, KEY_TYPE> getEntityKey,
      Function<DTO, KEY_TYPE> getDtoKey,
      BiConsumer<ENTITY, DTO> patchEntity,
      Function<DTO, ENTITY> createNewEntity // ✅ FIXED
      ) {
    Map<KEY_TYPE, ENTITY> entityMap = new HashMap<>();
    for (ENTITY entity : existingEntities) {
      entityMap.put(getEntityKey.apply(entity), entity);
    }

    Set<KEY_TYPE> incomingKeys = new HashSet<>();
    List<ENTITY> toAdd = new ArrayList<>();

    for (DTO dto : incomingDtos) {
      KEY_TYPE key = getDtoKey.apply(dto);
      incomingKeys.add(key);

      ENTITY existing = entityMap.get(key);
      if (existing != null) {
        patchEntity.accept(existing, dto);
      } else {
        toAdd.add(createNewEntity.apply(dto)); // ✅ Now valid
      }
    }

    // Remove entries not present in incoming list
    existingEntities.removeIf(entity -> !incomingKeys.contains(getEntityKey.apply(entity)));

    // Add new entries
    existingEntities.addAll(toAdd);
  }
}
