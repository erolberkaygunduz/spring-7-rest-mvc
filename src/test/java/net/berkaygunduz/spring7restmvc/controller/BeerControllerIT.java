package net.berkaygunduz.spring7restmvc.controller;

import net.berkaygunduz.spring7restmvc.entity.Beer;
import net.berkaygunduz.spring7restmvc.model.BeerDTO;
import net.berkaygunduz.spring7restmvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//Integration Test
@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerIdNotFound(){

        assertThrows(NotFoundException.class,()->{
            beerController.getBeerById(UUID.randomUUID());

        });
    }

    @Test
    void testGetBeerById(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerController.getBeerById(beer.getId());

        assertNotNull(beerDTO);
    }

    @Test
    void testListBeers(){
        List<BeerDTO> dtos = beerController.listBeers();
        assertEquals(3, dtos.size());
    }

    @Rollback
    @Transactional
    @Test
    void testListBeers_EmtpyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();
        assertEquals(0,dtos.size());


    }

}