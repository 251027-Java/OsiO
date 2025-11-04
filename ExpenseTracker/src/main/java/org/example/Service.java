package org.example;

import org.example.Repository.IRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    //Fields
    private final IRepository repository;


    //Constructors
    public Service(IRepository repository) {
        this.repository = repository;
    }


    //Methods
    public Expense createNewExpense(int id, double value, String merchant){
        if (repository.readExpense(id) != null){
            return null;
        }
        Expense newExpense = new Expense(id, new Date(), value, merchant);
        repository.createExpense(newExpense);
        return newExpense;
    }

    public Expense getExpense(int id){
        return repository.readExpense(id);
    }

    public boolean deleteExpense(int id){
        if (repository.readExpense(id) != null){
            return false;
        }
       repository.deleteExpense(id);
       return true;
    };

    public void printExpenses(){
        System.out.println(repository.loadExpenses());
    }

    public double sumExpenses(){
        List<Expense> expenses = repository.loadExpenses();
        double sum = 0;
        for (Expense e : expenses ){
            sum += e.getValue();
        }
        System.out.println(sum);
    }
}
