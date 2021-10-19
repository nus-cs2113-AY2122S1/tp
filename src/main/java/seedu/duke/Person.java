package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    private String name;
    private ArrayList<Expense> listOfExpenses = new ArrayList<>();
    private HashMap<Person, Double> moneyOwed = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Expense> getListOfExpenses() {
        return listOfExpenses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExpense(Expense expense) {
        listOfExpenses.add(expense);
    }

    public HashMap<Person, Double> getMoneyOwed() {
        return this.moneyOwed;
    }

    public double getTotalExpenditure() {
        return moneyOwed.get(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
