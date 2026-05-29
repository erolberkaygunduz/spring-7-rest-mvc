package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.CustomerDTO;

import java.util.*;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    Optional<CustomerDTO> getCustomerById(UUID uuid);
    CustomerDTO saveCustomer(CustomerDTO customer);

    void updateCustomerById(UUID customerId, CustomerDTO customer);

    void deleteCustomerById(UUID uuid);

    void patchCustomerById(UUID customerId, CustomerDTO customer);
}
