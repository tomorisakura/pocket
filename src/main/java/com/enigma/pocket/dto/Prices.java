package com.enigma.pocket.dto;

import java.util.Objects;

public class Prices {
    private Double productPriceSell;
    private Double productPriceBuy;

    public Prices() {
    }

    public Prices(Double productPriceSell, Double productPriceBuy) {
        this.productPriceSell = productPriceSell;
        this.productPriceBuy = productPriceBuy;
    }

    public Double getProductPriceSell() {
        return productPriceSell;
    }

    public void setProductPriceSell(Double productPriceSell) {
        this.productPriceSell = productPriceSell;
    }

    public Double getProductPriceBuy() {
        return productPriceBuy;
    }

    public void setProductPriceBuy(Double productPriceBuy) {
        this.productPriceBuy = productPriceBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prices prices = (Prices) o;
        return Objects.equals(productPriceSell, prices.productPriceSell) && Objects.equals(productPriceBuy, prices.productPriceBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productPriceSell, productPriceBuy);
    }
}
