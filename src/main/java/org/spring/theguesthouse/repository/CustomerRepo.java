package org.spring.theguesthouse.repository;

import org.spring.theguesthouse.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {}
