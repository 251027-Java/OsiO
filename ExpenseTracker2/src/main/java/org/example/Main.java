package org.example;

import org.example.Repository.H2Repository;
import org.example.Repository.IRepository;
import org.example.Repository.JSONRepository;
import org.example.Repository.MongoRepository;
import org.example.ExpenseService;

import java.util.ArrayList;
import java.util.List;

// As a user, I want to track my expenses so that i can build/submit an expense report at the end of the week.
// As a user, I need to include the date, value, and merchant to include on my expense report.

public class Main {
    // fields

    // methods
    static void main() {
        System.out.println("Expense Tracker Starting...");
        List<Expense> expenses = new ArrayList<Expense>();

        // THIS is where we switch our repository from one to another
//        IRepository repo = new TextRepository();
//        IRepository repo = new CSVRepository();
//        IRepository repo = new JSONRepository();
        IRepository repo = new MongoRepository();


//        System.out.println("Creating a test expense:");
      
//        service.createNewExpense(3,77.95, "Costco");
//        service.deleteExpense(3);

//        service.createNewExpense(4,67.67, "at&t");
//        service.deleteExpense(4);
//        service.createNewExpense(5,67.67, "bucees");
//        service.deleteExpense(7);
//        expenses.add(new Expense(2, new Date(), 85.75, "Costco"));
//        expenses.add(new Expense(3, new Date(), 10000, "Private Jet"));
//        repo.saveExpenses(expenses);

//        System.out.println("Loading saved expenses...");
//        expenses = repo.loadExpenses();
//        System.out.println(expenses);

//        service.sumExpenses();
//        service.printExpenses();

        ExpenseService service = new ExpenseService(repo);
        service.createNewExpense(4,67.67, "at&t");
        
        System.out.println("Expense Tracker Closing...");
    }
}
