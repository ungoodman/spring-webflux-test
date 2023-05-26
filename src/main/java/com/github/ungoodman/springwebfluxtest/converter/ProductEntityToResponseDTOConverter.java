package com.github.ungoodman.springwebfluxtest.converter;

import com.github.ungoodman.springwebfluxtest.model.dto.response.ProductResponseDTO;
import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityToResponseDTOConverter implements Converter<Product, ProductResponseDTO> {
    @Override
    public ProductResponseDTO convert(Product source) {
        return ProductResponseDTO.builder()
                .id(source.getId())
                .productName(source.getProductName())
                .productCode(source.getProductCode())
                .amount(source.getAmount())
                .version(source.getVersion())
                .statusCode(source.getStatusCode())
                .createBy(source.getCreatedBy())
                .createTime(source.getCreatedTime())
                .updatedBy(source.getUpdatedBy())
                .updatedTime(source.getUpdatedTime())
                .build();
    }
}
