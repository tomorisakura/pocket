package com.enigma.pocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "m_products")
public class Product {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String productImage;
    private String productName;
    private Double productPriceBuy;
    private Double productPriceSell;
    private Integer productStatus;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")
    private List<ProductHistoryPrice> historyPrices = new ArrayList<>();

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    public Double getProductPriceBuy() {
        return productPriceBuy;
    }

    public void setProductPriceBuy(Double productPriceBuy) {
        this.productPriceBuy = productPriceBuy;
    }

    public Double getProductPriceSell() {
        return productPriceSell;
    }

    public void setProductPriceSell(Double productPriceSell) {
        this.productPriceSell = productPriceSell;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductHistoryPrice> getHistoryPrices() {
        return historyPrices;
    }

    public void setHistoryPrices(List<ProductHistoryPrice> historyPrice) {
        this.historyPrices = historyPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productName='" + productName + '\'' +
                ", productPriceBuy=" + productPriceBuy +
                ", productPriceSell=" + productPriceSell +
                ", productStatus=" + productStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
