package com.retail.product_service.controller;

import com.retail.product_service.dto.common.ProductSearchParameter;
import com.retail.product_service.dto.request.ProductRequestDto;
import com.retail.product_service.dto.response.ProductResponseDto;
import com.retail.product_service.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(1000); // TODO: remove later just added to mimic latency
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAll(ProductSearchParameter parameter, Pageable pageable) throws InterruptedException {
        Thread.sleep(2000); // TODO: remove later just added to mimic latency
        return ResponseEntity.ok(productService.getAll(parameter, pageable));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto) {
        return ResponseEntity.ok(productService.save(dto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> createAllProduct(@RequestBody List<ProductRequestDto> dto) {
        return ResponseEntity.ok(productService.saveAll(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(productService.update(dto, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(@Validated(ProductRequestDto.OnProductPatch.class) @RequestBody ProductRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(productService.patch(dto, id));
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<Void> softDeleteProduct(@PathVariable Long id) {
        productService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}