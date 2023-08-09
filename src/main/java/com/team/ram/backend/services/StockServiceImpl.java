package com.team.ram.backend.services;

import com.team.ram.backend.entities.Stock;
import com.team.ram.backend.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> fetchStockList() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteStockById(Integer stockId) {
        stockRepository.deleteById(stockId);
    }
}
