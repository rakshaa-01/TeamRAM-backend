package com.team.ram.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockTest {

    private Stock stock;
    private Stock stock1;

    @BeforeEach
    void setUp() {
        stock = Stock.builder()
                .stockTicker("AMZN")
                .price(10.5)
                .volume(50)
                .buyOrSell("SELL")
                .statusCode(0)
                .id(1)
                .build();

        stock1 = Stock.builder()
                .stockTicker("AMZN")
                .price(10.5)
                .volume(50)
                .buyOrSell("SELL")
                .statusCode(0)
                .id(1)
                .build();
    }
    @Test
    void testToString() {
        assertEquals(stock.toString(), stock1.toString());
    }

    @Test
    void testEquals() {
        boolean equal = stock.equals(stock1);
        assertTrue(equal);
    }

    @Test
    void testHashCode() {
        assertTrue(stock.hashCode()==stock1.hashCode());
    }
}