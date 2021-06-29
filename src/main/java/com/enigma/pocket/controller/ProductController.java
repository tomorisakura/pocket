package com.enigma.pocket.controller;


import com.enigma.pocket.dto.ProductDto;
import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import com.enigma.pocket.services.ProductServices;
import com.enigma.pocket.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @Autowired
    ProductHistoryPriceServices productHistoryPriceServices;

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productServices.findAllProduct();
    }

    @GetMapping("/product")
    public PageWrapper<Product> getAllProduct(@RequestBody ProductDto productDto,
                                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productServices.findProduct(productDto, pageable);
        return new PageWrapper<>(200, products);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") String id) {
        return productServices.findProductById(id);
    }

    @PostMapping("/product")
    public Product insertProduct(@RequestBody Product product) {
        return productServices.insertProduct(product);
    }

    @PutMapping("/product")
    public Product updateProductById(@RequestBody Product product) {
        return productServices.updateProduct(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productServices.deleteProductById(id);
    }

    @GetMapping("/product/{id}/history")
    public List<ProductHistoryPrice> getHistoryByProduct(@PathVariable(name = "id") String id) {
        return productHistoryPriceServices.findAllByProduct(id);
    }

    @GetMapping("/product/name/{productName}")
    public Product getProductByName(@PathVariable(name = "productName") String productName) {
        return productServices.findProductByName(productName);
    }

}
