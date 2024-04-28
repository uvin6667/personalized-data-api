package com.isource.personalizeddataapi.controller;

import com.isource.personalizeddataapi.model.*;
import com.isource.personalizeddataapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("internal")
@Tag(name = "Data Team Controller", description = "Handles the Internal Operations")
public class DataTeamController {

    private final ProductService productService;

    public DataTeamController(ProductService productService){
        this.productService = productService;
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
