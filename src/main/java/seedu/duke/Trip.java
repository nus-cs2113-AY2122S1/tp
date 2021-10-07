package seedu.duke;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Trip {

    private LocalDate dateOfTrip;
    private ArrayList<Expense> listOfExpenses;
    private ArrayList<Person> listOfPersons;
    private double budget;
    private double exchangeRate;
    private String foreignCurrency;
    private String repaymentCurrency;
    private String location;

    public Trip() {
        //TODO: create non-empty constructor
    }

    public void getWhoOwesMe() {

        for (Person person : listOfPersons) {
            Ui.printWhoOwesMe(person);
        }

    }

    public LocalDate getDateOfTrip() {
        return dateOfTrip;
    }

    public String getDateOfTripString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateOfTrip.format(pattern);
    }

    public void setDateOfTrip(String dateOfTrip) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            this.dateOfTrip = LocalDate.parse(dateOfTrip, pattern);
        } catch (DateTimeParseException e) {
            //TODO: catch date exception
        }
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(String foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
    }

    public String getRepaymentCurrency() {
        return repaymentCurrency;
    }

    public void setRepaymentCurrency(String repaymentCurrency) {
        this.repaymentCurrency = repaymentCurrency;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPersonName(int indexOfPerson, String newName) {
        Person personToEdit = listOfPersons.get(indexOfPerson);
        personToEdit.setName(newName);
    }

    public void addExpense(Expense expense) {
        listOfExpenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        listOfExpenses.remove(expense);
    }

    public void viewAllExpenses() {
        for (Expense expense : listOfExpenses) {
            System.out.println(expense);
        }
    }

}
