package com.isource.personalizeddataapi.repository;

import com.isource.personalizeddataapi.dto.ProductDto;
import com.isource.personalizeddataapi.entity.ShelfItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ShelfItem,Long> {
    @Query("SELECT new com.isource.personalizeddataapi.dto.ProductDto(si.product.productId, " +
            "si.product.category, si.product.brand) " +
            "FROM ShelfItem si " +
            "WHERE si.shopper.shopperId = :shopperId " +
            "AND (:category IS NULL OR si.product.category = :category) " +
            "AND (:brand IS NULL OR si.product.brand = :brand) " +
            "")
    List<ProductDto> getProductsByShopper(
            @Param("shopperId") String shopperId,
            @Param("category") String category,
            @Param("brand") String brand);
}
