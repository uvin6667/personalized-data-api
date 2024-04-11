package com.isource.personalizeddataapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSaveResponse {
    private Product product;
    private String responseStatus;

    public ProductSaveResponse(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
