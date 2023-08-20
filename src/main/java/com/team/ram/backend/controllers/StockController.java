package com.team.ram.backend.controllers;
import com.team.ram.backend.entities.Stock;
import com.team.ram.backend.services.StockService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@CrossOrigin
public class StockController {

    @Autowired
    private StockService stockService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(StockController.class);

    @PostMapping("/stock")
    public Stock saveStock(@Valid @RequestBody Stock stock) {
        LOGGER.info("Inside saveStock() of StockController class");
        return stockService.saveStock(stock);
    }

    @GetMapping("/stock")
    public List<Stock> fetchStockList() {
        LOGGER.info("Inside fetchStockList() of StockController class");
        return stockService.fetchStockList();
    }

    @DeleteMapping("/stock/{id}")
    public String deleteStockById(@PathVariable("id") Integer stockId) {
        stockService.deleteStockById(stockId);
        LOGGER.info("Stock order deleted successfully");
        // The below can be used when return type of calling function is - ResponseEntity<String>
        // return ResponseEntity.ok("Stock order deleted successfully!.");
         return "Stock order ID: " + stockId + " deleted Successfully!!";
    }

    @GetMapping("/check-ticker")
    public boolean checkTicker(@RequestParam String stockTicker) {
        String apiUrl = "https://v588nmxc10.execute-api.us-east-1.amazonaws.com/default/tickerList";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);
        if (response != null && response.contains(stockTicker)) {
            return true;
        } else {
            return false;
        }
    }
}
