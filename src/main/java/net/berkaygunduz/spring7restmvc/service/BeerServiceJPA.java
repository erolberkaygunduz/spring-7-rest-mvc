package net.berkaygunduz.spring7restmvc.service;

import lombok.RequiredArgsConstructor;
import net.berkaygunduz.spring7restmvc.mappers.BeerMapper;
import net.berkaygunduz.spring7restmvc.model.BeerDTO;
import net.berkaygunduz.spring7restmvc.repositories.BeerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(
                beerMapper.beerToBeerDto(
                        beerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        return null;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beerDTO) {

    }

    @Override
    public void deleteBeerById(UUID beerId) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beerDTO) {

    }
}
