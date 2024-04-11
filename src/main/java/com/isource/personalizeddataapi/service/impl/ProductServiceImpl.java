package com.isource.personalizeddataapi.service.impl;

import com.isource.personalizeddataapi.model.PersonalisedProductDetail;
import com.isource.personalizeddataapi.model.PersonalisedProductList;
import com.isource.personalizeddataapi.model.Product;
import com.isource.personalizeddataapi.entity.ShelfItem;
import com.isource.personalizeddataapi.entity.Shopper;
import com.isource.personalizeddataapi.repository.ProductRepository;
import com.isource.personalizeddataapi.repository.ShelfItemRepository;
import com.isource.personalizeddataapi.repository.ShopperRepository;
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
    private ShopperRepository shopperRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> getProductsByShopper(String shopperId, String category, String brand, int limit) {
        return shelfItemRepository.getProductsByShopper(shopperId,category,brand,limit);
    }

    @Override
    public Product saveProduct(Product productDto) {
        return modelMapper.map(productRepository.save(modelMapper.map(productDto, com.isource.personalizeddataapi.entity.Product.class)), Product.class);
    }

    @Override
    public boolean savePPL(PersonalisedProductList personalisedProductList) {
        try {
            String shopperId = personalisedProductList.getShopperId();
            Shopper shopper = shopperRepository.findByShopperId(shopperId);
            if (shopper == null){
                shopper = shopperRepository.save(new Shopper(shopperId));
            }
            for (PersonalisedProductDetail personalisedProductDetail : personalisedProductList.getShelf()) {
                com.isource.personalizeddataapi.entity.Product product = productRepository.findByProductId(personalisedProductDetail.getProductId());
                ShelfItem shelfItem = new ShelfItem(shopper, product, personalisedProductDetail.getRelevancyScore());
                shelfItemRepository.save(shelfItem);
            }
            return true;
        } catch (Exception e){
            return false;
        }

    }

}
