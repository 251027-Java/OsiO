package com.revature.ExpenseReport.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Expense {
    //fields
    @Id
    @GeneratedValue
    private String id;

    private String merchant;
    private Date date;
    private double value;


    //constructor
    public Expense(){}

    public Expense(Date date, double value, String merchant){
        this.date = date;
        this.value = value;
        this.merchant = merchant;
    }

    //methods
}
