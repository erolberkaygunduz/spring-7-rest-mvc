package net.berkaygunduz.spring7restmvc.controller;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.berkaygunduz.spring7restmvc.model.CustomerDTO;
import net.berkaygunduz.spring7restmvc.service.CustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(value = CUSTOMER_PATH)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerByID(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity saveCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO saveCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CUSTOMER_PATH + "/" + saveCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId) {
        if (!customerService.deleteCustomerById(customerId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping({CUSTOMER_PATH_ID})
    public ResponseEntity updateCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer) {
        if(customerService.updateCustomerById(customerId, customer).isEmpty()){
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer) {
        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
