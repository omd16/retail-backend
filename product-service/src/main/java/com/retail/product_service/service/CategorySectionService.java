package com.retail.product_service.service;

import com.retail.product_service.dto.request.CategorySectionRequestDTO;
import com.retail.product_service.dto.response.CategorySectionResponseDTO;
import com.retail.product_service.entity.CategorySectionItem;
import com.retail.product_service.exception.NotFoundException;
import com.retail.product_service.mapper.CategorySectionMapper;
import com.retail.product_service.mapper.context.CategorySectionMapperContext;
import com.retail.product_service.repository.CategoryRepository;
import com.retail.product_service.repository.CategorySectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategorySectionService {

    private final CategorySectionRepository sectionRepository;
    private final CategoryRepository categoryRepository;
    private final CategorySectionMapper mapper;

    public CategorySectionResponseDTO createSection(CategorySectionRequestDTO requestDTO) {
        var categorySection = mapper.toEntity(requestDTO,getCategorySectionItems(requestDTO), new CategorySectionMapperContext());
        return mapper.toDto(sectionRepository.save(categorySection));
    }

    public CategorySectionResponseDTO updateSection(CategorySectionRequestDTO requestDTO, Long id) {
        var section = sectionRepository.findById(id).orElseThrow();
        mapper.updateSection(section, requestDTO, getCategorySectionItems(requestDTO), new CategorySectionMapperContext());

        return mapper.toDto(sectionRepository.save(section));
    }

    public CategorySectionResponseDTO getSection(Long id) {
        return mapper.toDto(sectionRepository.findById(id).orElseThrow(() -> new NotFoundException("Section not found")));
    }

    public Page<CategorySectionResponseDTO> getAllSection(Pageable pageable) {
        return sectionRepository.findAll(pageable).map(mapper::toDto);
    }

    public void softDelete(Long id){
        var section = sectionRepository.findById(id).orElseThrow();
        section.setIsActive(false);
        sectionRepository.save(section);
    }

    public void delete(Long id){
        sectionRepository.deleteById(id);
    }


    private List<CategorySectionItem> getCategorySectionItems(CategorySectionRequestDTO requestDTO) {
        List<CategorySectionItem> items = new ArrayList<>();
        requestDTO.getItems().forEach(item -> {
            final var category = categoryRepository.findById(item.getCategoryId()).orElseThrow(() -> new NotFoundException("Category with id: %s is not found".formatted(item.getCategoryId())));
            items.add(mapper.toItemEntity(item, category));
        });
        return items;
    }
}
