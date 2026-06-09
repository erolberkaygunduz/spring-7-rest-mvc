package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.CustomerDTO;

import java.util.*;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    Optional<CustomerDTO> getCustomerById(UUID uuid);
    CustomerDTO saveCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteCustomerById(UUID uuid);

    void patchCustomerById(UUID customerId, CustomerDTO customer);
}
