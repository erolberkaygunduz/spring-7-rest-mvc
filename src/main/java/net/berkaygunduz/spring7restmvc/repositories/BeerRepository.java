package net.berkaygunduz.spring7restmvc.repositories;

import net.berkaygunduz.spring7restmvc.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
