package com.isource.personalizeddataapi.model;

import lombok.Data;

import java.util.List;

public record PersonalisedProductList (String shopperId, List<PersonalisedProductDetail> shelf){

}
