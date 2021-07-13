package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.services.PocketService;
import com.enigma.pocket.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PocketController {

    @Autowired
    PocketService pocketService;

    @PostMapping("/pocket")
    public ResponseMessage<Pocket> postPocket(@RequestBody Pocket pocket) {
        Pocket data = pocketService.createPocket(pocket);
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/pockets")
    public ResponseMessage<List<Pocket>> getAllPocket() {
        List<Pocket> data = pocketService.getAllPocket();
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/pocket/{id}")
    public ResponseMessage<Pocket> getPocketById(@PathVariable(name = "id") String id) {
        Pocket data = pocketService.getPocketById(id);
        return ResponseMessage.success(200, data);
    }

    @DeleteMapping("/pocket/{id}")
    public void deletePocketById(@PathVariable(name = "id") String id) { pocketService.deletePocket(id); }

    @PutMapping("/pocket")
    public ResponseMessage<Pocket> updatePocket(@RequestBody Pocket pocket) {
        Pocket data = pocketService.updatePocket(pocket);
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/customer-pocket")
    public ResponseMessage<List<Pocket>> findPocketByProductAndCustomer(
            @RequestParam(name = "productId") String productId,
            @RequestParam(name = "customerId") String customerId) {
        List<Pocket> data = pocketService.findPocketByCustomerAndProduct(customerId, productId);
        return ResponseMessage.success(200, data);
    }
}
