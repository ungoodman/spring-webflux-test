package com.github.ungoodman.springwebfluxtest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private String id;

    @NonNull
    private String productName;

    @NonNull
    private String productCode;

    private Integer amount;

    private LocalDateTime createdTime;

    private String createdBy;

    private LocalDateTime updatedTime;

    private String updatedBy;
}
