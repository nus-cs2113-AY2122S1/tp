package seedu.duke;

import java.util.ArrayList;

public class Person {
    String name;
    boolean isUser;
    float amtOwedToUser;
    ArrayList <Expense> listOfExpenses = new ArrayList<>();

    public Person(String name, Boolean isUser){
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

    public void setAmtOwedToUser(float amount){
        amtOwedToUser += amount;
    }

    public float getTotalExpenditure(){
        float total = 0;
        for (Expense Expense : listOfExpenses){
//            total += Expense.getAmountSpent() / Expense.getPersonsInvolved().size(); //Assuming everyone pays equally
        }
        return total;
    }

}
