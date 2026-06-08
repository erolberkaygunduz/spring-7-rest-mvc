package net.berkaygunduz.spring7restmvc.service;

import lombok.RequiredArgsConstructor;
import net.berkaygunduz.spring7restmvc.mappers.CustomerMapper;
import net.berkaygunduz.spring7restmvc.model.CustomerDTO;
import net.berkaygunduz.spring7restmvc.repositories.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID uuid) {
        return Optional.ofNullable(customerMapper
                .customerToCustomerDto(customerRepository
                        .findById(uuid).orElse(null)));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public void deleteCustomerById(UUID uuid) {

    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

    }
}
