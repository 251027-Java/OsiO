import ExpeneseItem from "./ExpenseItem";

const ExpenseList = ({ items, selectedIds, onToggleItem }) => {
    if (items.length === 0) {
        return <h2 className="text-center text-slate-700 font-bold text-xl"> No expenses found. </h2>
    };

    return (
        <div className="w-full mx-auto bg-slate-200 p-6 rounded-2xl shadow inner">
            {items.map((expense) => (
                <ExpeneseItem
                    key={expense.id}
                    id={expense.id}
                    title={expense.title}
                    amount={expense.amount}
                    date={expense.date}
                    isSelected={selectedIds.includes(expense.id)}
                    onToggle={onToggleItem} />
            ))}
        </div>
    );
};

export default ExpenseList;
