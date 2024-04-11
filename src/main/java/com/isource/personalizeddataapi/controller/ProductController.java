package com.isource.personalizeddataapi.controller;

import com.isource.personalizeddataapi.model.PersonalisedProductList;
import com.isource.personalizeddataapi.model.Product;
import com.isource.personalizeddataapi.model.PPLSaveResponse;
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

    @PostMapping(value = "/save-product")
    public ResponseEntity<ProductSaveResponse> saveProduct(@RequestBody Product productDto) {
        try{
            return ResponseEntity.ok(new ProductSaveResponse(productService.saveProduct(productDto),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ProductSaveResponse("Failure"));
        }
    }

    @PostMapping(value = "/save-ppl")
    public ResponseEntity<PPLSaveResponse> savePersonalisedProductList(@RequestBody PersonalisedProductList personalisedProductList) {
        try{
            if (productService.savePPL(personalisedProductList)) {
                return ResponseEntity.ok(new PPLSaveResponse("Success"));
            } else {
                return ResponseEntity.internalServerError().body(new PPLSaveResponse("Failure"));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new PPLSaveResponse("Failure"));
        }
    }

}
