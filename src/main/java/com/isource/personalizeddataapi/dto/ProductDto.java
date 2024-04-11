package com.isource.personalizeddataapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private String productId;
    private String category;
    private String brand;

}
