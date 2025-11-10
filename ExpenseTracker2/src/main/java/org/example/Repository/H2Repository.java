package org.example.Repository;
import java.sql.*;
import java.util.List;

import org.example.Expense;
import org.example.Repository.IRepository;


public class H2Repository implements IRepository{
    //fields
    private static final String H2_URL = "jdbc:h2:mem:expenses;DB_CLOSE_DELAY=-1";
    private Connection connection;

    //constructor
    public H2Repository() {
        try {
            connection = DriverManager.getConnection(H2_URL);
            try (Statement stmt = connection.createStatement()) {
                connection = DriverManager.getConnection(H2_URL);
                String sql = "CREATE SCHEMA IF NOT EXISTS ExpenseReport;" +
                        "CREATE TABLE IF NOT EXISTS ExpenseReport.Expense(" +
                        "id INT PRIMARY KEY," +
                        "date TIMESTAMP NOT NULL," +
                        "price FLOAT CHECK (price > 0)," +
                        "merchant VARCHAR(50) NOT NULL" +
                        ");";

                stmt.execute(sql);
                IO.println("Succesful creation of H2 database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //methods
    @Override
    public void createExpense(Expense expense) {
        String sql = "INSERT INTO ExpenseReport.Expense (id, date, price, merchant) Values ( ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, expense.getId());
            stmt.setDate(2, new java.sql.Date(expense.getDate().getTime()));
            stmt.setDouble(3, expense.getValue());
            stmt.setString(4, expense.getMerchant());
            stmt.executeUpdate();
            IO.println("Expense Created Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExpense(int id) {
        //  DELETE FROM X WHERE Y
        // DELETE FROM ExpenseReport.Expenses WHERE id = id;
        String sql = "DELETE FROM ExpenseReport.Expense WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            int row = stmt.executeUpdate();
            if (row > 0) {
                IO.println("Successfully deleted row with expense id of " + id);
            } else {
                IO.println("Expense id " + id + " does not exist");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Expense readExpense(int id) {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {}

    @Override
    public List<Expense> loadExpenses() {
        return List.of();
    }

    @Override
    public void saveExpenses(List<Expense> expenses) {}

}
