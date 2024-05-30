package com.example.LAB5_Nhom4.repository;

import com.example.LAB5_Nhom4.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findByEmail(String email);
    List<Customer> findByNameContainingIgnoreCase(String keyword);
}
