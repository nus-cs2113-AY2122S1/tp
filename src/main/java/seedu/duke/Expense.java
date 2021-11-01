package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

public class Expense extends ExpenseSplittingFunctions {
    private double amountSpent;
    private String description;
    private ArrayList<Person> personsList;
    private String category;
    private LocalDate date;
    private Person payer;
    private HashMap<String, Double> amountSplit = new HashMap<>();
    private static final DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private double exchangeRate;

    /**
     * Legacy Constructor for {@link Expense} - does not include parsing.
     *
     * @param amountSpent   (placeholder)
     * @param category      (placeholder)
     * @param listOfPersons (placeholder)
     * @param description   (placeholder)
     * @param exchangeRate  (placeholder)
     */
    //@@author lixiyuan416
    public Expense(Double amountSpent, String category, ArrayList<Person> listOfPersons,
                   String description, double exchangeRate) {
        this.amountSpent = amountSpent;
        this.description = description;
        this.category = category;
        this.personsList = listOfPersons;
        this.exchangeRate = exchangeRate;
    }
    //@@author

    /**
     * Constructor for {@link Expense} class - contains parsing and amount assignment.
     *
     * @param inputDescription String of user input to be parsed and assigned to expense attributes
     */

    public Expense(String inputDescription) throws  InvalidAmountException {
        String[] expenseInfo = inputDescription.split(" ", 3);
        setAmountSpent(expenseInfo[0]);
        setCategory(expenseInfo[1].toLowerCase());
        this.personsList = checkValidPersons(expenseInfo[2]);
        this.description = getDescriptionParse(expenseInfo[2]);
        this.exchangeRate = Storage.getOpenTrip().getExchangeRate();
        this.date = promptDate();
        if (personsList.size() == 1) {
            updateOnePersonSpending(this, personsList.get(0));
        } else {
            updateIndividualSpending(this);
        }
    }
    //@@author

    private static String getDescriptionParse(String userInput) {
        return userInput.split("/")[1].trim();
    }

    /**
     * Obtains a list of Person objects from array of names of people.
     *
     * @param userInput the input of the user
     * @return listOfPersons ArrayList containing Person objects included in the expense
     */
    private static ArrayList<Person> checkValidPersons(String userInput) {
        String[] listOfPeople = userInput.split("/")[0].split(",");
        ArrayList<Person> validListOfPeople = new ArrayList<>();
        ArrayList<String> invalidListOfPeople = new ArrayList<>();
        Storage.getLogger().log(Level.INFO, "Checking if names are valid");
        if (listOfPeople.length == 1 && listOfPeople[0].trim().equalsIgnoreCase("-all")) {
            return Storage.getOpenTrip().getListOfPersons();
        }
        for (String name : listOfPeople) {
            boolean isValidPerson = false;
            for (Person person : Storage.getOpenTrip().getListOfPersons()) {
                if (name.trim().equalsIgnoreCase(person.getName())) {
                    validListOfPeople.add(person);
                    isValidPerson = true;
                    break;
                }
            }
            if (!isValidPerson) {
                invalidListOfPeople.add(name);
            }
        }
        if (!invalidListOfPeople.isEmpty()) {
            Ui.printInvalidPeople(invalidListOfPeople);
            String newUserInput = Storage.getScanner().nextLine();
            return checkValidPersons(newUserInput);
        }
        return validListOfPeople;
    }

    //@@author

    public void setPayer(Person person) {
        this.payer = person;
    }

    public Person getPayer() {
        return payer;
    }


    public void setAmountSplit(Person person, double amount) {
        amountSplit.put(person.getName(), amount);
    }

    public HashMap<String, Double> getAmountSplit() {
        return amountSplit;
    }

    //@@author lixiyuan416
    /**
     * Prompts user for date.
     *
     * @return today's date if user input is an empty string, otherwise keeps prompting user until a valid date is given
     */
    public LocalDate promptDate() {
        Scanner sc = Storage.getScanner();
        Ui.expensePromptDate();
        String inputDate = sc.nextLine();
        while (!isDateValid(inputDate)) {
            inputDate = sc.nextLine();
        }
        if (inputDate.isEmpty()) {
            return LocalDate.now();
        } else {
            return LocalDate.parse(inputDate, inputPattern);
        }
    }

    private Boolean isDateValid(String date) {
        if (date.isEmpty()) {
            return true;
        }
        try {
            LocalDate.parse(date, inputPattern);
            return true;
        } catch (DateTimeParseException e) {
            Storage.getLogger().log(Level.INFO, "Invalid date format entered");
            Ui.expenseDateInvalid();
            return false;
        }
    }

    public void addPerson(Person p) {
        personsList.add(p);
    }

    public void printDate() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println(formattedDate);
    }
    //@@author


    //expense details
    @Override
    public String toString() {
        return ("\t" + this.getDescription()
                + System.lineSeparator()
                + "\t" + "Date: " + this.getStringDate()
                + System.lineSeparator()
                + "\t" + "Amount Spent: " + Ui.stringForeignMoney(this.getAmountSpent())
                + System.lineSeparator()
                + "\t" + "People involved:"
                + System.lineSeparator()
                + getPersonExpense()
                + "\t" + "Payer: " + this.getPayer()
                + System.lineSeparator()
                + "\t" + "Category: " + this.category)
                + System.lineSeparator();
    }

    public String getPersonExpense() {
        StringBuilder returnString = new StringBuilder();
        String name;
        String formattedSpace = "\t";
        for (Person p : personsList) {
            name = p.getName();
            returnString.append(formattedSpace);
            returnString.append(formattedSpace);
            returnString.append(personsList.indexOf(p) + 1).append(") ");
            returnString.append(name).append(", ");
            returnString.append(Ui.stringForeignMoney(getAmountSplit().get(name)));
            returnString.append(System.lineSeparator());
        }
        return returnString.toString();
    }

    //Getters and setters

    public ArrayList<Person> getPersonsList() {
        return personsList;
    }

    public String getCategory() {
        return category;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    //@@author itsleeqian
    public void setAmountSpent(String amount) throws InvalidAmountException {
        try {
            this.amountSpent = Double.parseDouble(amount);
            if (this.amountSpent <= 0) {
                throw new InvalidAmountException();
            }
            this.amountSpent = Double.parseDouble(amount);
            this.amountSpent = Storage.formatForeignMoneyDouble(this.amountSpent);
        } catch (InvalidAmountException e) {
            Ui.printInvalidAmountError();
            Scanner scanner = Storage.getScanner();
            setAmountSpent(scanner.nextLine().strip());
        }
    }
    //@@author

    public String getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStringDate() {
        return date.format(outputPattern);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
