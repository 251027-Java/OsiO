const ExpensesService = {
    baseUrl: "http://localhost:3000/expenses",
    //Get, Put, Host, Patch, Delete
    async getAll() {
        console.log(this.baseUrl);
        const response = await fetch(this.baseUrl);
        console.log(response);
        if (!response.ok) throw new Error("Failed to fetch expenses");
        return response.json();
    },
    async postExpense(expense) {
        const response = await fetch(this.baseUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(expense)
        });
        if (!response.ok) throw new Error("Failed to save expense");
        return response.json();
    },
};
export default ExpensesService;