package net.berkaygunduz.spring7restmvc.service;

import lombok.extern.slf4j.Slf4j;
import net.berkaygunduz.spring7restmvc.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer Id in service called. Id : "+ id.toString());
        return Beer.builder().id(id)
                .version(1)
                .beerName("Efes")
                .beerStyle(BeerStyle.PILSNER)
                .upc("12454")
                .price(new BigDecimal("4.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
