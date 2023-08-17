package com.team.ram.backend.repositories;

import com.team.ram.backend.entities.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StockRepositoryTest {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Stock stock;

    @BeforeEach
    void setUp() {
        stock =
               Stock.builder()
                       .stockTicker("AMZN")
                       .price(10.5)
                       .volume(50)
                       .buyOrSell("SELL")
                       .statusCode(0)
                       .build();
        entityManager.persistAndFlush(stock);
    }

    @Test
    public void fetchStockList_repository_test() {
        List<Stock> stock2 = stockRepository.findAll();
        Assertions.assertNotNull(stock2);
        assertEquals(4, stock2.size());
    }
}
