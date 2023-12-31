package com.team.ram.backend.error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException(String message) {
        super(message);
    }
}
