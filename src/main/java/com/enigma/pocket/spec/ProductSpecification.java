package com.enigma.pocket.spec;


import com.enigma.pocket.dto.ProductDto;
import com.enigma.pocket.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;

public class ProductSpecification {
    public static Specification<Product> findProduct(ProductDto productDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final Collection<Predicate> predicates = new ArrayList<>();
            if (!(productDto.getProductName() == null || productDto.getProductName().equals(""))) {
                final Predicate productName = criteriaBuilder.like(root.get("productName"), "%"+productDto.getProductName()+"%");
                predicates.add(productName);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
