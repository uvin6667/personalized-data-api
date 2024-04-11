package com.isource.personalizeddataapi.model;

import com.isource.personalizeddataapi.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponse {
    private List<ProductDto> products;
    private String responseStatus;
}
