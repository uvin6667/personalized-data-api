package com.isource.personalizeddataapi.service.impl;

import com.isource.personalizeddataapi.dto.ProductDto;
import com.isource.personalizeddataapi.repository.ProductRepository;
import com.isource.personalizeddataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getProductsByShopper(String shopperId, String category, String brand) {
        return productRepository.getProductsByShopper(shopperId,category,brand);
    }
}
