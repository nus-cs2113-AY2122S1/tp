package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Trip {

    private LocalDate dateOfTrip;
    private ArrayList<Expense> listOfExpenses = new ArrayList<>();
    private ArrayList<Person> listOfPersons = new ArrayList<>();
    //private double budget; //may not be needed anymore
    private double exchangeRate;
    private String foreignCurrency;
    private String foreignCurrencyFormat;
    private String foreignCurrencySymbol;
    private String repaymentCurrency = "SGD";
    private String repaymentCurrencyFormat = "%.02f";
    private String repaymentCurrencySymbol = "$";
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
        assert newTripInfo.length == 5;
        this.location = newTripInfo[0];
        setDateOfTrip(newTripInfo[1]);
        setForeignCurrency(newTripInfo[2].toUpperCase());
        setExchangeRate(newTripInfo[3]);
        //setBudget(newTripInfo[3]);
        this.listOfPersons = splitPeople(newTripInfo[4]);
    }

    public static void getFilteredExpenses(String expenseCategory, String expenseAttribute) {
        Trip currentTrip = Storage.getOpenTrip();
        ArrayList<Expense> listOfCurrentExpenses = currentTrip.getListOfExpenses();
        if (listOfCurrentExpenses.isEmpty()) {
            Ui.printNoExpensesError();
            return;
        }
        try {
            switch (expenseCategory) {
            case "category":
                findMatchingCategoryExpenses(listOfCurrentExpenses, expenseAttribute);
                break;
            case "description":
                findMatchingDescriptionExpenses(listOfCurrentExpenses, expenseAttribute);
                break;
            case "payer":
                findMatchingPayerExpenses(listOfCurrentExpenses, expenseAttribute);
                break;
            default:
                Ui.printInvalidFilterError();
                break;
            }

        } catch (IndexOutOfBoundsException e) {
            Ui.printFilterFormatError();
        }

    }

    private static void findMatchingPayerExpenses(ArrayList<Expense> listOfCurrentExpenses, String expenseAttribute) {
        boolean isThereExpenses = false;
        for (Expense e : listOfCurrentExpenses) {
            if (e.getPayer().getName().equals(expenseAttribute)) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                isThereExpenses = true;
            }
        }
        if (isThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }

    private static void findMatchingDescriptionExpenses(ArrayList<Expense> listOfCurrentExpenses,
                                                        String expenseAttribute) {
        boolean isThereExpenses = false;
        String descriptionToLowerCase;
        String attributeToLowerCase = expenseAttribute.toLowerCase();
        for (Expense e : listOfCurrentExpenses) {
            descriptionToLowerCase = e.getDescription().toLowerCase();
            if (descriptionToLowerCase.contains(attributeToLowerCase)) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                isThereExpenses = true;
            }
        }
        if (isThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }

    private static void findMatchingCategoryExpenses(ArrayList<Expense> listOfCurrentExpenses,
                                                     String expenseAttribute) {
        boolean isThereExpenses = false;
        for (Expense e : listOfCurrentExpenses) {
            if (e.getCategory().equals(expenseAttribute)) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                isThereExpenses = true;
            }
        }
        if (isThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }

    public LocalDate getDateOfTrip() {
        return dateOfTrip;
    }

    public String getDateOfTripString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return getDateOfTrip().format(pattern);
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
            Ui.printDateTimeFormatError();
            Scanner scanner = Storage.getScanner();
            setDateOfTrip(scanner.nextLine().strip());
        }
    }

    /*public double getBudget() {
        return this.budget;
    }

    public void setBudget(String budget) {
        try {
            this.budget = Double.parseDouble(budget);
        } catch (NumberFormatException e) {
            Ui.printBudgetFormatError();
            Scanner scanner = Storage.getScanner();
            setBudget(scanner.nextLine().strip());
        }
    }*/

    public double getExchangeRate() {
        return exchangeRate;
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
            Ui.printExchangeRateFormatError();
            Scanner scanner = Storage.getScanner();
            setExchangeRate(scanner.nextLine().strip());
        }
    }

    public double getTotalExpenses() {
        double totalExpense = 0;
        for (Expense currentExpense : listOfExpenses) {
            totalExpense += currentExpense.getAmountSpent();
        }
        return totalExpense;
    }

    /*public double getBudgetLeft() {
        return getBudget() - getTotalExpenses();
    }*/


    public String getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(String foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
        setForeignCurrencyFormat(this.foreignCurrency);
        setForeignCurrencySymbol(this.foreignCurrency);
    }

    private void setForeignCurrencyFormat(String input) {
        if (Storage.getAvailableCurrency().containsKey(input)) {
            this.foreignCurrencyFormat = Storage.getAvailableCurrency().get(input)[1];
        } else {
            this.foreignCurrencyFormat = "%.02f";
        }
    }

    private void setForeignCurrencySymbol(String input) {
        if (Storage.getAvailableCurrency().containsKey(input)) {
            this.foreignCurrencySymbol = Storage.getAvailableCurrency().get(input)[0];
        } else {
            this.foreignCurrencySymbol = "";
        }
    }

    public String getForeignCurrencyFormat() {
        return foreignCurrencyFormat;
    }

    public String getForeignCurrencySymbol() {
        return foreignCurrencySymbol;
    }

    public String getRepaymentCurrency() {
        return repaymentCurrency;
    }

    public void setRepaymentCurrency(String repaymentCurrency) {
        this.repaymentCurrency = repaymentCurrency.toUpperCase();
        setRepaymentCurrencyFormat(this.repaymentCurrency);
        setForeignCurrencySymbol(this.repaymentCurrency);

    }

    private void setRepaymentCurrencyFormat(String input) {
        if (Storage.getAvailableCurrency().containsKey(input)) {
            this.repaymentCurrencyFormat = Storage.getAvailableCurrency().get(input)[1];
        } else {
            this.repaymentCurrencyFormat = "%.02f";
        }
    }

    public String getRepaymentCurrencyFormat() {
        return repaymentCurrencyFormat;
    }

    public String getRepaymentCurrencySymbol() {
        return repaymentCurrencySymbol;
    }

    private void setRepaymentCurrencySymbol(String input) {
        if (Storage.getAvailableCurrency().containsKey(input)) {
            this.repaymentCurrencySymbol = Storage.getAvailableCurrency().get(input)[0];
        } else {
            this.repaymentCurrencySymbol = "";
        }
    }

    public String getLocation() {
        return this.location;
    }

    public ArrayList<Person> getListOfPersons() {
        return listOfPersons;
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

    public ArrayList<Expense> getListOfExpenses() {
        return listOfExpenses;
    }

    public void removeExpense(Integer i) {
        listOfExpenses.remove(listOfExpenses.get(i));
    }

    public void viewAllExpenses() {
        if (listOfExpenses.isEmpty()) {
            Ui.printNoExpensesError();
        } else {
            for (Expense expense : listOfExpenses) {
                Ui.printExpenseDetails(expense);
                System.out.println();
            }
        }
    }

    /**
     * Splits the user-entered {@link String} of people involved in a trip into a String array.
     *
     * @param peopleChained String of all persons involved in the trip
     * @return {@link String} array, each element of the array being a person involved in the trip
     */
    private ArrayList<Person> splitPeople(String peopleChained) {
        ArrayList<Person> listOfPeople = new ArrayList<>();
        for (String personName : peopleChained.split(",")) {
            Person person = new Person(personName.trim());
            listOfPeople.add(person);
        }
        for (Person person : listOfPeople) {
            for (Person personToAdd : listOfPeople) {
                person.getMoneyOwed().put(personToAdd, 0.0);
            }
        }
        return listOfPeople;

    }

}
