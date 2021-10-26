package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

public class Expense {
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
    public Expense(Double amountSpent, String category, ArrayList<Person> listOfPersons,
                   String description, double exchangeRate) {
        this.amountSpent = amountSpent;
        this.description = description;
        this.category = category;
        this.personsList = listOfPersons;
        this.exchangeRate = exchangeRate;
    }

    /**
     * Constructor for {@link Expense} class - contains parsing, date prompting and amount assignment.
     *
     * @param inputDescription String of user input to be parsed and assigned to expense attributes
     */
    public Expense(String inputDescription) {
        String[] expenseInfo = inputDescription.split(" ", 3);
        this.amountSpent = Double.parseDouble(String.format(
                expenseInfo[0], Storage.getOpenTrip().getForeignCurrencyFormat()));
        this.category = expenseInfo[1].toLowerCase();
        this.personsList = checkValidPersons(expenseInfo[2]);
        this.description = getDescriptionParse(expenseInfo[2]);
        this.exchangeRate = Storage.getOpenTrip().getExchangeRate();
        this.date = prompDate();
        if (personsList.size() == 1) {
            Parser.updateOnePersonSpending(this, personsList.get(0));
        } else {
            Parser.updateIndividualSpending(this);
        }
    }

    private static String getDescriptionParse(String userInput) {
        return userInput.split("/")[1].trim();
    }

    //@@author joshualeeky
    /**
     * Obtains a list of Person objects from array of names of people.
     *
     * @param userInput the input of the user
     * @return listOfPersons ArrayList containing Person objects included in the expense
     */
    private static ArrayList<Person> checkValidPersons(String userInput) {
        String[] listOfPeople = userInput.split("/")[0].split(",");
        ArrayList<Person> validListOfPeople = new ArrayList<>();
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
                Ui.printInvalidPerson(name);
                Scanner newUserInput = Storage.getScanner();
                return checkValidPersons(newUserInput.nextLine());
            }
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

    /**
     * Prompts user for date.
     *
     * @return today's date if user input is an empty string, otherwise keeps prompting user until a valid date is given
     */
    public LocalDate prompDate() {
        Scanner sc = Storage.getScanner();
        System.out.println("Enter date of expense:");
        System.out.println("\tPress enter to use today's date");
        String inputDate = sc.nextLine();
        while (!isDateValid(inputDate)) {
            inputDate = sc.nextLine();
        }
        if (inputDate.isEmpty()) {
            return LocalDate.now();
        }
        return LocalDate.parse(inputDate, inputPattern);
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
            System.out.println("\tPlease enter date as DD-MM-YYYY, or enter nothing to use today's date");
            return false;
        }
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
        return ("\t" + this.getDescription()
                + System.lineSeparator()
                + "\t" + "date: " + this.getStringDate()
                + System.lineSeparator()
                + "\t" + "Amount Spent: " + Ui.stringForeignMoney(this.getAmountSpent())
                + System.lineSeparator()
                + "\t" + "People involved: "
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
