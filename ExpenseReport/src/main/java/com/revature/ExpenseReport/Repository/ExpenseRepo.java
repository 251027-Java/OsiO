package com.revature.ExpenseReport.Repository;

import com.revature.ExpenseReport.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, String> {
    //Expense findById(String id);
    List<Expense> findByMerchant(String merchant);
}
