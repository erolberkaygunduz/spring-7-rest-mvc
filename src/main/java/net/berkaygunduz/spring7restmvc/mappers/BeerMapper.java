package net.berkaygunduz.spring7restmvc.mappers;

import net.berkaygunduz.spring7restmvc.entity.Beer;
import net.berkaygunduz.spring7restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
