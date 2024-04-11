package com.isource.personalizeddataapi.service;

import com.isource.personalizeddataapi.model.PersonalisedProductList;
import com.isource.personalizeddataapi.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByShopper(String shopperId, String category, String brand);

    Product saveProduct(Product productDto);

    boolean savePPL(PersonalisedProductList personalisedProductList);
}
