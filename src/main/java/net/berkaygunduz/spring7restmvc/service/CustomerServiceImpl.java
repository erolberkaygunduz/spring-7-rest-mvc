package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .customerName("Berkay")
                .uuid(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .customerName("Monika")
                .uuid(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .customerName("Erkay")
                .uuid(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getUuid(), customer1);
        customerMap.put(customer2.getUuid(), customer2);
        customerMap.put(customer3.getUuid(), customer3);
    }


    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<Customer> getCustomerById(UUID uuid) {
        return Optional.of(customerMap.get(uuid));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .customerName(customer.getCustomerName())
                .uuid(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getUuid(),savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {

        Customer existCustomer = customerMap.get(customerId);
        existCustomer.setCustomerName(customer.getCustomerName());
        existCustomer.setVersion(customer.getVersion());

        customerMap.put(customerId,existCustomer);
    }

    @Override
    public void deleteCustomerById(UUID uuid) {
        customerMap.remove(uuid);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer exist = customerMap.get(customerId);

        Optional.ofNullable(customer.getCustomerName())
                .filter(StringUtils::hasText)
                .ifPresent(exist::setCustomerName);

        Optional.ofNullable(customer.getVersion())
                .ifPresent(exist::setVersion);
    }
}
