package org.example;

import org.example.Repository.IRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private IRepository repository;
    private Service service;

    @BeforeEach
    public void setUp() {
        service = new Service(repository);
    }

    @Test
    public void createNewExpense() {
        //Arrange
        int id = 1;
        //Act
        Expense newExpense = service.createNewExpense(1, 50, "Walmart");
        //Assert
        assertNotNull(newExpense);
        assertEquals(id, newExpense.getId());
        assertEquals("Walmart", newExpense.getMerchant());
        assertEquals(50.0, newExpense.getValue());
    }

    @Test
    public void getExpense() {
        //Arrange
        int id = 3;
        //Act
        Expense expense = new Expense(3, new Date(), 100, "Amazon");
        int newExpenseId = expense.getId();
        //Assert
        assertEquals(id, newExpenseId);
    }

    @Test
    void deleteExpense() {
        //Arrange
        int id = 5;
        //Act
        Expense expense = new Expense(5, new Date(), 150, "Target");
        boolean isItThere = service.deleteExpense(5);
        //Assert
        assertTrue(isItThere);
    }

    @Test
    void printExpenses() {
        //Arrange
        int id = 7;
        String output;
        //Act
        Expense expense = new Expense(7, new Date(), 200, "Best Buy");
        output = expense.toString();
        service.printExpenses();
        //Assert
        assertTrue(output.toString().contains("BestBuy"));
    }

    @Test
    void sumExpenses() {
        //Arrange
        double value = 0;
        //Act
        Expense expense = new Expense(9, new Date(), 33, "Kroger");
        Expense expense2 = new Expense(11, new Date(), 44, "Specs");
        Expense expense3 = new Expense(15, new Date(), 10, "Gamestop");
        service.sumExpenses();
        value = service.sumExpenses();
        //Assert
        assertEquals(value, service.sumExpenses());
    }

}
}