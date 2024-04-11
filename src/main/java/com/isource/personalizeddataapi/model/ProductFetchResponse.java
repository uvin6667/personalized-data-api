package com.isource.personalizeddataapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductFetchResponse {
    private List<Product> products;
    private String responseStatus;
}
