package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static seedu.duke.Parser.isNumeric;

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
    private static final DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final int ISO_LENGTH = 3;
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String PAYER = "payer";
    private static final String PERSON = "person";
    private static final String DATE = "date";

    /**
     * Empty {@link Trip} constructor (included for legacy test support).
     */
    public Trip() {
        //empty constructor
    }

    private static final int LOCATION_STRING = 1;
    private static final int DATE_STRING = 2;
    private static final int FORCUR_STRING = 3;
    private static final int EXRATE_STRING = 4;
    private static final int PERSONS_STRING = 5;

    /**
     * Non-empty {@link Trip} constructor. Reads in a String array and processes it to set attributes for a given Trip.
     *
     * @param newTripInfo array containing one attribute in each element
     */
    public Trip(String[] newTripInfo) throws ForceCancelException {
        assert newTripInfo.length == 6;
        setLocation(newTripInfo[LOCATION_STRING].strip());
        setDateOfTrip(newTripInfo[DATE_STRING].strip());
        setForeignCurrency(newTripInfo[FORCUR_STRING].strip().toUpperCase());
        setExchangeRate(newTripInfo[EXRATE_STRING].strip());
        setListOfPersons(splitPeople(newTripInfo[PERSONS_STRING]));
    }

    //@@author leeyikai
    public void getFilteredExpenses(String expenseCategory, String expenseAttribute) {

        if (listOfExpenses.isEmpty()) {
            Ui.printNoExpensesError();
            return;
        }
        try {
            switch (expenseCategory) {
            case CATEGORY:
                findMatchingCategoryExpenses(listOfExpenses, expenseAttribute);
                break;
            case DESCRIPTION:
                findMatchingDescriptionExpenses(listOfExpenses, expenseAttribute);
                break;
            case PAYER:
                findMatchingPayerExpenses(listOfExpenses, expenseAttribute);
                break;
            case PERSON:
                findMatchingPersonExpenses(listOfExpenses, expenseAttribute);
                break;
            case DATE:
                findMatchingDateExpenses(listOfExpenses, expenseAttribute);
                break;
            default:
                Ui.printInvalidFilterError();
                break;
            }

        } catch (IndexOutOfBoundsException e) {
            Ui.printFilterFormatError();
        } catch (ForceCancelException e) {
            Ui.printForceCancelled();
        }

    }
    //@@author

    //@@author lixiyuan416
    private void findMatchingDateExpenses(ArrayList<Expense> listOfCurrentExpenses, String expenseAttribute)
            throws ForceCancelException {
        boolean areThereExpenses = false;
        String inputDate = expenseAttribute;
        while (!isDateValid(inputDate)) {
            inputDate = Ui.receiveUserInput();
        }
        LocalDate dateToFind = LocalDate.parse(inputDate, inputPattern);
        for (Expense e : listOfCurrentExpenses) {
            if (e.getDate().isEqual(dateToFind)) {
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

    //@@author leeyikai
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

    //@@author lixiyuan416
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

    private boolean isDateValid(String inputDate) {
        try {
            if (Parser.doesDateReallyExist(inputDate)) {
                LocalDate.parse(inputDate, inputPattern);
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            Storage.getLogger().log(Level.INFO, "Invalid date format entered");
            Ui.viewFilterDateFormatInvalid();
            return false;
        }
    }
    //@@author

    //@@author itsleeqian
    public void getIndividualExpenseSummary(Person person) {
        double currentAmount; //amount paid for current expense
        double totalAmountSpent = 0;
        double totalAmountSpentInLocalCurrency = 0;
        Trip currTrip = Storage.getOpenTrip();
        int expensesInvolved = 0; //num of expenses involved
        HashMap<String, Double> categoriesSplit = new HashMap<>(); //contains the amount spent in each category
        for (Expense e : listOfExpenses) {
            if (containsPerson(e.getPersonsList(), person.getName())) {
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
        totalAmountSpentInLocalCurrency = roundToLocal(totalAmountSpentInLocalCurrency, currTrip, categoriesSplit);
        System.out.println(person + " has spent "
                + Ui.stringForeignMoney(totalAmountSpent)
                + " (" + currTrip.getRepaymentCurrency() + " "
                //+ currTrip.getRepaymentCurrencySymbol()
                + String.format(currTrip.getRepaymentCurrencyFormat(), totalAmountSpentInLocalCurrency) + ") on "
                + expensesInvolved
                + " expenses on the following categories:");
        for (Map.Entry<String, Double> set : categoriesSplit.entrySet()) {
            System.out.println(set.getKey() + ": " + Ui.stringForeignMoney(set.getValue())
                    + " (" + Ui.stringRepaymentMoney(set.getValue()) + ")");
        }
    }

    /**
     * Helper method for getIndividualExpenseSummary() method.
     * Returns the rounded and formatted total repayment amount spent.
     * @param totalAmount the amount before rounding
     * @param currTrip the Trip the user is in/computing
     * @param categoriesSplit the HashMap containing the category and the amount spent on said category
     * @return a rounded and formatted value for amount spent in local currency
     */
    private double roundToLocal(double totalAmount, Trip currTrip, HashMap<String, Double> categoriesSplit) {
        for (Map.Entry<String, Double> set : categoriesSplit.entrySet()) {
            totalAmount += Storage.formatRepaymentMoneyDouble(
                    set.getValue() / currTrip.getExchangeRate());
        }
        return totalAmount;
    }

    /**
     * Returns true if personsList contains a person with a specific name.
     * This is to replace the list.contains() method due to bugs with json deserialization.
     *
     * @param personsList list of persons to check
     * @param name the name to check for
     * @return true if personsList contains a person with a specific name
     */
    public boolean containsPerson(ArrayList<Person> personsList, String name) {
        for (Person person : personsList) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    //@@author


    public LocalDate getDateOfTrip() {
        return dateOfTrip;
    }

    /**
     * Returns the {@link LocalDate} object as a formatted string (with the format dd MMMM yy).
     *
     * @return the formatted date as a {@link String}.
     */
    public String getDateOfTripString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return getDateOfTrip().format(pattern);
    }

    /**
     * Parses a user input (in {@link String}) into a {@link LocalDate}.
     *
     * @param dateOfTrip user-entered date of trip as a String
     */
    public void setDateOfTrip(String dateOfTrip) throws ForceCancelException {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate date = LocalDate.parse(dateOfTrip, pattern);
            if (Parser.doesDateReallyExist(dateOfTrip)) {
                if (date.isBefore(LocalDate.EPOCH)) {
                    dateInvalid();
                } else {
                    this.dateOfTrip = date;
                }
            } else {
                dateInvalid();
            }
        } catch (DateTimeParseException e) {
            Ui.printDateTimeFormatError();
            userInputDateError();
        }
    }

    private void dateInvalid() throws ForceCancelException {
        Ui.dateInvalidError();
        userInputDateError();
    }

    public void userInputDateError() throws ForceCancelException {
        String newInput = Ui.receiveUserInput();
        setDateOfTrip(newInput);
    }


    public double getExchangeRate() {
        return exchangeRate;
    }


    /**
     * Parses an exchange rate entered by the user (as a {@link String}) into a {@link Double}.
     *
     * @param exchangeRateString user-entered exchange rate (as a String)
     */
    public void setExchangeRate(String exchangeRateString) throws ForceCancelException {
        try {
            this.exchangeRate = Double.parseDouble(exchangeRateString);
        } catch (NumberFormatException e) {
            Ui.printExchangeRateFormatError();
            String userInput = Ui.receiveUserInput();
            setExchangeRate(userInput);
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
    //@@author

    //@@author itsleeqian
    public void setForeignCurrency(String foreignCurrency) throws ForceCancelException {
        try {
            if (isNumeric(foreignCurrency) || foreignCurrency.length() != ISO_LENGTH) {
                throw new NumberFormatException();
            }
            this.foreignCurrency = foreignCurrency;
            setForeignCurrencyFormat(this.foreignCurrency);
            setForeignCurrencySymbol(this.foreignCurrency);
        } catch (NumberFormatException e) {
            Ui.printIsoFormatError();
            String userInput = Ui.receiveUserInput();
            setForeignCurrency(userInput);
        }
    }
    //@@author

    //@@author joshualeeky
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
            this.foreignCurrencySymbol = "$";
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


    public void setListOfPersons(ArrayList<Person> listOfPersons) throws ForceCancelException {
        if (listOfPersons.isEmpty()) {
            Ui.noPersonsAdded();
            String userInput = Ui.receiveUserInput();
            setListOfPersons(splitPeople(userInput));
            return;
        }
        this.listOfPersons = listOfPersons;
    }

    public ArrayList<Person> getListOfPersons() {
        return listOfPersons;
    }

    public void setLocation(String location) throws ForceCancelException {
        if (location.isBlank()) {
            Ui.locationIsBlank();
            setLocation(Ui.receiveUserInput());
        }
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
            if (personName.isBlank()) {
                continue;
            }
            Person person = new Person(personName.strip());
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
