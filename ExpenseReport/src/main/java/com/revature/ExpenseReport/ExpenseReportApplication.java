package com.revature.ExpenseReport;

import com.revature.ExpenseReport.Model.AppUser;
import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Model.Report;
import com.revature.ExpenseReport.Repository.AppUserRepo;
import com.revature.ExpenseReport.Repository.ExpenseRepo;
import com.revature.ExpenseReport.Repository.ReportRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ExpenseReportApplication {

	public static void main(String[] args) {
        SpringApplication.run(ExpenseReportApplication.class, args);
	}

    @Bean // Bean is a single method that is run after the application is started
    CommandLineRunner seedData (ExpenseRepo repository, AppUserRepo appUserRepository) {
        return args -> {
            //expenses seed
            var e1 = new Expense(LocalDate.now(), new BigDecimal(59.99), "Walmart");
            var e2 = new Expense(LocalDate.now().minusDays(1), new BigDecimal(14.75), "Starbucks");
            var e3 = new Expense(LocalDate.now().minusDays(2), new BigDecimal(99.88), "Buffalo Wild Wings");

            repository.saveAll(List.of(e1, e2, e3));

            appUserRepository.save(new AppUser("admin", "password123", "ADMIN"));
            appUserRepository.save(new AppUser("user", "moneyballs", "USER"));

        };
    }

    @Bean // Bean is a single method that is run after the application is started
    CommandLineRunner seedDataReport (ReportRepo repository) {
        return args -> {
            //repository seed
            var r1 = new Report("Report1", "Started");
            repository.save(r1);
        };
    }
}
