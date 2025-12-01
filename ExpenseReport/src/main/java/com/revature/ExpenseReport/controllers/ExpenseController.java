package com.revature.ExpenseReport.controllers;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Services.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Expense> getAllExpenses(){
        return service.getAllExpenses(); //all of the expenses
    }

    @GetMapping("/search")
    public List<Expense> search(@RequestParam String merchant){
        return service.searchByMerchant(merchant); //all expenses for a merchant
    }



}
