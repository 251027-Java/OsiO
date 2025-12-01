package com.revature.ExpenseReport;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Repository.ExpenseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ExpenseReportApplication {

	public static void main(String[] args) {
        SpringApplication.run(ExpenseReportApplication.class, args);
	}

    @Bean
    CommandLineRunner seedData(ExpenseRepo repository) {
        return args -> {
            var e1 = new Expense(new Date(), 67.99, "Walmart");
            var e2 = new Expense(new Date(), 56.99, "Depop");
            var e3 = new Expense(new Date(), 88.99, "PlayStation Store");
            repository.saveAll(List.of(e1, e2, e3));
        };
    }
}
