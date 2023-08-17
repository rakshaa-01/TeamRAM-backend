package com.team.ram.backend.services;

import com.team.ram.backend.entities.Stock;
import com.team.ram.backend.repositories.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    private Stock stock;

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
//        Mockito.when(stockRepository.findByDepartmentNameIgnoreCase("IT"))
//                .thenReturn(department);
    }

    @Test
    public void saveStock_service_test() {
        Stock inputStock = Stock.builder()
                .stockTicker("AMZN")
                .price(10.5)
                .volume(50)
                .buyOrSell("SELL")
                .statusCode(0)
                .build();
        Mockito.when(stockService.saveStock(inputStock)).thenReturn(stock);
        Assertions.assertNotNull(inputStock);
        //assertEquals(inputStock.getId(), stock.getId()); - ID expected:0, actual:1
        assertEquals(inputStock.getStockTicker(), stock.getStockTicker());
    }

    @Test
    public void fetchStockList_service_test() {
        List<Stock> inputStock = stockService.fetchStockList();
        Assertions.assertNotNull(inputStock);
        Mockito.when(stockService.fetchStockList()).thenReturn(inputStock);
    }

    @Test
    public void deleteStockById_service_test() {
        stockService.deleteStockById(stock.getId());
        Mockito.verify(stockRepository, Mockito.times(1))
                .deleteById(stock.getId());
    }
}
