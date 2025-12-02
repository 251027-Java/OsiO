package com.revature.ExpenseReport.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDTO(String expenseId, LocalDate expenseDate, BigDecimal expenseValue, String expenseMerchant){}
//public record ExpenseWOIDDTO(LocalDate expenseDate, BigDecimal expenseValue, String expenseMerchant){}