package net.berkaygunduz.spring7restmvc.controller;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.berkaygunduz.spring7restmvc.model.Customer;
import net.berkaygunduz.spring7restmvc.service.CustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{uuid}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomerById(@PathVariable UUID uuid, @RequestBody Customer customer){
        customerService.patchCustomerById(uuid,customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerById(@PathVariable UUID uuid){
        customerService.deleteCustomerById(uuid);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping({CUSTOMER_PATH_ID})
    public ResponseEntity updateCustomerById(@PathVariable UUID uuid,@RequestBody Customer customer){
        customerService.updateCustomerById(uuid,customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handleCustomer(@RequestBody Customer customer){
        Customer saveCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",CUSTOMER_PATH+"/" + saveCustomer.getUuid().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @GetMapping(value = CUSTOMER_PATH)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = CUSTOMER_PATH_ID)
    public Optional<Customer> getCustomerByID(@PathVariable("uuid") UUID uuid){
        return customerService.getCustomerById(uuid);
    }
}
