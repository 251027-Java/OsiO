package com.revature.ExpenseReport.Services;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Repository.ExpenseRepo;
import com.revature.ExpenseReport.controllers.ExpenseDTO;
import com.revature.ExpenseReport.controllers.ExpenseWOIDDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    //fields
    private final ExpenseRepo repository;

    //constructors
    public ExpenseService(ExpenseRepo repository){
        this.repository = repository;
    }

    //methods
    public List<ExpenseDTO> getAllExpenses(){
        //returns list of expenses
        //need to convert every expense to a DTO
        //keep/put back in list
        return repository.findAll().stream().map(this::ExpenseToDto).toList();
    }

    public List<ExpenseDTO> searchByMerchant(String merchant){
        return repository.findByMerchant(merchant).stream().map(this::ExpenseToDto).toList();
    }

    public ExpenseDTO create(ExpenseWOIDDTO dto){
        Expense entity = new Expense(dto.expenseDate(), dto.expenseValue(), dto.expenseMerchant());
        return ExpenseToDto(repository.save(entity));
    }

    public ExpenseDTO getById(String id){
        Optional<Expense> res = repository.findById(id);
        return (res.isEmpty()) ? null : ExpenseToDto(res.get());
    }

    //update
    public ExpenseDTO update(String id, ExpenseDTO dto){
        Expense expense = repository.findById(id).orElseThrow (() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        expense.setDate(dto.expenseDate());
        expense.setValue(dto.expenseValue());
        expense.setMerchant(dto.expenseMerchant());
        return ExpenseToDto(repository.save(expense));
    }

    //delete
    public void delete(String id){
        repository.deleteById(id);
    }

    private ExpenseDTO ExpenseToDto(Expense expense){
        return new ExpenseDTO(expense.getId(), expense.getDate(), expense.getValue(), expense.getMerchant());
    }
}
