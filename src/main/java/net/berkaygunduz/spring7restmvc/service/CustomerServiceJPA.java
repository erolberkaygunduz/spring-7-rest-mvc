package net.berkaygunduz.spring7restmvc.service;

import lombok.RequiredArgsConstructor;
import net.berkaygunduz.spring7restmvc.entity.Customer;
import net.berkaygunduz.spring7restmvc.mappers.CustomerMapper;
import net.berkaygunduz.spring7restmvc.model.CustomerDTO;
import net.berkaygunduz.spring7restmvc.repositories.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
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
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customerDTO)));

    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer ->{
            foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, ()->{
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();

    }

    @Override
    public Boolean deleteCustomerById(UUID uuid) {
        if(customerRepository.existsById(uuid)){
            customerRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

    }
}
