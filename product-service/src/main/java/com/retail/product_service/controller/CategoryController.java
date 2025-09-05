package com.retail.product_service.controller;

import com.retail.product_service.dto.request.CategoryRequestDto;
import com.retail.product_service.dto.response.CategoryResponseDto;
import com.retail.product_service.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/")
    public ResponseEntity<Page<CategoryResponseDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(categoryService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody CategoryRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.update(dto, id));
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<Void> softDeleteCategory(@PathVariable Long id) {
        categoryService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
