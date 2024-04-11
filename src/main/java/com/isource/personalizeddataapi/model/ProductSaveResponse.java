package com.isource.personalizeddataapi.model;

import com.isource.personalizeddataapi.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSaveResponse {
    private ProductDto product;
    private String responseStatus;

    public ProductSaveResponse(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
