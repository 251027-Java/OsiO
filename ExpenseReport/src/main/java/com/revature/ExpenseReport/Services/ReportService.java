package com.revature.ExpenseReport.Services;

import com.revature.ExpenseReport.Model.Expense;
import com.revature.ExpenseReport.Model.Report;
import com.revature.ExpenseReport.Repository.ReportRepo;
import com.revature.ExpenseReport.controllers.ExpenseDTO;
import com.revature.ExpenseReport.controllers.ReportDTO;
import com.revature.ExpenseReport.controllers.ReportWOIDDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    //fields
    private final ReportRepo repository;

    //constructors
    public ReportService(ReportRepo repository){
        this.repository = repository;
    }

    private Expense DTOtoExpense (ExpenseDTO dto){
        return new Expense(dto.expenseId(),dto.expenseDate(), dto.expenseValue(), dto.expenseMerchant());
    }

    //methods
    public List<ReportDTO> getAllReports(){
        //returns list of reports
        //need to convert every report to a DTO
        //keep/put back in list
        return repository.findAll().stream().map(this::ReportToDto).toList();
    }

    public ReportDTO create(ReportWOIDDTO dto){
        Report entity = new Report(dto.reportTitle(), dto.reportStatus());
        return ReportToDto(repository.save(entity));
    }

    public ReportDTO getById(String id){
        Optional<Report> res = repository.findById(id);
        return (res.isEmpty()) ? null : ReportToDto(res.get());
    }

    //update
    public ReportDTO update(String id, ReportDTO dto){
        Report report = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        report.setReportTitle(dto.reportTitle());
        report.setReportStatus(dto.reportStatus());
        report.getReportExpenses().clear();

        //System.out.println("Report");
        //System.out.println(report);

        for(ExpenseDTO e: dto.reportExpenses()){
            Expense expense = DTOtoExpense(e);
            expense.setReport(report);
            report.getReportExpenses().add(expense);
        }

        //System.out.println(report);

        Report saved = repository.save(report);

        //System.out.println(saved);

        return ReportToDto(saved);
    }

    //delete
    public void delete(String id){
        repository.deleteById(id);
    }


    private ExpenseDTO ExpenseToDto(Expense expense){
        return new ExpenseDTO(expense.getId(), expense.getDate(), expense.getValue(), expense.getMerchant());
    }

    private ReportDTO ReportToDto(Report report){
        return new ReportDTO(report.getReportId(), report.getReportTitle(),
                report.getReportStatus(),
                report.getReportExpenses().stream().map(this::ExpenseToDto).toList());
    }


}
