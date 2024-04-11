package com.isource.personalizeddataapi.model;

import java.util.List;

public record ProductFetchResponse ( List<Product> products, String responseStatus) {

}