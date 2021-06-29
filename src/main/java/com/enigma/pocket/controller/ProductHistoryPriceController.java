package com.enigma.pocket.controller;

import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductHistoryPriceController {

    @Autowired
    ProductHistoryPriceServices productHistoryPriceServices;

    @GetMapping("/historys")
    public List<ProductHistoryPrice> getAllHistoryPrice() {
        return productHistoryPriceServices.findAllHistoryPrice();
    }
}
