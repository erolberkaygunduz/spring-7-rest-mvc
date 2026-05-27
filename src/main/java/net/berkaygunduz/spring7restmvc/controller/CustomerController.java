package net.berkaygunduz.spring7restmvc.controller;

import jakarta.websocket.server.PathParam;
import lombok.*;
import net.berkaygunduz.spring7restmvc.model.Customer;
import net.berkaygunduz.spring7restmvc.service.CustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("{customerId}")
    public ResponseEntity patchCustomerById(@PathVariable UUID customerId, @RequestBody Customer customer){
        customerService.patchCustomerById(customerId,customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deleteCustomerById(@PathVariable UUID uuid){
        customerService.deleteCustomerById(uuid);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"{customerId}"})
    public ResponseEntity updateCustomerById(@PathVariable UUID customerId,@RequestBody Customer customer){
        customerService.updateCustomerById(customerId,customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handleCustomer(@RequestBody Customer customer){
        Customer saveCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/" + saveCustomer.getUuid().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable("customerId") UUID customerId){
        return customerService.getCustomerById(customerId);
    }
}
