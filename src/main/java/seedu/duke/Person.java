package seedu.duke;

import java.util.ArrayList;

public class Person {
    private String name;
    private boolean isUser;
    private float amtOwedToUser;
    private ArrayList<Expense> listOfExpenses = new ArrayList<>();

    public Person(String name, Boolean isUser) {
        this.name = name;
        this.isUser = isUser;
        amtOwedToUser = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Expense> getListOfExpenses() {
        return listOfExpenses;
    }

    public boolean getIsUser() {
        return isUser;
    }

    public float getAmtOwedToUser() {
        return amtOwedToUser;
    }

    public void setAmtOwedToUser(float amount) {
        amtOwedToUser += amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExpense(Expense expense) {
        listOfExpenses.add(expense);
    }

    public float getTotalExpenditure() {
        float total = 0;
        for (Expense expense : listOfExpenses) {
            total += expense.getCostPerPerson(); //Assuming everyone pays equally
        }
        return total;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
