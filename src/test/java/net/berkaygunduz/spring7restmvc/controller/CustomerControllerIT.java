package net.berkaygunduz.spring7restmvc.controller;

import net.berkaygunduz.spring7restmvc.entity.*;
import net.berkaygunduz.spring7restmvc.mappers.CustomerMapper;
import net.berkaygunduz.spring7restmvc.model.*;
import net.berkaygunduz.spring7restmvc.repositories.CustomerRepository;
import net.berkaygunduz.spring7restmvc.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerMapper customerMapper;
    @Qualifier("customerServiceJPA")
    @Autowired
    private CustomerService customerService;

    @Transactional
    @Rollback
    @Test
    void saveCustomer(){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("Zywiec")
                .build();

        ResponseEntity responseEntity = customerController.saveCustomer(customerDTO);
        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertNotNull(responseEntity.getHeaders().getLocation());

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();
        assertNotNull(customer);
    }

    @Test
    void updateExistingCustomer_NotFound(){
        assertThrows(NotFoundException.class, ()->{
            customerController.updateCustomerById(UUID.randomUUID(), CustomerDTO.builder().build());
        });

    }

    @Rollback
    @Transactional
    @Test
    void updateExistingCustomer(){
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "STARWARS";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateCustomerById(customer.getId(), customerDTO);
        assertEquals(HttpStatusCode.valueOf(204),responseEntity.getStatusCode());

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(customerName,updatedCustomer.getCustomerName());

    }

    @Test
    void testDeleteCustomerById_NotFound(){
        assertThrows(NotFoundException.class, ()->{
            customerController.deleteCustomerById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void testDeleteCustomerById(){
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity responseEntity = customerController.deleteCustomerById(customer.getId());

        assertEquals(HttpStatusCode.valueOf(204),responseEntity.getStatusCode());
        assertThat(customerRepository.findById(customer.getId()).isEmpty());
    }

    @Rollback
    @Transactional
    @Test
    void testListAllEmptyList(){
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.getAllCustomers();
        assertEquals(0,dtos.size());
    }

    @Test
    void testListAll(){
        List<CustomerDTO> dtos = customerController.getAllCustomers();
        assertEquals(3,dtos.size());
    }

    @Test
    void testGetCustomerById_ThrowNotNull(){
        assertThrows(NotFoundException.class, ()->{
            customerController.getCustomerByID(UUID.randomUUID());
        });
    }

    @Test
    void testGetCustomerById(){
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerByID(customer.getId());

        assertNotNull(customerDTO);

    }

}