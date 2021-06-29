package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.services.PocketService;
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
}
