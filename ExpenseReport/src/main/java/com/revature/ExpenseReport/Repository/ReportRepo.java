package com.revature.ExpenseReport.Repository;

import com.revature.ExpenseReport.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Report, String> {}
