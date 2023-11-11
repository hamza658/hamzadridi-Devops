package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.StockServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StockServiceImpTest {
    @Mock
    StockRepository Repo;

    @InjectMocks
    StockServiceImpl Service;

    Stock stock = Stock.builder().libelleStock("stock").qte(100).qteMin(10).build();
    List<Stock> listStocks = new ArrayList<Stock>() {
        {
            add(Stock.builder().libelleStock("first").qte(10).qteMin(5).build());
            add(Stock.builder().libelleStock("second").qte(200).qteMin(10).build());
        }
    };

    @Test
    void testRetrieveStock() {
        Mockito.when(Repo.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Stock s1 = Service.retrieveStock(2L);
        Assertions.assertNotNull(s1);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + s1);
        System.out.println("Expected: " + stock);
    }

    @Test
    void testAllRetrieveStock() {
        Mockito.when(Repo.findAll()).thenReturn(listStocks);
        List<Stock> lStocks = Service.retrieveAllStocks();
        Assertions.assertNotNull(lStocks);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + lStocks);
        System.out.println("Expected: " + listStocks);
    }

    @Test
    void testAddstock() {
        Mockito.when(Repo.save(stock)).thenReturn(stock);
        Stock s1 = Service.addStock(stock);
        Assertions.assertNotNull(s1);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + s1);
        System.out.println("Expected: " + stock);
    }

    @Test
    void testUpdatestock() {
        stock.setQteMin(5);
        Mockito.when(Repo.save(stock)).thenReturn(stock);
        Stock s1 = Service.updateStock(stock);
        Assertions.assertEquals(stock, s1);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + s1);
        System.out.println("Expected: " + stock);
    }

    @Test
    void testDeletestock() {
        Service.deleteStock(stock.getIdStock());
        Mockito.verify(Repo, Mockito.times(1)).deleteById(stock.getIdStock());

        // Print the actual and expected values to the console
        System.out.println("Deletion successful");
    }
}
