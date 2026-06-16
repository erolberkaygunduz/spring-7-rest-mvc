package net.berkaygunduz.spring7restmvc.service;

import com.opencsv.bean.CsvToBeanBuilder;
import net.berkaygunduz.spring7restmvc.model.BeerCSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) {

        try {
            List<BeerCSVRecord> beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                    .withType(BeerCSVRecord.class)
                    .build().parse();
            return beerCSVRecords;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
