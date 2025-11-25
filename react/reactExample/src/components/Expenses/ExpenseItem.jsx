import ExpenseDate from "./ExpenseDate";
const ExpeneseItem = ({ id, title, amount, date, isSelected, onToggle }) => {
    return (
        <div className = {`flex items-center justify-between bg-white rounded-xl p-3 my-3 border-l-4 shadow-md hover:shadow-md tansition-all duration-300 ${isSelected ? 'bg-indigo-50 border-indigo-600 ring-2 ring-indigo-200' : 'bg-white border-transparent hover:shadow-md'}`}>
            {/*checkbox for report*/}
            <div className ="flex items-center">
                <input type="checkbox" checked ={isSelected} onChange = {() => onToggle(id)}/>
            </div>
            <div>
                <ExpenseDate date={date}/>
            </div>
            <h1>{title}</h1>
            <h2>{amount}</h2>
        </div>
    );
};

export default ExpeneseItem;