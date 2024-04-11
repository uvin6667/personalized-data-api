package com.isource.personalizeddataapi.model;

import com.isource.personalizeddataapi.dto.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private List<ProductDto> products;
}
