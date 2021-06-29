package com.enigma.pocket.dto;

import java.math.BigDecimal;

public class WalletDto {
    private String id;
    private String phoneNumber;
    private BigDecimal balance;

    public WalletDto() {
    }

    public WalletDto(String phoneNumber, BigDecimal balance) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
