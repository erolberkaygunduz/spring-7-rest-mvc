package net.berkaygunduz.spring7restmvc.repositories;

import net.berkaygunduz.spring7restmvc.entity.Beer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest
@ActiveProfiles("localmysql")
public class MySqlIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer("mysql:9");

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeer(){
        List<Beer> beers = beerRepository.findAll();

        assertThat(beers.size()).isGreaterThan(0);
    }
}
