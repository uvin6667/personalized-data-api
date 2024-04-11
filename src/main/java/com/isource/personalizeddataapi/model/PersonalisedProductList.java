package com.isource.personalizeddataapi.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonalisedProductList {

    private String shopperId;
    private List<PersonalisedProductDetail> shelf;
}
