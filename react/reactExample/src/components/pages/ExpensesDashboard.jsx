import ExpenseFilter from "../Expenses/ExpenseFilter";
import ExpenseForm from "./../ExpenseForm";
import ExpenseList from "./../Expenses/ExpenseList";    
import ReportSummary from "./../ReportSummary";
const ExpensesDashboard = ({ filteredExpenses, selectedIds, setSelectedIds, toggleExpenseHandler, filteredYear, filterChangeHandler, addExpenseHandler, reportExpenses, saveReportHandler}) => {
    return (
        <div>
            <h1>Expenses Dashboard</h1>
            <div className="min-h-screen bg-slate-900 px-4 font-sans">
                <h1 className="text-3xl text-slate-100 font-bold"> testing output </h1>
                <ExpenseFilter selected={filteredYear} onChangeFilter={filterChangeHandler} />
                <ExpenseForm onSaveExpenseData={addExpenseHandler} />
                <ExpenseList items={filteredExpenses} selectedIds={selectedIds} onToggleItem={toggleExpenseHandler} />
                <ReportSummary selectedExpenses={reportExpenses} closeHandler={() => setSelectedIds([])} onSave={saveReportHandler} />
            </div>
        </div>
    );
}

export default ExpensesDashboard;