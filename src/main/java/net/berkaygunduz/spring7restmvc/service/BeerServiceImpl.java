package net.berkaygunduz.spring7restmvc.service;

import lombok.extern.slf4j.Slf4j;
import net.berkaygunduz.spring7restmvc.model.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Efes")
                .beerStyle(BeerStyle.PILSNER)
                .upc("12454")
                .price(new BigDecimal("4.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Tuborg")
                .beerStyle(BeerStyle.LAGER)
                .upc("235235")
                .price(new BigDecimal("6.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Blank")
                .beerStyle(BeerStyle.LAGER)
                .upc("235523")
                .price(new BigDecimal("9.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existingBeer = beerMap.get(beerId);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(beerId,existingBeer);
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        Optional.ofNullable(beer.getBeerName())
                .filter(StringUtils::hasText)
                .ifPresent(existing::setBeerName);

        Optional.ofNullable(beer.getBeerStyle())
                .ifPresent(existing::setBeerStyle);

        Optional.ofNullable(beer.getPrice())
                .ifPresent(existing::setPrice);

        Optional.ofNullable(beer.getQuantityOnHand())
                .ifPresent(existing::setQuantityOnHand);

        Optional.ofNullable(beer.getUpc())
                .filter(StringUtils::hasText)
                .ifPresent(existing::setUpc);
    }

    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<Beer> getBeerById(UUID id) {

        log.debug("Get Beer Id in service called. Id : " + id.toString());
        return Optional.of(beerMap.get(id));
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .version(1)
                .build();

        beerMap.put(savedBeer.getId(),savedBeer);
        return savedBeer;
    }


}
