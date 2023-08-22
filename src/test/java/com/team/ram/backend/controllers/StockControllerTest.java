package com.team.ram.backend.controllers;

import com.team.ram.backend.services.StockService;
import com.team.ram.backend.entities.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
public class StockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    private Stock stock;

    private final String apiUrl = "https://v588nmxc10.execute-api.us-east-1.amazonaws.com/default/tickerList";


    @MockBean
    private RestTemplate restTemplate;

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
    }

    @Test
    void saveStock_controller_test() throws Exception {
       Stock inputStock = Stock.builder()
               .stockTicker("AMZN")
               .price(10.5)
               .volume(50)
               .buyOrSell("SELL")
               .statusCode(0)
               .build();

        when(stockService.saveStock(inputStock))
                .thenReturn(stock);

        mockMvc.perform(post("/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"stockTicker\":\"AMZN\",\"price\":10.5,\"volume\":50,\"buyOrSell\":\"BUY\",\"statusCode\":0}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchStockList_controller_test() throws Exception {
        List<Stock> inputStock = new ArrayList<>();
        inputStock.add(stock);
        when(stockService.fetchStockList())
                .thenReturn(inputStock);

        mockMvc.perform(get("/stock")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStockById_controller_test() throws Exception {
        Mockito.doNothing().when(stockService).deleteStockById(stock.getId());
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/stock/{id}", stock.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void checkTicker_test_positive() throws Exception {
        String stockTicker = "AAPL";
        String apiResponse = "AAPL,GOOG,AMZN";

        when(restTemplate.getForObject(apiUrl, String.class)).thenReturn(apiResponse);

        StockController stockController = new StockController();

        boolean result = stockController.checkTicker(stockTicker);
        assertTrue(result);
    }

    @Test
    void checkTicker_test_negative() throws Exception{
        String stockTicker = "TISLA";
        String apiResponse = "AAPL,GOOG,AMZN";

        when(restTemplate.getForObject(apiUrl, String.class)).thenReturn(apiResponse);

        StockController stockController = new StockController();

        boolean result = stockController.checkTicker(stockTicker);
        assertFalse(result);
    }
}
