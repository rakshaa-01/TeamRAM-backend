package com.team.ram.backend.repositories;

import com.team.ram.backend.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
 //If any new function added here, write tests in StockRepositoryTest.java, for others not needed.
}
