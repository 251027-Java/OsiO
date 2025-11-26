import ExpenseForm from './components/ExpenseForm';
import ExpenseItem from './components/Expenses/ExpenseItem';
import ExpenseList from './components/Expenses/ExpenseList';
import ExpenseFilter from './components/Expenses/ExpenseFilter';
import { useEffect, useState } from 'react';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'; 
import ReportSummary from './components/ReportSummary';
import SavedReportLists from './components/SavedReportLists';
import ExpensesService from './services/ExpensesService';
import Navigation from './components/pages/Navigation';
import ExpensesDashboard from './components/pages/ExpensesDashboard';
import SavedReportsPage from './components/pages/savedReportsPage';
//import './App.css'

function App() {
  const navigate = useNavigate();
  const [expenses, setExpenses] = useState([]);
  const [filteredYear, setFilteredYear] = useState('2023');
  const [selectedIds, setSelectedIds] = useState([]);
  const [savedReports, setSavedReports] = useState(() => {
    try {
      const savedReports = localStorage.getItem('savedReports');
      return savedReports ? JSON.parse(savedReports) : [];
    } catch (error) {
      console.warn("failed to load reports from localStorage:", error);
      return [];
    }
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    localStorage.setItem('savedReports', JSON.stringify(savedReports));
  }, [savedReports]);

  useEffect(() => {

    async function fetchExpenses() {
      setIsLoading(true);
      setError(null);

      try {
        const data = await ExpensesService.getAll();
        const transformedData = data.map((item) => ({
          ...item,
          date: new Date(item.date)
        }));
        setExpenses(transformedData);
      } catch (error) {
        console.warn("Error fetching expenses from server!", error);
        setError(error.message);
        //default 
      } finally {
        console.log("finally");
        setIsLoading(false);
      }
    }
    fetchExpenses();
  }, []);

  const deleteReportHandler = (id) => {
    setSavedReports((prevReports) => prevReports.filter(report => report.id !== id));
  };


  const addExpenseHandler = async (expense) => {
    setIsLoading(true);
    setError(null);
    try {
      //run post request to get back json of new expense with id
      const newExpenseData = await ExpensesService.postExpense(expense);
      //unpack json and add date
      const expenseWithDate = { ...newExpenseData, date: new Date(newExpenseData.date) };
      //add new expense to list of old expenses
      setExpenses((prev) => [expenseWithDate, ...prev]);
    } catch (error) {
      setError('Failed to save expense! ' + error.message);
    } finally {
      setIsLoading(false);
    }
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
    <div>
      <Navigation />
      <Routes>
        <Route
          path="/dashboard"
          element= {<ExpensesDashboard
            expenses={expenses}
            filteredExpenses={filteredExpenses} 
            selectedIds={selectedIds} 
            setSelectedIds={setSelectedIds}
            toggleExpenseHandler={toggleExpenseHandler} 
            filteredYear={filteredYear} 
            filterChangeHandler={filterChangeHandler}
            addExpenseHandler={addExpenseHandler}
            reportExpenses={reportExpenses} 
            saveReportHandler={saveReportHandler}
            />}/>
          <Route
          path="/reports"
          element= {<SavedReportsPage savedReports={savedReports} deleteReportHandler={deleteReportHandler}/>}/>
          <Route
          path="/"
          element= {<div>
            <Link to="/dashboard">Go to Dashboard</Link>
            </div>}
            />
      </Routes>
    {/* <div className="min-h-screen bg-slate-900 px-4 font-sans">
      <h1 className="text-3xl text-slate-100 font-bold"> testing output </h1>
      <ExpenseFilter selected={filteredYear} onChangeFilter={filterChangeHandler} />
      <ExpenseForm onSaveExpenseData={addExpenseHandler} />
      <ExpenseList items={filteredExpenses} selectedIds={selectedIds} onToggleItem={toggleExpenseHandler} />
      <ReportSummary selectedExpenses={reportExpenses} closeHandler={() => setSelectedIds([])} onSave={saveReportHandler} />
    </div>*/}
    </div> 
  )
}

export default App
