package com.isource.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Shopper {
    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    public Shopper(String shopperId){
        this.shopperId = shopperId;
    }
}
