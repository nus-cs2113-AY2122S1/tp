package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Trip {

    private LocalDate dateOfTrip;
    private ArrayList<Expense> listOfExpenses = new ArrayList<>();
    private ArrayList<Person> listOfPersons = new ArrayList<>();
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
        this.listOfPersons = splitPeople(newTripInfo[4]);
    }

    //@@author leeyikai
    public void getFilteredExpenses(String expenseCategory, String expenseAttribute) {

        if (listOfExpenses.isEmpty()) {
            Ui.printNoExpensesError();
            return;
        }
        try {
            switch (expenseCategory) {
            case "category":
                findMatchingCategoryExpenses(listOfExpenses, expenseAttribute);
                break;
            case "description":
                findMatchingDescriptionExpenses(listOfExpenses, expenseAttribute);
                break;
            case "payer":
                findMatchingPayerExpenses(listOfExpenses, expenseAttribute);
                break;
            case "person":
                findMatchingPersonExpenses(listOfExpenses, expenseAttribute);
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
        boolean areThereExpenses = false;
        for (Expense e : listOfCurrentExpenses) {
            if (e.getPayer().getName().equalsIgnoreCase(expenseAttribute)) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                areThereExpenses = true;
            }
        }
        if (!areThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }

    private static void findMatchingDescriptionExpenses(ArrayList<Expense> listOfCurrentExpenses,
                                                        String expenseAttribute) {
        boolean areThereExpenses = false;
        String descriptionToLowerCase;
        String attributeToLowerCase = expenseAttribute.toLowerCase();
        for (Expense e : listOfCurrentExpenses) {
            descriptionToLowerCase = e.getDescription().toLowerCase();
            if (descriptionToLowerCase.contains(attributeToLowerCase)) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                areThereExpenses = true;
            }
        }
        if (!areThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }

    private static void findMatchingCategoryExpenses(ArrayList<Expense> listOfCurrentExpenses,
                                                     String expenseAttribute) {
        boolean areThereExpenses = false;
        for (Expense e : listOfCurrentExpenses) {
            if (e.getCategory().equalsIgnoreCase(expenseAttribute)) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                areThereExpenses = true;
            }
        }
        if (!areThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }
    //@@author

    private static void findMatchingPersonExpenses(ArrayList<Expense> listOfCurrentExpenses,
                                                   String personToSearchFor) {
        boolean areThereExpenses = false;
        for (Expense e : listOfCurrentExpenses) {
            boolean isExpenseToBeAdded = false;
            ArrayList<Person> personList = e.getPersonsList();
            for (Person p : personList) {
                if (p.getName().equalsIgnoreCase(personToSearchFor)) {
                    isExpenseToBeAdded = true;
                    break;
                }
            }
            if (isExpenseToBeAdded) {
                int index = listOfCurrentExpenses.indexOf(e);
                Ui.printFilteredExpenses(e, index);
                areThereExpenses = true;
            }
        }
        if (!areThereExpenses) {
            Ui.printNoMatchingExpenseError();
        }
    }


    public void getIndividualExpenseSummary(Person person) {
        double currentAmount; //amount paid for current expense
        double totalAmountSpent = 0;
        int expensesInvolved = 0; //num of expenses involved
        HashMap<String, Double> categoriesSplit = new HashMap<>(); //contains the amount spent in each category
        for (Expense e : listOfExpenses) {
            if (e.getPersonsList().contains(person)) {
                currentAmount = e.getAmountSplit().get(person.getName());
                String currentCategory = e.getCategory();
                totalAmountSpent += currentAmount;
                expensesInvolved++;
                //the following if else is to update the category/amtSpent hashmap
                if (!categoriesSplit.containsKey(currentCategory)) {
                    categoriesSplit.put(currentCategory, currentAmount);
                } else {
                    double updatedValue = categoriesSplit.get(currentCategory) + currentAmount;
                    categoriesSplit.put(currentCategory, updatedValue);
                }
            }
        }
        System.out.println(person + " has spent "
                + Ui.stringForeignMoney(totalAmountSpent)
                + " (" + Ui.stringRepaymentMoney(totalAmountSpent) + ")"
                + " on "
                + expensesInvolved
                + " expenses in the following split: ");
        for (Map.Entry<String, Double> set : categoriesSplit.entrySet()) {
            System.out.println(set.getKey() + ": " + Ui.stringForeignMoney(set.getValue())
                    + " (" + Ui.stringRepaymentMoney(totalAmountSpent) + ")");
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


    //@@author joshualeeky
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
        setRepaymentCurrencySymbol(this.repaymentCurrency);

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

    //@@author
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
        listOfExpenses.sort(Comparator.comparing(Expense::getDate));
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
            System.out.println("List of all Expenses in detail: ");
            for (Expense expense : listOfExpenses) {
                System.out.print(listOfExpenses.indexOf(expense) + 1 + ". ");
                Ui.printExpenseDetails(expense);
            }
        }
    }


    public Expense getExpenseAtIndex(Integer index) {
        return listOfExpenses.get(index - 1);
    }


    //@@author joshualeeky

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
                person.getMoneyOwed().put(personToAdd.getName(), 0.0); // Remove .getName()
            }
        }
        return listOfPeople;
    }
}
