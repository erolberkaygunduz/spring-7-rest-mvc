package net.berkaygunduz.spring7restmvc.service;

import net.berkaygunduz.spring7restmvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException{
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> beerCSVRecords = beerCsvService.convertCSV(file);
        System.out.println(beerCSVRecords.size());

        assertThat(beerCSVRecords.size()).isGreaterThan(0);
    }
}
