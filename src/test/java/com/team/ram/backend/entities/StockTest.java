package com.team.ram.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.stream.Stream;

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
    void testValidStockCreation() {
        Stock stock = new Stock();
        stock.setId(1);
        stock.setStockTicker("AMZN");
        stock.setPrice(100.00);
        stock.setVolume(10);
        stock.setBuyOrSell("BUY");
        stock.setStatusCode(0);
        assertThat(stock.getId()).isEqualTo(1);
        assertThat(stock.getStockTicker()).isEqualTo("AMZN");
        assertThat(stock.getPrice()).isEqualTo(100.00);
        assertThat(stock.getVolume()).isEqualTo(10);
        assertThat(stock.getBuyOrSell()).isEqualTo("BUY");
        assertThat(stock.getStatusCode()).isEqualTo(0);
    }

    @Test
    void nullStockTicker_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Stock(1, null, 100.00, 10, "BUY", 0));
        assertThat(exception.getMessage()).isEqualTo("Stock Label cannot be null");
    }

    @Test
    void minusPrice_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Stock(1, "AMZN", -100.00, 10, "BUY", 0));
        assertThat(exception.getMessage()).isEqualTo("Price cannot be a minus amount");
    }

    @Test
    void minusVolume_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Stock(1, "AMZN", 100.00, -10, "BUY", 0));
        assertThat(exception.getMessage()).isEqualTo("Volume cannot be a minus amount");
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
        assertTrue(stock.hashCode() == stock1.hashCode());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStockParams")
    void invalidParams_Throws_withMessage(int id, String stockTicker, double price, int volume, String buyOrSell, int statusCode, String expectedExceptionMessage){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Stock(id, stockTicker, price, volume, buyOrSell, statusCode));
        assertThat(ex.getMessage()).isEqualTo(expectedExceptionMessage);

    }

    static Stream<Arguments> provideInvalidStockParams(){
        return Stream.of(
                Arguments.of(1, null, 100.00, 10, "BUY", 0, "Stock Label cannot be null"),
                Arguments.of(1, "AMZN", -100.00, 10, "BUY", 0, "Price cannot be a minus amount"),
                Arguments.of(1, "AMZN", 100.00, -10, "BUY", 0, "Volume cannot be a minus amount")
        );
    }
}