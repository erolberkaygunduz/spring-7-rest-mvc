package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.*;
import org.springframework.data.domain.Page;

import java.util.*;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);

    Page<BeerDTO> getAllBeersAsList(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO);

    Boolean deleteBeerById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDTO);
}
