package com.enigma.pocket.controller;

import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import com.enigma.pocket.util.ResponseMessage;
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

    @GetMapping("/history")
    public ResponseMessage<List<ProductHistoryPrice>> getAllHistoryPrice() {
        List<ProductHistoryPrice> data = productHistoryPriceServices.findAllHistoryPrice();
        return ResponseMessage.success(200, data);
    }
}
