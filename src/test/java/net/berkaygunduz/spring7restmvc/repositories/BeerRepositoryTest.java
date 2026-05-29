package net.berkaygunduz.spring7restmvc.repositories;

import net.berkaygunduz.spring7restmvc.entity.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("MyBeer")
                .build());

        assertNotNull(savedBeer.getId());
        assertEquals("MyBeer",savedBeer.getBeerName());
    }

}