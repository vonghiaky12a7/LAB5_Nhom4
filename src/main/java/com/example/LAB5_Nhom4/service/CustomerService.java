package com.example.LAB5_Nhom4.service;

import com.example.LAB5_Nhom4.entity.Customer;
import com.example.LAB5_Nhom4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository repo;

    public boolean customerExists(Customer customer) {
        return repo.findByEmail(customer.getEmail()) != null;
    }

    public void saveCustomer(Customer customer) {
        if (!customerExists(customer)) {
            repo.save(customer);
        } else {
            // Handle the case where the customer already exists
            throw new IllegalArgumentException("Customer with the same email already exists.");
        }
    }

    public List<Customer> saveCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            if (customerExists(customer)) {
                throw new IllegalArgumentException("Customer with the same email already exists.");
            }
        }
        return repo.saveAll(customers);
    }

    public List<Customer> listAllCustomer() {
        return repo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Customer getCustomerByName(String name) {
        return repo.findByName(name);
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = repo.findById(customer.getId()).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAddress(customer.getAddress());
            return repo.save(existingCustomer);
        }
        return null;
    }

    public List<Customer> searchCustomers(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

}
