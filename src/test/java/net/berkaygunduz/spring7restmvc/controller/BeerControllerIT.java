package net.berkaygunduz.spring7restmvc.controller;

import net.berkaygunduz.spring7restmvc.entity.Beer;
import net.berkaygunduz.spring7restmvc.mappers.BeerMapper;
import net.berkaygunduz.spring7restmvc.model.BeerDTO;
import net.berkaygunduz.spring7restmvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//Integration Test
@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;


    @Test
    void deleteBeerById_NotFound(){
        assertThrows(NotFoundException.class, ()->{
            beerController.deleteBeerById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void deletedBeerById_Found(){
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = beerController.deleteBeerById(beer.getId());

        assertEquals(HttpStatusCode.valueOf(204),responseEntity.getStatusCode());
        assertThat(beerRepository.findById(beer.getId()).isEmpty());
    }

    @Test
    void updateExistingBeer_NotFound(){
        assertThrows(NotFoundException.class, ()->{
            beerController.updateBeerById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);

        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateBeerById(beer.getId(), beerDTO);
        assertEquals(HttpStatusCode.valueOf(204),responseEntity.getStatusCode());

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertEquals(beerName,updatedBeer.getBeerName());
    }

    @Rollback
    @Transactional
    @Test
    void testBeerSave(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Zywiec")
                .build();

        ResponseEntity responseEntity = beerController.saveBeer(beerDTO);
        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertNotNull(responseEntity.getHeaders().getLocation());

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Beer beer = beerRepository.findById(savedUUID).get();
        assertNotNull(beer);
    }

    @Test
    void testBeerId_NotFound(){

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