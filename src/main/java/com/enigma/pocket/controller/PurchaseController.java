package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Purchase;
import com.enigma.pocket.services.PurchaseService;
import com.enigma.pocket.util.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/purchase")
    public ResponseMessage<Purchase> postPurchase(
            @RequestParam(name = "customerId") String customerId,
            @RequestBody Purchase purchase) throws JsonProcessingException {
        Purchase data = purchaseService.purchase(purchase, customerId);
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/purchases")
    public ResponseMessage<List<Purchase>> getAllPurchases() {
        List<Purchase> data = purchaseService.findAllPurchase();
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/customer/{id}/purchase")
    public ResponseMessage<List<Purchase>> getPurchaseByCustomerId(@PathVariable(name = "id") String id) {
        List<Purchase> data = purchaseService.findPurchaseByCustomerId(id);
        return ResponseMessage.success(200, data);
    }
}
