package com.github.ungoodman.springwebfluxtest.service.command;

import com.github.ungoodman.springwebfluxtest.dto.ProductDTO;
import com.github.ungoodman.springwebfluxtest.entity.Product;
import com.github.ungoodman.springwebfluxtest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductCommandService {
    @Autowired
    private ProductRepository repository;

    public Mono<Product> createNewProduct(ProductDTO productDTO) {
        Product productToSave = new Product();

        productToSave.setProductCode(productDTO.getProductCode());
        productToSave.setProductName(productDTO.getProductName());
        productToSave.setCreatedBy("SYSTEM");
        productToSave.setCreatedTime(LocalDateTime.now());
        productToSave.setAmount(productDTO.getAmount());

        return repository.save(productToSave);
    }
}
