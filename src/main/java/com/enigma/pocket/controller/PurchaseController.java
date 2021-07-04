package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Purchase;
import com.enigma.pocket.services.PurchaseService;
import com.enigma.pocket.util.Response;
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
    public Purchase postPurchase(@RequestParam(name = "customerId") String customerId,
                                 @RequestBody Purchase purchase) throws JsonProcessingException {
        return purchaseService.purchase(purchase, customerId);
    }

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return purchaseService.findAllPurchase();
    }

    @GetMapping("/customer/{id}/purchase")
    public Response<List<Purchase>> getPurchaseByCustomerId(@PathVariable(name = "id") String id) {
        List<Purchase> data = purchaseService.findPurchaseByCustomerId(id);
        return new Response<>(200, true, data);
    }
}
