package com.example.LAB5_Nhom4.controller;

import com.example.LAB5_Nhom4.entity.Customer;
import com.example.LAB5_Nhom4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> listCustomer = customerService.listAllCustomer();
        model.addAttribute("listCustomer", listCustomer);
        return "index";
    }

    @GetMapping("/add")
    public String showNewCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            customerService.saveCustomer(customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "add_customer";
        }
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "edit_customer";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("keyword") String keyword, Model model) {
        List<Customer> listCustomer = customerService.searchCustomers(keyword);
        model.addAttribute("listCustomer", listCustomer);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}
