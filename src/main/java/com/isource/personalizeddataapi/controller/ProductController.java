package com.isource.personalizeddataapi.controller;

import com.isource.personalizeddataapi.model.ProductResponse;
import com.isource.personalizeddataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<ProductResponse> getProductsByShopper(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        try{
            return ResponseEntity.ok(new ProductResponse(productService.getProductsByShopper(shopperId,category,brand),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ProductResponse(new ArrayList<>(),"Failure"));
        }
    }

}
