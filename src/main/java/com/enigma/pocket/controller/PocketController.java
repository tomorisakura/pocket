package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.services.PocketService;
import com.enigma.pocket.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PocketController {

    @Autowired
    PocketService pocketService;

    @PostMapping("/pocket")
    public Pocket postPocket(@RequestBody Pocket pocket) {
        return pocketService.createPocket(pocket);
    }

    @GetMapping("/pockets")
    public List<Pocket> getAllPocket() {
        return pocketService.getAllPocket();
    }

    @GetMapping("/pocket/{id}")
    public Pocket getPocketById(@PathVariable(name = "id") String id) {
        return pocketService.getPocketById(id);
    }

    @DeleteMapping("/pocket/{id}")
    public void deletePocketById(@PathVariable(name = "id") String id) { pocketService.deletePocket(id); }

    @PutMapping("/pocket")
    public Pocket updatePocket(@RequestBody Pocket pocket) { return pocketService.updatePocket(pocket); }

    @GetMapping("/customer-pocket")
    public Response<List<Pocket>> findPocketByProductAndCustomer(@RequestParam(name = "productId") String productId,
                                                           @RequestParam(name = "customerId") String customerId) {
        List<Pocket> data = pocketService.findPocketByCustomerAndProduct(customerId, productId);
        return new Response<>(200, true, data);
    }
}
