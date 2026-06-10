package net.berkaygunduz.spring7restmvc.repositories;

import jakarta.validation.ConstraintViolationException;
import net.berkaygunduz.spring7restmvc.entity.Beer;
import net.berkaygunduz.spring7restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void should_SaveBeer_While_DataIsProper() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("MyBeer")
                .beerStyle(BeerStyle.LAGER)
                .upc("2342352356")
                .price(new BigDecimal("11.29"))
                .build());

        beerRepository.flush();

        assertNotNull(savedBeer.getId());
        assertEquals("MyBeer", savedBeer.getBeerName());
    }

    @Test
    void should_ThrowError_On_SaveBeer_While_BeerNameTooLong() {

        assertThrows(ConstraintViolationException.class,()->{
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("MyBeer983475698475694357693475693745690874359673459679öknbgsdkljfbvnknersdgşfhsdjkfgılueghrsthdfgnbvsdryghnm3b45kjhfdgbkjbn346")
                    .beerStyle(BeerStyle.LAGER)
                    .upc("2342352356")
                    .price(new BigDecimal("11.29"))
                    .build());

            beerRepository.flush();
        });
    }

}