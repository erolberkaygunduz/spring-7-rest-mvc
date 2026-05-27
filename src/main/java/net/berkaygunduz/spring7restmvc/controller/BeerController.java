package net.berkaygunduz.spring7restmvc.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.berkaygunduz.spring7restmvc.model.Beer;
import net.berkaygunduz.spring7restmvc.service.BeerService;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {

    private final BeerService beerService;

    public Beer getBeerByService(UUID id){
        log.debug("Get Beer By Id - in controller.");
        return beerService.getBeerById(id);
    }



}
