package com.retail.product_service.repository.specs;

import com.retail.product_service.dto.common.ProductSearchParameter;
import com.retail.product_service.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductSpecMapper {

    public Specification<Product> map(ProductSearchParameter parameter){
        List<Specification> specs = new ArrayList<>();
        Optional.ofNullable(parameter).map(ProductSearchParameter::getCategory).map(ProductSpecification::category).ifPresent(specs::add);

        return specs.stream().reduce(Specification::and).orElse(null);
    }
}
