package seedu.duke;

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

    /**
     * Non-empty {@link Trip} constructor. Reads in a String array and processes it to set attributes for a given Trip.
     *
     * @param newTripInfo array containing one attribute in each element
     */
    public Trip(String[] newTripInfo) {
        this.location = newTripInfo[0];
        setDateOfTrip(newTripInfo[1]);
        this.listOfPersons = Parser.splitPeople(newTripInfo[2]);
        setExchangeRate(newTripInfo[3]);
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

    /**
     * Parses a user input (in {@link String}) into a {@link LocalDate}.
     *
     * @param dateOfTrip user-entered date of trip as a String
     */
    public void setDateOfTrip(String dateOfTrip) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            this.dateOfTrip = LocalDate.parse(dateOfTrip, pattern);
        } catch (DateTimeParseException e) {
            System.out.print("Please check that your date-time format is dd-MM-yyyy. "
                    + "Please enter the date again: ");
            Scanner scanner = Storage.getScanner();
            setDateOfTrip(scanner.nextLine().strip());
        }
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setBudget(String budget) {
        try {
            this.budget = Double.parseDouble(budget);
        } catch (NumberFormatException e) {
            System.out.print("Please re-enter your exchange rate as a decimal number (e.g. 1.32): ");
            Scanner scanner = Storage.getScanner();
            setBudget(scanner.nextLine().strip());
        }
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * Parses an exchange rate entered by the user (as a {@link String}) into a {@link Double}.
     *
     * @param exchangeRateString user-entered exchange rate (as a String)
     */
    public void setExchangeRate(String exchangeRateString) {
        try {
            this.exchangeRate = Double.parseDouble(exchangeRateString);
        } catch (NumberFormatException e) {
            System.out.print("Please re-enter your exchange rate as a decimal number (e.g. 1.32): ");
            Scanner scanner = Storage.getScanner();
            setExchangeRate(scanner.nextLine().strip());
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
