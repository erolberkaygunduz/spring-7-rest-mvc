package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
