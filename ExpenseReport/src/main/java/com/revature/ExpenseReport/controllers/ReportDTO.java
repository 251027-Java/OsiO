package com.revature.ExpenseReport.controllers;

import java.util.List;

public record ReportDTO (String reportId,
                        String reportTitle,
                        String reportStatus,
                        List<ExpenseDTO> reportExpenses ){}
