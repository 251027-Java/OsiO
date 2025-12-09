package com.revature.ExpenseReport.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Repository.ExpenseRepo;
import com.revature.ExpenseReport.Services.ExpenseService;
import com.revature.ExpenseReport.controllers.ExpenseDTO;
import com.revature.ExpenseReport.controllers.ExpenseWOIDDTO;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTests{
    //fields
    @Mock
    private ExpenseRepo repository;

    @InjectMocks
    private ExpenseService service;
    //Constructor

    //methods

        // Arrange - prepping any resources needed for the test
        // act - action/function of executing the code
        // assert - final check to pass or fail

    /*
    public ExpenseDTO getById(String id){
        Optional<Expense> res = repository.findById(id);
        return (res.isEmpty()) ? null : ExpenseToDto(res.get());
    } */

   @Test
    void happyPath_getExpenseById_returnsExpenseDTO() {
        // Arrange
        //prepping db value
        String id = "thisIsTheId";
        LocalDate date = LocalDate.now();
        Expense savedExpense = new Expense(date, new BigDecimal("50.00"), "Video Games");
        savedExpense.setId(id);

        //prepping expected value
        ExpenseDTO expected = new ExpenseDTO(id, date, new BigDecimal("50.00"), "Video Games");

        //put entry in database
        when(repository.findById(id)).thenReturn(Optional.of(savedExpense));

        // Act
        ExpenseDTO actual = service.getById(id);

        // Assert
        //comparing expected to actual
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void happyPath_createExpense_returnsExpenseDTO() {
        // Arrange
        // prep the input DTO
        LocalDate date = LocalDate.now();
        ExpenseWOIDDTO testDTO = new ExpenseWOIDDTO(date, new BigDecimal("67.70"), "Market Street");

        // prep the value that will be saved and returned from the db
        String id = "anothertestID";
        Expense savedTestExpense = new Expense(date, new BigDecimal("67.70"), "Market Street");
        savedTestExpense.setId(id);

        // prep our expected value to compare with for the assert
        ExpenseDTO expected = new ExpenseDTO(id, date, new BigDecimal("67.70"), "Market Street");

        // "save" the fake entry in the db
        when(repository.save(any(Expense.class))).thenReturn(savedTestExpense);

        // ACT
        ExpenseDTO actual = service.create(testDTO);

        // Assert
        // compare expected to actual
        assertThat(actual).isEqualTo(expected);
    }
}

