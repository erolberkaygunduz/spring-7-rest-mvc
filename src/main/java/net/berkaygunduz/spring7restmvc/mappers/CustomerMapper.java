package net.berkaygunduz.spring7restmvc.mappers;

import net.berkaygunduz.spring7restmvc.entity.Customer;
import net.berkaygunduz.spring7restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDto(Customer customer);
}
