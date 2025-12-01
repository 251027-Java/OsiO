package com.revature.ExpenseReport.Services;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Repository.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    //fields
    private final ExpenseRepo repository;

    //constructors
    public ExpenseService(ExpenseRepo repository){
        this.repository = repository;
    }

    //methods
    public List<Expense> getAllExpenses(){
        return repository.findAll();
    }

    public List<Expense> searchByMerchant(String merchant){
        return repository.findByMerchant(merchant);
    }
}
