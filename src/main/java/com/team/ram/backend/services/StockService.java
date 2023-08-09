package com.team.ram.backend.services;

import com.team.ram.backend.entities.Stock;

import java.util.List;

public interface StockService {
    public Stock saveStock(Stock stock);

    public List<Stock> fetchStockList();


    public void deleteStockById(Integer stockId);
}
