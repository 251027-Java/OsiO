package com.revature.ExpenseReport.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
public class Report {
    //fields
    @Id @GeneratedValue
    private String reportId;
    private String reportTitle;
    private String reportStatus;

    @OneToMany(mappedBy = "report")
    private List<Expense> reportExpenses =  new ArrayList<>();

    //constructor
    public Report (String title, String status){
        this.reportTitle = title;
        this.reportStatus = status;
    }

    @Override
    public String toString() {
        return "Report: %s - Status: %s - Expenses: %s".formatted(this.reportId, this.reportStatus, this.reportExpenses.stream().map(Expense::getId).toList().toString());
    }


    //methods
}
