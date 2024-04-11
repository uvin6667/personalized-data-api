package com.isource.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product", indexes = {
        @Index(name = "idx_category", columnList = "category"),@Index(name = "idx_brand",columnList = "brand")})
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;
    private String category;
    private String brand;
}
