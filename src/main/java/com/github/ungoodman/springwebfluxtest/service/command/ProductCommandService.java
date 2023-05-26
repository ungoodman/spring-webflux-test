package com.github.ungoodman.springwebfluxtest.service.command;

import com.github.ungoodman.springwebfluxtest.converter.ProductEntityToResponseDTOConverter;
import com.github.ungoodman.springwebfluxtest.model.dto.response.ProductResponseDTO;
import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import com.github.ungoodman.springwebfluxtest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Transactional
public class ProductCommandService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductEntityToResponseDTOConverter entityToResponseDTOConverter;

    public Mono<ProductResponseDTO> saveProduct(Product productToSave) {
        return repository.save(productToSave).map(product -> entityToResponseDTOConverter.convert(product));
    }
}
