package com.github.ungoodman.springwebfluxtest.repository;

import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.UUID;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Serializable> {
    Flux<Product> findByStatusCode(String statusCode);
    Mono<Product> findByIdAndStatusCode(UUID id, String statusCode);
    Mono<Product> findByProductCode(String code);
}
