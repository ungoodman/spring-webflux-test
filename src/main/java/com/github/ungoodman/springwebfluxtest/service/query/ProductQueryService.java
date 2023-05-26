package com.github.ungoodman.springwebfluxtest.service.query;

import com.github.ungoodman.springwebfluxtest.converter.ProductEntityToResponseDTOConverter;
import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import com.github.ungoodman.springwebfluxtest.model.enums.StatusCode;
import com.github.ungoodman.springwebfluxtest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ProductQueryService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductEntityToResponseDTOConverter entityToResponseDTOConverter;

    public Flux<Product> findAll() {
        return repository.findByStatusCode(StatusCode.ACTIVE.getCode());
    }

    public Mono<Product> findById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public Mono<Product> findById(String id, String statusCode) {
        return repository.findByIdAndStatusCode(UUID.fromString(id), statusCode);
    }

    public Mono<Product> findByCode(String code) {
        return repository.findByProductCode(code);
    }
}
