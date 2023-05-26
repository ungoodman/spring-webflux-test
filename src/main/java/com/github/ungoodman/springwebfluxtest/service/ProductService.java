package com.github.ungoodman.springwebfluxtest.service;

import com.github.ungoodman.springwebfluxtest.converter.ProductEntityToResponseDTOConverter;
import com.github.ungoodman.springwebfluxtest.converter.ProductRequestDTOToEntityConverter;
import com.github.ungoodman.springwebfluxtest.exception.GenericBadException;
import com.github.ungoodman.springwebfluxtest.model.dto.request.ProductRequestDTO;
import com.github.ungoodman.springwebfluxtest.model.dto.response.ProductResponseDTO;
import com.github.ungoodman.springwebfluxtest.model.entity.Product;
import com.github.ungoodman.springwebfluxtest.model.enums.Action;
import com.github.ungoodman.springwebfluxtest.model.enums.StatusCode;
import com.github.ungoodman.springwebfluxtest.service.command.ProductCommandService;
import com.github.ungoodman.springwebfluxtest.service.query.ProductQueryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductCommandService commandService;

    @Autowired
    private ProductQueryService queryService;

    @Autowired
    private ProductRequestDTOToEntityConverter requestDTOToEntityConverter;

    @Autowired
    private ProductEntityToResponseDTOConverter entityToResponseDTOConverter;

    public Flux<ProductResponseDTO> retrieveAllProducts() {
        return queryService.findAll()
                .map(entityToResponseDTOConverter::convert);
    }

    public Mono<ProductResponseDTO> findById(String id) {
        return queryService.findById(id)
                .map(entityToResponseDTOConverter::convert);
    }

    public Mono<ProductResponseDTO> createNewProduct(ProductRequestDTO requestDTO) {
        validateNewProduct(requestDTO);

        return commandService.saveProduct(
                requestDTOToEntityConverter.convert(requestDTO))
                .map(entityToResponseDTOConverter::convert);
    }

    public Mono<ProductResponseDTO> performAction(String id, Action action, ProductRequestDTO requestDTO) {
        requestDTO.setId(id);

        validateMandatory(requestDTO);

        if (Action.SAVE.equals(action))
            return saveDraft(requestDTO);

        throw new GenericBadException("Invalid Action");
    }

    private Mono<ProductResponseDTO> saveDraft(ProductRequestDTO requestDTO) {
        return queryService.findById(requestDTO.getId(), StatusCode.INACTIVE.getCode())
                .flatMap(product -> {
                    if (Objects.isNull(product) || Objects.isNull(product.getId()))
                        throw new GenericBadException("Product not found!, Product ID: " + requestDTO.getId());

                    return commandService.saveProduct(
                            updateData(product, requestDTO)
                    ).map(entityToResponseDTOConverter::convert);
                });
    }

    private Product updateData(Product product, ProductRequestDTO requestDTO) {
        ModelMapper mapper = new ModelMapper();
        Product newProduct = mapper.map(product, Product.class);

        if (!Objects.isNull(requestDTO.getProductName())
                && requestDTO.getProductName().isBlank())
            newProduct.setProductName(requestDTO.getProductName());

        if (!Objects.isNull(requestDTO.getProductCode())
                && requestDTO.getProductCode().isBlank())
            newProduct.setProductCode(requestDTO.getProductCode());

        if (!Objects.isNull(requestDTO.getAmount()))
            newProduct.setAmount(requestDTO.getAmount());

        newProduct.setUpdatedBy(requestDTO.getActionBy());
        newProduct.setUpdatedTime(LocalDateTime.now());
        newProduct.setVersion(product.getVersion() + 1);

        return newProduct;
    }

    private void validateNewProduct(ProductRequestDTO requestDTO) {
        if (!Objects.isNull(requestDTO.getProductName()) && requestDTO.getProductName().isBlank())
            throw new GenericBadException("Product Name is mandatory!");

        if (!Objects.isNull(requestDTO.getProductCode()) && requestDTO.getProductCode().isBlank())
            throw new GenericBadException("Product Code is mandatory!");

        validateMandatory(requestDTO);
    }

    private void validateMandatory(ProductRequestDTO requestDTO) {
        if (requestDTO.getId().isBlank())
            throw new GenericBadException("ID is mandatory!");

        if (requestDTO.getActionBy().isBlank())
            throw new GenericBadException("Action By is mandatory!");
    }
}
