package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.expense.Expense;
import seedu.duke.trip.FilterFinder;
import seedu.duke.trip.Trip;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    static Expense exp;
    static Trip trip;

    @BeforeAll
    static void setUp() throws ForceCancelException, SameNameException {
        String[] stringArray = {"", "USA", "01-12-2020", "USD", "0.74", "Albert, Betty, Chris, Don, Evan"};
        trip = new Trip(stringArray);
        Storage.getListOfTrips().add(trip);
        Storage.setOpenTrip(Storage.getListOfTrips().indexOf(trip));
        final double amountSpent = 600.0;
        final String description = "Dinner at fancy restaurant";
        Person person1 = new Person("Albert");
        Person person2 = new Person("Betty");
        Person person3 = new Person("Chris");
        ArrayList<Person> personsList = new ArrayList<>();
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        final String category = "food";
        final LocalDate date = LocalDate.parse("02-12-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        final Person payer = person3;
        HashMap<String, Double> amountSplit = new HashMap<>();
        amountSplit.put("Albert", 100.0);
        amountSplit.put("Betty", 200.0);
        amountSplit.put("Chris", 300.0);
        exp = new Expense(amountSpent, description, personsList, category, date, payer, amountSplit);
        System.setOut(System.out);
    }


    @Test
    void testGetAmountSpent() {
        assertEquals(600.0, exp.getAmountSpent());
    }

    @Test
    void testGetPersonExpense() {
        assertEquals("\t\t1) Albert, USD $100.00" + System.lineSeparator()
                + "\t\t2) Betty, USD $200.00" + System.lineSeparator()
                + "\t\t3) Chris, USD $300.00" + System.lineSeparator(), exp.getPersonExpense());
    }

    @Test
    void testGetDescription() {
        assertEquals("Dinner at fancy restaurant", exp.getDescription());
    }

    @Test
    void testSetCategory() {
        exp.setCategory("f&b");
        assertEquals("f&b", exp.getCategory());
    }

    @Test
    void testGetStringDate() {
        assertEquals("02 Dec 2020", exp.getStringDate());
    }

    @Test
    void testSetPayer() {
        Person person2 = new Person("Betty");
        exp.setPayer(person2);
        assertEquals("Betty", exp.getPayer().getName());
    }

    @Test
    void testGetPersonsList() {
        assertEquals("[Albert, Betty, Chris]", exp.getPersonsList().toString());
    }

    @Test
    void testGetAmountSplit() {
        assertEquals("{Chris=300.0, Betty=200.0, Albert=100.0}", exp.getAmountSplit().toString());
    }

    //@@author lixiyuan416
    //Tests expense filtering methods
    @Test
    void findMatchingPersonExpenses_validName_printExpense() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        trip.addExpense(exp);
        String correctOutput = "1. \tDinner at fancy restaurant" + System.lineSeparator()
                + "\tDate: 02 Dec 2020" + System.lineSeparator()
                + "\tAmount Spent: USD $600.00" + System.lineSeparator()
                + "\tPeople involved:" + System.lineSeparator()
                + "\t\t1) Albert, USD $100.00" + System.lineSeparator()
                + "\t\t2) Betty, USD $200.00" + System.lineSeparator()
                + "\t\t3) Chris, USD $300.00" + System.lineSeparator()
                + "\tPayer: Chris" + System.lineSeparator() +
                "\tCategory: food";
        FilterFinder.findMatchingPersonExpenses(trip.getListOfExpenses(), "Chris");
        assertEquals(bo.toString().trim(), correctOutput);
    }

    @Test
    void findMatchingPersonExpenses_invalidName_printNotFound() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        FilterFinder.findMatchingPersonExpenses(trip.getListOfExpenses(), "Mr Muscle");

        assertEquals(bo.toString().trim(), "No matching expenses found.");
    }

    @Test
    void findMatchingDateExpensesReturnsEmpty() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        try {
            FilterFinder.findMatchingDateExpenses(trip.getListOfExpenses(), "01-12-4000");
        } catch (ForceCancelException e) {
            e.printStackTrace();
        }
        assertEquals(bo.toString().trim(), "No matching expenses found.");
    }

    @AfterAll
    static void restoreSystemOut() {
        System.setOut(System.out);
    }

}
