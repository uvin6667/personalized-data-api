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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ShelfItemRepository shelfItemRepository;
    private final ProductRepository productRepository;
    private final ShopperRepository shopperRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ShelfItemRepository shelfItemRepository, ProductRepository productRepository, ShopperRepository shopperRepository, ModelMapper modelMapper) {
        this.shelfItemRepository = shelfItemRepository;
        this.productRepository = productRepository;
        this.shopperRepository = shopperRepository;
        this.modelMapper = modelMapper;
    }

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
            String shopperId = personalisedProductList.shopperId();
            Shopper shopper = shopperRepository.findByShopperId(shopperId);
            if (shopper == null){
                shopper = shopperRepository.save(new Shopper(shopperId));
            }
            HashSet<ShelfItem> shelfItems = shelfItemRepository.findByShopper(shopper);
            List<String> productIds = shelfItems.stream().map(si -> si.getProduct().getProductId()).toList();
            for (PersonalisedProductDetail personalisedProductDetail : personalisedProductList.shelf()) {
                com.isource.personalizeddataapi.entity.Product product = productRepository.findByProductId(personalisedProductDetail.productId());
                boolean containsProduct = productIds.contains(personalisedProductDetail.productId());
                if(!containsProduct){
                    ShelfItem shelfItem = new ShelfItem(shopper, product, personalisedProductDetail.relevancyScore());
                    shelfItemRepository.save(shelfItem);
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }

    }

}
