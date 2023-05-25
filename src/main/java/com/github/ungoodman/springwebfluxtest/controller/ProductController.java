package com.github.ungoodman.springwebfluxtest.controller;

import com.github.ungoodman.springwebfluxtest.dto.ProductDTO;
import com.github.ungoodman.springwebfluxtest.entity.Product;
import com.github.ungoodman.springwebfluxtest.service.command.ProductCommandService;
import com.github.ungoodman.springwebfluxtest.service.query.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @Autowired
    private ProductQueryService queryService;

    @Autowired
    private ProductCommandService commandService;

    @GetMapping("/")
    public Flux<Product> getAllProducts() {
        return queryService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") String id) {
        return queryService.findById(id);
    }

    @GetMapping("/{code}")
    public Mono<Product> getProductByCode(@PathVariable("code") String code) {
        return queryService.findByCode(code);
    }

    @PostMapping("/")
    public Mono<Product> createProduct(@RequestBody ProductDTO productDTO) {
        return commandService.createNewProduct(productDTO);
    }

}
