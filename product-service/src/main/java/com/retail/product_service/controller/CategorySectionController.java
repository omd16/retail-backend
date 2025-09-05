package com.retail.product_service.controller;

import com.retail.product_service.dto.request.CategorySectionRequestDTO;
import com.retail.product_service.dto.response.CategorySectionResponseDTO;
import com.retail.product_service.service.CategorySectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product/sections")
@RequiredArgsConstructor
public class CategorySectionController {

  private final CategorySectionService sectionService;

  @PostMapping
  public ResponseEntity<CategorySectionResponseDTO> createSection(
      @RequestBody CategorySectionRequestDTO requestDTO) {
    var response = sectionService.createSection(requestDTO);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategorySectionResponseDTO> updateSection(
      @RequestBody CategorySectionRequestDTO requestDTO, @PathVariable Long id) {
    var response = sectionService.updateSection(requestDTO, id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategorySectionResponseDTO> getSection(@PathVariable Long id) {
    return ResponseEntity.ok(sectionService.getSection(id));
  }

  @GetMapping
  public ResponseEntity<Page<CategorySectionResponseDTO>> getAllSections(Pageable pageable) {
    return ResponseEntity.ok(sectionService.getAllSection(pageable));
  }

  @PutMapping("/soft-delete/{id}")
  public ResponseEntity<Void> softDelete(@PathVariable Long id) {
    sectionService.softDelete(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    sectionService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
