package com.retail.product_service.mapper.context;

import com.retail.product_service.entity.CategorySection;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

public class CategorySectionMapperContext {

    private CategorySection categorySection;

    @BeforeMapping
    public void setCategorySection(@MappingTarget CategorySection section){
        this.categorySection = section;
    }

    @AfterMapping
    public void addCategorySection(@MappingTarget CategorySection section){
        section.getItems().forEach(item -> item.setCategorySection(this.categorySection));
    }

    public CategorySection getCategorySection(){
        return this.categorySection;
    }

}
