package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.BeerDTO;

import java.util.*;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);

    List<BeerDTO> listBeers();

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    void updateBeerById(UUID beerId, BeerDTO beerDTO);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beerDTO);
}
