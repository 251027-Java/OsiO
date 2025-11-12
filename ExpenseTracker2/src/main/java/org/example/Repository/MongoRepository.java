package org.example.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;
import org.example.Expense;
import org.bson.Document;
import org.example.Repository.IRepository;


public class MongoRepository implements IRepository {
    // Fields
                                    // "[language]://[username]:[password]@[host]/[database]?options]"
    private final String URL = "mongodb://mongoadmin:secret@localhost:27017";
    private final MongoCollection<Document> expensesCollection;
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructor
    public MongoRepository() {
        mongoClient = MongoClients.create(URL);
        database = mongoClient.getDatabase("expensesdb");
        this.expensesCollection = database.getCollection("expenses");
        IO.println("Successful creation of MongoDB database!");
        /*try{
        mongoClient = MongoClients.create(URL);
        database = mongoClient.getDatabase("expensesdb");
        this.expensesCollection = database.getCollection("expenses");
        IO.println("Successful creation of MongoDB database!");
        } catch (Exception e){
            e.printStackTrace();
    */
    }

    //methods
    private Expense documentToExpense(Document doc) {
        return new Expense(doc.getInteger("_id"), 
            doc.getDate("date"), 
            doc.getDouble("value"), 
            doc.getString("merchant"));
    }

    private Document expenseToDocument(Expense expense) {
        return new Document("_id", expense.getId())
                .append("date", expense.getDate())
                .append("value", expense.getValue())
                .append("merchant", expense.getMerchant());
    }

    @Override
    public void createExpense(Expense expense) {
        Document expenseDoc = expenseToDocument(expense);
        expensesCollection.insertOne(expenseDoc);
    }

    @Override
    public void deleteExpense(int id) { 
        expensesCollection.deleteOne(Filters.eq("_id", id));       
    }

    @Override
    public List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        for (Document doc : expensesCollection.find()){
            expenses.add(documentToExpense(doc));
        }
        return expenses;
    }

    @Override
    public Expense readExpense(int id) {
        Document doc = expensesCollection.find(Filters.eq("_id", id)).first();
        return (doc != null) ? documentToExpense(doc) : null;
    }

    

    @Override
    public void updateExpense(Expense expense) {     
        Document doc = expenseToDocument(expense);
        expensesCollection.updateOne(Filters.eq("_id", expense.getId()), doc);   
    }

    @Override
    public void saveExpenses(List<Expense> expenses) {        
    }

}


