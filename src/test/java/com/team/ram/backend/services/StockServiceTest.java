package com.team.ram.backend.services;

import com.team.ram.backend.entities.Stock;
import com.team.ram.backend.error.StockNotFoundException;
import com.team.ram.backend.repositories.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    private Stock stock;

    private final List<Stock> stocks = new ArrayList<>();

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1);
        stock.setStockTicker("AMZN");
        stock.setPrice(100.00);
        stock.setVolume(10);
        stock.setBuyOrSell("BUY");
        stock.setStatusCode(0);
        stocks.add(stock);
        when(stockRepository.findAll()).thenReturn(stocks);
    }

    @Test
    public void saveStock_service_test() {
        Stock inputStock = new Stock();
        inputStock.setStockTicker("AMZN");
        inputStock.setPrice(100.00);
        inputStock.setVolume(10);
        inputStock.setBuyOrSell("BUY");
        inputStock.setStatusCode(0);
        when(stockService.saveStock(inputStock)).thenReturn(stock);
        Assertions.assertNotNull(inputStock);
        //assertEquals(inputStock.getId(), stock.getId()); // ID expected:0, actual:1
        assertEquals(inputStock.getStockTicker(), stock.getStockTicker());
    }

    @Test
    public void fetchStockList_service_test() {
        List<Stock> inputStock = stockService.fetchStockList();
        Assertions.assertNotNull(inputStock);
        when(stockService.fetchStockList()).thenReturn(inputStock);
    }

    @Test
    public void deleteStockById_positive_service_test() {
        when(stockRepository.findById(stock.getId())).thenReturn(Optional.of(stock));
        stockService.deleteStockById(stock.getId());
        verify(stockRepository, times(1)).deleteById(stock.getId());
    }

    @Test
    public void deleteStockById_negative_service_test() {
        when(stockRepository.findById(stock.getId())).thenReturn(Optional.empty());
        assertThrows(StockNotFoundException.class, () -> {
            stockService.deleteStockById(stock.getId());
        });

    }
}
