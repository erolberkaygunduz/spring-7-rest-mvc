package net.berkaygunduz.spring7restmvc.repositories;

import lombok.Data;
import net.berkaygunduz.spring7restmvc.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer(){

        Customer customer = customerRepository.save(Customer.builder()
                .customerName("BG Test").build());

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNotNull();

    }

}