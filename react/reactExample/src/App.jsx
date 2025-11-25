import ExpenseForm from './components/ExpenseForm';
import ExpenseItem from './components/Expenses/ExpenseItem';
import ExpenseList from './components/Expenses/ExpenseList';
import ExpenseFilter from './components/Expenses/ExpenseFilter';
import { useEffect, useState } from 'react';
import ReportSummary from './components/ReportSummary';
import SavedReportLists from './components/SavedReportLists';
//import './App.css'

const dummyExpenses = []

function App() {
  const [expenses, setExpenses] = useState(dummyExpenses);
  const [filteredYear, setFilteredYear] = useState('2023');
  const [selectedIds, setSelectedIds] = useState([]);
  const [savedReports, setSavedReports] = useState(() => {
    try{
    const savedReports = localStorage.getItem('savedReports');
    return savedReports ? JSON.parse(savedReports) : [];
  } catch (error) {
    console.warn("failed to load reports from localStorage:", error);
    return [];
  }
});
  useEffect(() => {
    localStorage.setItem('savedReports', JSON.stringify(savedReports));
  }, [savedReports]);

  const deleteReportHandler = (id) => {
    setSavedReports((prevReports) =>  prevReports.filter(report=> report.id !== id));
  };


  const addExpenseHandler = (expense) => {
    const expenseWithId = { ...expense, id: Math.random().toString() }

    setExpenses((prevExpenses) => {
      return [expenseWithId, ...prevExpenses]
      //return [expenseWithId, prevExpenses]
    })
  };

  const filterChangeHandler = (selectedYear) => {
    setFilteredYear(selectedYear);
  };

  const toggleExpenseHandler = (id) => {
    setSelectedIds((prevSelectedIds) => {
      if (prevSelectedIds.includes(id)) {
        return prevSelectedIds.filter((id) => id !== id);
      } else {
        return [...prevSelectedIds, id];
      }
    });
  }

  const saveReportHandler = (report) => {
    setSavedReports((prevReports) => {
      return [report, ...prevReports];
      setSelectedIds([]);
    }
    )
  };

  const filteredExpenses = expenses.filter((expense) => {
    return expense.date.getFullYear().toString() === filteredYear;
  });

  const reportExpenses = expenses.filter((expense) => selectedIds.includes(expense.id));

  return (
    <div className="min-h-screen bg-slate-900 px-4 font-sans">
      <h1 className="text-3xl text-slate-100 font-bold"> testing output </h1>
      <ExpenseFilter selected={filteredYear} onChangeFilter={filterChangeHandler} />
      <ExpenseForm onSaveExpenseData={addExpenseHandler} />
      <ExpenseList items={filteredExpenses} selectedIds={selectedIds} onToggleItem={toggleExpenseHandler} />
      <ReportSummary selectedExpenses={reportExpenses} closeHandler={() => setSelectedIds([])} onSave={saveReportHandler} />
      <SavedReportLists reports={savedReports} onDelete={deleteReportHandler} />
    </div>
  )
}

export default App
