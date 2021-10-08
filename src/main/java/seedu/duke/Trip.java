package seedu.duke;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trip {

    private LocalDate dateOfTrip;
    private ArrayList<Expense> listOfExpenses = new ArrayList<>();
    private ArrayList<Person> listOfPersons = new ArrayList<>();
    private double budget;
    private double exchangeRate;
    private String foreignCurrency;
    private String repaymentCurrency;
    private String location;

    public Trip() {
        //empty constructor
    }

    public Trip(String[] newTripInfo) {
        this.location = newTripInfo[0];
        this.listOfPersons = Parser.splitPeople(newTripInfo[2]);
        setExchangeRateString(newTripInfo[3]);
        setDateOfTrip(newTripInfo[1]);
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
            System.out.print("Please check that your date-time format is dd-MM-yyyy. " +
                    "Please enter the date again: ");
            Scanner scanner = new Scanner(System.in);
            setDateOfTrip(scanner.nextLine().strip());
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

    public void setExchangeRateString(String exchangeRateString) {
        try {
            this.exchangeRate = Double.parseDouble(exchangeRateString);
        } catch (NumberFormatException e) {
            System.out.print("Please re-enter your exchange rate as a decimal number: ");
            Scanner scanner = new Scanner(System.in);
            setExchangeRateString(scanner.nextLine().strip());
        }
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
