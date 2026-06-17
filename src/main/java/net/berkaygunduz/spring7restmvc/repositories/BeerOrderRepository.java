package net.berkaygunduz.spring7restmvc.repositories;

import net.berkaygunduz.spring7restmvc.entity.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}
