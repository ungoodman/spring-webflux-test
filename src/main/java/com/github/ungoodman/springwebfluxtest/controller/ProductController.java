package com.github.ungoodman.springwebfluxtest.controller;

import com.github.ungoodman.springwebfluxtest.model.dto.request.ProductRequestDTO;
import com.github.ungoodman.springwebfluxtest.model.dto.response.ProductResponseDTO;
import com.github.ungoodman.springwebfluxtest.model.enums.Action;
import com.github.ungoodman.springwebfluxtest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public Flux<ProductResponseDTO> getAllProducts() {
        return service.retrieveAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductResponseDTO> getProductById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public Mono<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO requestDTO) {
        return service.createNewProduct(requestDTO);
    }

    @PostMapping(value = "/{id}/{action}", consumes = "application/json")
    public Mono<ProductResponseDTO> performActionProduct(
            @PathVariable("id") String id,
            @PathVariable("action") Action action,
            @RequestBody ProductRequestDTO requestDTO
    ) {
        return service.performAction(id, action, requestDTO);
    }

}
