package com.github.ungoodman.springwebfluxtest.model.dto.request;

import com.github.ungoodman.springwebfluxtest.model.enums.Action;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class ProductRequestDTO {
    private String id;

    private String productName;

    private String productCode;

    private Integer amount;

    private Action action;

    @NonNull
    private String actionBy;
}
