package com.enigma.pocket.repository;


import com.enigma.pocket.dto.Prices;
import com.enigma.pocket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByCreatedAtBetween(Date start, Date end);

    //native query
    @Query(value = "SELECT * FROM m_products p WHERE p.product_price_sell > :maxPrice AND p.product_status = :status", nativeQuery = true)
    List<Product> findAllProductGreaterThan(@Param("maxPrice")BigDecimal maxPrice, @Param("status") Integer status);

    //projection
    @Query(value = "SELECT new com.enigma.pocket.dto.Prices(p.productPriceSell, p.productPriceBuy) FROM Product p")
    List<Prices> prices();
    
    Product findProductByProductName(String name);
}
