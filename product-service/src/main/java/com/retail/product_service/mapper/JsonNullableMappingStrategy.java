package com.retail.product_service.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonNullableMappingStrategy {

    public <Z> Z unwrap(JsonNullable<Z> source) {
        return source.orElse(null);
    }

    public <Z> JsonNullable<Z> wrap(Z source) {
        if (source == null) {
            return JsonNullable.undefined();
        }

        return JsonNullable.of(source);
    }

    @Condition
    public boolean shouldMapNullable(JsonNullable<?> source) {
        return source != null && source.isPresent();
    }

    @BeforeMapping
    public <Z> List<Z> unwrapList(JsonNullable<List<Z>> source) {
        return source.orElse(null);
    }

}
