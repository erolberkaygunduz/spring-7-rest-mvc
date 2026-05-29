package net.berkaygunduz.spring7restmvc.repositories;

import net.berkaygunduz.spring7restmvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
