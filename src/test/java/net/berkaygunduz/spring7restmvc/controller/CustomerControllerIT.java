package net.berkaygunduz.spring7restmvc.controller;

import net.berkaygunduz.spring7restmvc.entity.Customer;
import net.berkaygunduz.spring7restmvc.model.CustomerDTO;
import net.berkaygunduz.spring7restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerController customerController;

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