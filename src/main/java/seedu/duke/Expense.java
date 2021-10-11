package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Constructor requires a Person class which is the user, amount spent, and a description.
 * printDate prints out a nicely formatted date.
 * getExpenseSummary assumes user pays the bill first, and expense is equally split among his friends.
 */
public class Expense {
    private double amountSpent;
    private String description;
    private String location;
    private final ArrayList<Person> personsList;
    private final ArrayList<String> categoriesList;
    private LocalDate date;

    public Expense(Double amountSpent, String description, ArrayList<Person> listOfPersons) {
        this.amountSpent = amountSpent;
        this.date = LocalDate.now();
        this.description = description;
        this.personsList = new ArrayList<Person>();
        this.categoriesList = new ArrayList<String>();
        for (Person p : listOfPersons) {
            addPerson(p);
        }
    }

    public double getCostPerPerson() {
        return amountSpent / personsList.size();
    }

    public void addPerson(Person p) {
        personsList.add(p);
    }

    public void addCategory(String category) {
        categoriesList.add(category);
    }

    public void printDate() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        System.out.println(formattedDate);
    }


    //expense details
    @Override
    public String toString() {
        return (this.getDescription()
                + " at "
                + this.getLocation()
                + System.lineSeparator()
                + "date: " + this.getDate()
                + System.lineSeparator()
                + "Amount Spent: " + this.getAmountSpent()
                + System.lineSeparator()
                + "People involved: " + this.getPersonsList().toString()
                + System.lineSeparator()
                + "Categories involved: " + this.getCategoriesList().toString());
    }

    //Getters and setters

    public ArrayList<Person> getPersonsList() {
        return personsList;
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
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
