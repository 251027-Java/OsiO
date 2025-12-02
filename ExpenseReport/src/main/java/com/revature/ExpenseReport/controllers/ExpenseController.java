package com.revature.ExpenseReport.controllers;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Services.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    //fields
    private final ExpenseService service;

    //constructors
    public ExpenseController(ExpenseService service){
        this.service = service;
    }

    //methods
    @GetMapping
    public List<ExpenseDTO> getAllExpenses(){
        return service.getAllExpenses(); //all of the expenses
    }

    @GetMapping("/search")
    public List<ExpenseDTO> search(@RequestParam String merchant){
        return service.searchByMerchant(merchant); //all expenses for a merchant
    }

    //create an expense
    @PostMapping
    public ExpenseDTO create(@RequestBody ExpenseWOIDDTO expensedto){
        return service.create(expensedto);
    }


    //Expense lookup

    //get expense by id
    @GetMapping("/{id}")
    public ExpenseDTO getbyId(@PathVariable String id){
        return service.getById(id);
    }


    //update/modify expense

    //delete expense

}
