package com.github.ungoodman.springwebfluxtest.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="t_product")
public class Product {
    @Id
    @Column("id")
    private UUID id;

    @Column("nm")
    private String productName;

    @Column("cd")
    private String productCode;

    @Column("amount")
    private Integer amount;

    @Column("crt_time")
    private LocalDateTime createdTime;

    @Column("crt_by")
    private String createdBy;

    @Column("upd_time")
    private LocalDateTime updatedTime;

    @Column("upd_by")
    private String updatedBy;
}
