package com.isource.personalizeddataapi.controller;

import com.isource.personalizeddataapi.dto.ProductDto;
import com.isource.personalizeddataapi.model.ProductFetchResponse;
import com.isource.personalizeddataapi.model.ProductSaveResponse;
import com.isource.personalizeddataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getbyShopper")
    public ResponseEntity<ProductFetchResponse> getProductsByShopper(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        try{
            return ResponseEntity.ok(new ProductFetchResponse(productService.getProductsByShopper(shopperId,category,brand),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ProductFetchResponse(new ArrayList<>(),"Failure"));
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ProductSaveResponse> saveProduct(@RequestBody ProductDto productDto) {
        try{
            return ResponseEntity.ok(new ProductSaveResponse(productService.saveProduct(productDto),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ProductSaveResponse("Failure"));
        }
    }

}
