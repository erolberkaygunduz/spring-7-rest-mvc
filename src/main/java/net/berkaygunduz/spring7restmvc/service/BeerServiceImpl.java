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

    Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beerDTO1 = BeerDTO.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Efes")
                .beerStyle(BeerStyle.PILSNER)
                .upc("12454")
                .price(new BigDecimal("4.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beerDTO2 = BeerDTO.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Tuborg")
                .beerStyle(BeerStyle.LAGER)
                .upc("235235")
                .price(new BigDecimal("6.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beerDTO3 = BeerDTO.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Blank")
                .beerStyle(BeerStyle.LAGER)
                .upc("235523")
                .price(new BigDecimal("9.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beerDTO1.getId(), beerDTO1);
        beerMap.put(beerDTO2.getId(), beerDTO2);
        beerMap.put(beerDTO3.getId(), beerDTO3);
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
        BeerDTO existingBeerDTO = beerMap.get(beerId);
        existingBeerDTO.setBeerName(beerDTO.getBeerName());
        existingBeerDTO.setPrice(beerDTO.getPrice());
        existingBeerDTO.setUpc(beerDTO.getUpc());
        existingBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());

        beerMap.put(beerId, existingBeerDTO);
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beerDTO) {
        BeerDTO existing = beerMap.get(beerId);

        Optional.ofNullable(beerDTO.getBeerName())
                .filter(StringUtils::hasText)
                .ifPresent(existing::setBeerName);

        Optional.ofNullable(beerDTO.getBeerStyle())
                .ifPresent(existing::setBeerStyle);

        Optional.ofNullable(beerDTO.getPrice())
                .ifPresent(existing::setPrice);

        Optional.ofNullable(beerDTO.getQuantityOnHand())
                .ifPresent(existing::setQuantityOnHand);

        Optional.ofNullable(beerDTO.getUpc())
                .filter(StringUtils::hasText)
                .ifPresent(existing::setUpc);
    }

    @Override
    public List<BeerDTO> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer Id in service called. Id : " + id.toString());
        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        BeerDTO savedBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beerDTO.getBeerName())
                .beerStyle(beerDTO.getBeerStyle())
                .quantityOnHand(beerDTO.getQuantityOnHand())
                .upc(beerDTO.getUpc())
                .price(beerDTO.getPrice())
                .version(1)
                .build();

        beerMap.put(savedBeerDTO.getId(), savedBeerDTO);
        return savedBeerDTO;
    }


}
