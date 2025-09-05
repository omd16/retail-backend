package com.retail.product_service.service;

import com.retail.product_service.dto.request.CategoryRequestDto;
import com.retail.product_service.dto.response.CategoryResponseDto;
import com.retail.product_service.entity.Category;
import com.retail.product_service.exception.NotFoundException;
import com.retail.product_service.mapper.CategoryMapper;
import com.retail.product_service.repository.CategoryRepository;
import com.retail.product_service.util.UrlStringfyUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryResponseDto getCategory(Long id){
        return categoryRepository.findById(id).map(categoryMapper::map)
                .orElseThrow(() -> new NotFoundException("Could not found product"));
    }

    public Page<CategoryResponseDto> getAll(Pageable page){
        return categoryRepository.findAll(page).map(categoryMapper::map);
    }

    public CategoryResponseDto save(CategoryRequestDto dto){
        var category = categoryMapper.map(dto);
        setUrlSlug(category);
        return categoryMapper.map(categoryRepository.save(category));
    }

    public CategoryResponseDto update(CategoryRequestDto dto, Long id){
        var category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("No category found for given id"));
        categoryMapper.update(category, dto);

        return categoryMapper.map(categoryRepository.save(category));
    }

    public void softDelete(Long id){
        var product = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("No product found for given id"));
        product.setIsActive(false);
        categoryRepository.save(product);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    private void setUrlSlug(Category category){
        category.setUrlSlug(UrlStringfyUtil.toUrlSlug(category.getName(), 40));
    }

}
