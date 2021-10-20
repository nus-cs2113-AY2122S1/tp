package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Constructor requires a Person class which is the user, amount spent, and a description.
 * printDate prints out a nicely formatted date.
 * getExpenseSummary assumes user pays the bill first, and expense is equally split among his friends.
 */
public class Expense {
    private double amountSpent;
    private String description;
    private String location;
    private ArrayList<Person> personsList;
    private String category;
    private LocalDate date;
    private Person payer;
    private HashMap<Person, Double> amountSplit = new HashMap<>();

    public Expense(Double amountSpent, String category, ArrayList<Person> listOfPersons, String description) {
        this.amountSpent = amountSpent;
        this.date = LocalDate.now();
        this.description = description;
        this.category = category;
        this.personsList = listOfPersons;
        for (Person person : listOfPersons){
            amountSplit.put(person, 0.0);
        }
    }

    public void setPayer(Person person){
        this.payer = person;
    }

    public void setAmountSplit(Person person, double amount){
        amountSplit.put(person, amount);
    }

    public double getCostPerPerson() {
        return amountSpent / personsList.size();
    }

    public void addPerson(Person p) {
        personsList.add(p);
    }

    public void printDate() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        System.out.println(formattedDate);
    }


    //expense details
    @Override
    public String toString() {
        return (this.getDescription()
                + System.lineSeparator()
                + "date: " + this.getDate()
                + System.lineSeparator()
                + "Amount Spent: " + String.format("%.02f", this.getAmountSpent())
                + System.lineSeparator()
                + "People involved: " + this.getPersonsList().toString()
                + System.lineSeparator()
                + "Category: " + this.category);
    }

    //Getters and setters

    public ArrayList<Person> getPersonsList() {
        return personsList;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
