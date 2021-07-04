package com.enigma.pocket.services;

import com.enigma.pocket.entity.Pocket;

import java.math.BigDecimal;
import java.util.List;

public interface PocketService {
    public List<Pocket> getAllPocket();
    public Pocket getPocketById(String id);
    public Pocket createPocket(Pocket pocket);
    Pocket updatePocket(Pocket pocket);
    void topUp(Pocket pocket, Double qty, BigDecimal total);
    void sellPocket(Pocket pocket, Double qty, BigDecimal total);
    void deletePocket(String pocketId);
}
