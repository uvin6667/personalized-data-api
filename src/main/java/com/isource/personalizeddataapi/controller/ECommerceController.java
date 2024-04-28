package com.isource.personalizeddataapi.controller;

import com.isource.personalizeddataapi.model.ProductFetchResponse;
import com.isource.personalizeddataapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("external")
@Tag(name = "E-Commerce Controller", description = "Handles the External Operations")
public class ECommerceController {

    private final ProductService productService;

    public ECommerceController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/get-products")
    @Cacheable(value = "productCache", key = "{#shopperId, #category, #brand, #limit}")
    @Operation(method = "getProductsByShopper",
            security = { @SecurityRequirement(name = "basicAuth")},
            description = "Fetches products of a particular shopper. Both internal and external personnel are authorized to use.")
    public ResponseEntity<ProductFetchResponse> getProductsByShopper(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        try{
            return ResponseEntity.ok(new ProductFetchResponse(productService.getProductsByShopper(shopperId,category,brand,limit),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ProductFetchResponse(new ArrayList<>(),"Failure"));
        }
    }

}
