package com.isource.personalizeddataapi.controller;

import com.isource.personalizeddataapi.model.PersonalisedProductList;
import com.isource.personalizeddataapi.model.Product;
import com.isource.personalizeddataapi.model.PPLSaveResponse;
import com.isource.personalizeddataapi.model.ProductFetchResponse;
import com.isource.personalizeddataapi.model.ProductSaveResponse;
import com.isource.personalizeddataapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
@Tag(name = "Product Controller", description = "Handles all the Internal and External Operations")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
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

    @PostMapping(value = "/save-product")
    @Operation(method = "saveProduct", security = { @SecurityRequirement(name = "basicAuth")},
            description = "Saves a product in the database. Only internal db team have the access.")
    public ResponseEntity<ProductSaveResponse> saveProduct(@RequestBody Product product) {
        try{
            return ResponseEntity.ok(new ProductSaveResponse(productService.saveProduct(product),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ProductSaveResponse(null,"Failure"));
        }
    }

    @PostMapping(value = "/save-ppl")
    @Operation(method = "savePersonalisedProductList", security = { @SecurityRequirement(name = "basicAuth")},
            description = "Saves a personalised product list in the database. Only internal db team have the access.")
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
