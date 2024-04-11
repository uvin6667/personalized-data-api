package com.isource.personalizeddataapi.service.impl;

import com.isource.personalizeddataapi.dto.ProductDto;
import com.isource.personalizeddataapi.entity.Product;
import com.isource.personalizeddataapi.repository.ProductRepository;
import com.isource.personalizeddataapi.repository.ShelfItemRepository;
import com.isource.personalizeddataapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ShelfItemRepository shelfItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> getProductsByShopper(String shopperId, String category, String brand) {
        return shelfItemRepository.getProductsByShopper(shopperId,category,brand);
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        return modelMapper.map(productRepository.save(modelMapper.map(productDto,Product.class)),ProductDto.class);
    }

}
