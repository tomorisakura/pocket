package com.enigma.pocket.spec;

import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerSpecification {
    public static Specification<Customer> findCustomers(CustomerSearchDto customerSearchForm) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final Collection<Predicate> predicates = new ArrayList<>();
            if (!(customerSearchForm.getFirstName() == null || customerSearchForm.getFirstName().equals(""))) {
                final Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + customerSearchForm.getFirstName() + "%");
                predicates.add(firstNamePredicate);
            }

                if (!(customerSearchForm.getLastName() == null || customerSearchForm.getLastName().equals(""))) {
                    final Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + customerSearchForm.getLastName() + "%");
                    predicates.add(lastNamePredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
