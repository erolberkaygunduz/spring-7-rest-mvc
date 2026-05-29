package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.Beer;

import java.util.*;

public interface BeerService {

    Optional<Beer> getBeerById(UUID id);

    List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}
