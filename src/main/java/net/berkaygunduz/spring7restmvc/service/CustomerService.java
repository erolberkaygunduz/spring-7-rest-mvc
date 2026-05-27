package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.Customer;

import java.util.*;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(UUID uuid);
    Customer saveCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID uuid);

    void patchCustomerById(UUID customerId, Customer customer);
}
