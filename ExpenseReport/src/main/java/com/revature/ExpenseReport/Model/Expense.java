package com.revature.ExpenseReport.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity // telling JPA that this needs to be a table in the database
@Table(name = "expenses") // naming the table in the database
public class Expense {
    // Fields
    @Id @GeneratedValue private String expenseId;
    @Column(name = "expenseMerchant") private String expenseMerchant; // manage column information for this variable
    private LocalDate expenseDate;
    private BigDecimal expenseValue;

    @ManyToOne()
    @JoinColumn(name= "reportId")
    @ToString.Exclude
    private Report report;

    // Constructor
    public Expense() {}

    public Expense(LocalDate date, BigDecimal value, String merchant){
        this.expenseDate = date;
        this.expenseValue = value;
        this.expenseMerchant = merchant;
    }

    public Expense(String Id, LocalDate date, BigDecimal value, String merchant){
        this.expenseDate = date;
        this.expenseValue = value;
        this.expenseMerchant = merchant;
        this.expenseId = Id;
    }

    // Methods
    public String getId() { return expenseId; }
    public LocalDate getDate() { return expenseDate; }
    public BigDecimal getValue() { return expenseValue; }
    public String getMerchant() { return expenseMerchant; }
    public Report getReport() { return this.report; }

    public void setId(String id) { this.expenseId = id; }
    public void setDate(LocalDate date) { this.expenseDate = date; }
    public void setValue(BigDecimal value) { this.expenseValue = value; }
    public void setMerchant(String merchant) { this.expenseMerchant = merchant; }
    public void setReport(Report report) { this.report = report;}
}
