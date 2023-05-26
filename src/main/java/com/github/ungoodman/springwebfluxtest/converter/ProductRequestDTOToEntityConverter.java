package com.github.ungoodman.springwebfluxtest.converter;

import com.github.ungoodman.springwebfluxtest.model.dto.request.ProductRequestDTO;
import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import com.github.ungoodman.springwebfluxtest.model.enums.StatusCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductRequestDTOToEntityConverter implements Converter<ProductRequestDTO, Product> {
    @Override
    public Product convert(ProductRequestDTO source) {
        Product destination = new Product();

        if (!Objects.isNull(source.getId()) && !source.getId().isBlank())
            destination.setId(UUID.fromString(source.getId()));

        destination.setProductCode(source.getProductCode());
        destination.setProductName(source.getProductName());
        destination.setCreatedBy(source.getActionBy());
        destination.setStatusCode(StatusCode.INACTIVE.getCode());
        destination.setCreatedTime(LocalDateTime.now());
        destination.setAmount(source.getAmount());

        return destination;
    }
}
