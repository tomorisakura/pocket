package com.enigma.pocket.services;

import com.enigma.pocket.entity.Customer;

import java.math.BigDecimal;

public interface WalletService {
    void sendWallet(Customer customer, BigDecimal total);
}
