package net.berkaygunduz.spring7restmvc.controller;

import lombok.AllArgsConstructor;
import net.berkaygunduz.spring7restmvc.service.BeerService;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class BeerController {

    private final BeerService beerService;



}
