package com.github.ungoodman.springwebfluxtest.service.query;

import com.github.ungoodman.springwebfluxtest.entity.Product;
import com.github.ungoodman.springwebfluxtest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQueryService {
    @Autowired
    private ProductRepository repository;

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Mono<Product> findById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public Mono<Product> findByCode(String code) {
        return repository.findByProductCode(code);
    }
}
