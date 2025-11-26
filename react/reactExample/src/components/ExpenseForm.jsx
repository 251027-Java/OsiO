import { useState } from "react";


const ExpenseForm = (prop) => {
    //we want to ...
    //accept a new expense from the user
    //with a title, number, date
    //and add it to list (and create a new id key for it)
    const [enteredTitle, setEnteredTitle] = useState('');
    const [enteredAmount, setEnteredAmount] = useState('');
    const [enteredDate, setEnteredDate] = useState('');
    const titleChangeHandler = (event) => {
        setEnteredTitle(event.target.value);
    };
    const amountChangeHandler = (event) => {
        setEnteredAmount(event.target.value);
    };
    const dateChangeHandler = (event) => {
        setEnteredDate(event.target.value);
    };

    const submitHandler = (event) => {
        event.preventDefault();
        //const dateWithTime = enteredDate ? `${enteredDate}T00:00:00` : new Date().toISOString();
        const expenseData = {
            title: enteredTitle,
            amount: enteredAmount,
            date: new Date(`${enteredDate}T00:00:00`)
        };
        console.log(expenseData);
        prop.onSaveExpenseData(expenseData);
    };

    return (
        <div className="w-full mx-auto max-w-2xl bg-indigo-600 p-6 rounded-2xl shadow-lg ">
            <form onSubmit={submitHandler}>
                <div className="grid grid-cols-2 gap-4 mb-4">
                    <h3 className="text-white font-bold text-lg"> New Expense Form</h3>
                    {/*title, amount, date inputs here*/}
                    <div className="col-span-2 gap-4 mb-4">
                        <label className="block text-indigo-100 text-sm font-bold">Title</label>
                        <input
                            type="text"
                            value={enteredTitle}
                            onChange={titleChangeHandler}
                            placeholder="e.g. Fuel"
                            className="w-full bg-white px-3 py-2 rounded-lg text-slate-800 focus:outline-none focus:ring-2 focus:ring-indigo-300"
                            required
                        />
                    </div>

                    <div className="col-span-2 sm: col-span-1">
                        <label className="block text-indigo-100 text-sm font-bold">Amount</label>
                        <input
                            type="number"
                            min="0.01"
                            step="0.01"
                            value={enteredAmount}
                            onChange={amountChangeHandler}
                            placeholder="0.00"
                            className="w-full bg-white px-3 py-2 rounded-lg text-slate-800 focus:outline-none focus:ring-2 focus:ring-indigo-300"
                            required
                        />
                    </div>

                    <div className="cols-1">
                        <label className="block text-indigo-100 text-sm font-bold">Date</label>
                        <input
                            type="date"
                            min="2022-01-01"
                            max="2035-12-31"
                            value={enteredDate}
                            onChange={dateChangeHandler}
                            className="w-full bg-white px-3 py-2 rounded-lg text-slate-800 focus:outline-none focus:ring-2 focus:ring-indigo-300"
                            required
                        />
                    </div>

                    <div className="text-right">
                        <button
                            type="submit"
                            className="bg-indigo-900 text-white font-bold rounded-lg py-2 px-6 border border-indigo-700 hover:bg-indigo-800 hover:border-indigo-800 transition-colors border border-indigo-700"
                        >Add Expense</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default ExpenseForm;