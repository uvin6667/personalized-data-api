package com.isource.personalizeddataapi.service;

import com.isource.personalizeddataapi.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductsByShopper(String shopperId, String category, String brand);

    ProductDto saveProduct(ProductDto productDto);
}
