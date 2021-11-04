package seedu.duke.trip;

import seedu.duke.Person;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.expense.Expense;
import seedu.duke.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;

interface FilterFinder {

    DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //@@author lixiyuan416
    static void findMatchingDateExpenses(ArrayList<Expense> listOfCurrentExpenses, String expenseAttribute)
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
    static void findMatchingPayerExpenses(ArrayList<Expense> listOfCurrentExpenses, String expenseAttribute) {
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

    static void findMatchingDescriptionExpenses(ArrayList<Expense> listOfCurrentExpenses,
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

    static void findMatchingCategoryExpenses(ArrayList<Expense> listOfCurrentExpenses,
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
    static void findMatchingPersonExpenses(ArrayList<Expense> listOfCurrentExpenses,
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

    private static boolean isDateValid(String inputDate) {
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
}
