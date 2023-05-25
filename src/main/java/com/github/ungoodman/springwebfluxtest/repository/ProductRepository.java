package com.github.ungoodman.springwebfluxtest.repository;

import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Serializable> {
    Mono<Product> findByProductCode(String code);
}
