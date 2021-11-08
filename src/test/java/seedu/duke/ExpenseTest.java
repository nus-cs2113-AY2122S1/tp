package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.expense.Expense;
import seedu.duke.parser.Parser;
import seedu.duke.trip.FilterFinder;
import seedu.duke.trip.Trip;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@@ joshualeeky
class ExpenseTest {
    static Expense exp;
    static Trip trip;

    @BeforeAll
    static void setUp() throws ForceCancelException, SameNameException {
        String[] stringArray = {"", "USA", "01-12-2020", "USD", "0.74", "Albert, Betty, Chris, Don, Evan"};
        trip = new Trip(stringArray);
        Storage.getListOfTrips().add(trip);
        Storage.setOpenTrip(Storage.getListOfTrips().indexOf(trip));
        String input = "02-12-2020" + System.lineSeparator() + "Chris" + System.lineSeparator() + "100"
                + System.lineSeparator() + "200" + System.lineSeparator() + "y";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        exp = new Expense("600 food Albert, Betty, Chris /Dinner at fancy restaurant");
        trip.addExpense(exp);
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

    @Test
    void testInvalidAmount() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        String expectedOutput = "Please format your inputs as follows: "
                + System.lineSeparator() + "expense [amount] [category] [people] /[description]."
                + System.lineSeparator();
        Parser.parseUserInput("expense notNumber category Albert, Betty, Evan /description");
        assertEquals(actualOutput.toString(), expectedOutput);
    }

    @Test
    void testNormalAssignUserNo() throws ForceCancelException {
        String correctInput = "02-12-2020" + System.lineSeparator() + "Evan" + System.lineSeparator() + "1010"
                + System.lineSeparator() + "1010" + System.lineSeparator() + "n"
                + System.lineSeparator() + "Evan" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(correctInput.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("3000 category Albert, Betty, Evan /description");
        assertEquals("Evan", testExpense.getPayer().getName());
        assertEquals(1000.0, testExpense.getAmountSplit().get("Albert"));
        assertEquals(1000.0, testExpense.getAmountSplit().get("Betty"));
        assertEquals(1000.0, testExpense.getAmountSplit().get("Evan"));
    }

    @Test
    void testAmountAssignedTooHigh() throws ForceCancelException {
        String correctInput = "02-12-2020" + System.lineSeparator() + "Betty" + System.lineSeparator() + "2114"
                + System.lineSeparator() + "Betty" + System.lineSeparator() + "1010"
                + System.lineSeparator() + "1010" + System.lineSeparator() + "y";
        System.setIn(new ByteArrayInputStream(correctInput.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("2113 category Albert, Betty, Evan /description");
        assertEquals("Betty", testExpense.getPayer().getName());
        assertEquals(1010.0, testExpense.getAmountSplit().get("Albert"));
        assertEquals(1010.0, testExpense.getAmountSplit().get("Betty"));
        assertEquals(93.0, testExpense.getAmountSplit().get("Evan"));
    }

    @Test
    void testInvalidPersonInExpense() throws ForceCancelException {
        String correctInput = "Don" + System.lineSeparator() + "02-12-2020" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(correctInput.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("2113 category Duke /description");
        assertEquals("Don", testExpense.getPayer().getName());
        assertEquals(2113.0, testExpense.getAmountSplit().get("Don"));
    }

    @Test
    void testInvalidPayer() throws ForceCancelException {
        String correctInput = "02-12-2020" + System.lineSeparator() + "Chris" + System.lineSeparator() + "Albert"
                + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(correctInput.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("3000 category Albert, Don, Betty /description");
        assertEquals("Albert", testExpense.getPayer().getName());
        assertEquals(1000.0, testExpense.getAmountSplit().get("Albert"));
        assertEquals(1000.0, testExpense.getAmountSplit().get("Betty"));
        assertEquals(1000.0, testExpense.getAmountSplit().get("Don"));
    }

    @Test
    void testUpdateOnePersonSpending() throws ForceCancelException {
        System.setIn(new ByteArrayInputStream("08-12-2010".getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("2113 category Albert /description");
        assertEquals("Albert", testExpense.getPayer().getName());
        assertEquals(2113.0, testExpense.getAmountSplit().get("Albert"));
        assertEquals(2213.0, trip.getListOfPersons().get(0).getMoneyOwed().get("Albert"));
    }

    @Test
    void testUpdateIndividualSpendingAssignZero() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Albert" + System.lineSeparator() + "1234"
                + System.lineSeparator() + "y";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("1234 category Albert, Evan, Don /description");
        trip.addExpense(testExpense);
        assertEquals(1234.0, testExpense.getAmountSplit().get("Albert"));
        assertEquals(0.0, testExpense.getAmountSplit().get("Evan"));
        assertEquals(0.0, testExpense.getAmountSplit().get("Don"));
    }

    @Test
    void testUpdateIndividualSpendingAssignZeroUserNo() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Albert" + System.lineSeparator() + "1234"
                + System.lineSeparator() + "n" + System.lineSeparator() + "Albert" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("1200 category Albert, Evan, Don /description");
        trip.addExpense(testExpense);
        assertEquals(400.0, testExpense.getAmountSplit().get("Albert"));
        assertEquals(400.0, testExpense.getAmountSplit().get("Evan"));
        assertEquals(400.0, testExpense.getAmountSplit().get("Don"));
    }

    @Test
    void testUpdateIndividualSpendingEqual() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Evan" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("900 category Don, Evan, Betty /description");
        trip.addExpense(testExpense);
        assertEquals(300.0, testExpense.getAmountSplit().get("Don"));
        assertEquals(300.0, testExpense.getAmountSplit().get("Evan"));
        assertEquals(300.0, testExpense.getAmountSplit().get("Betty"));
    }

    @Test
    void testUpdateIndividualSpendingMoreThanEqual() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Albert" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("901 category Albert, Don, Betty /description");
        trip.addExpense(testExpense);
        assertEquals(300.34, testExpense.getAmountSplit().get("Albert"));
        assertEquals(300.33, testExpense.getAmountSplit().get("Don"));
        assertEquals(300.33, testExpense.getAmountSplit().get("Betty"));
    }

    @Test
    void testUpdateIndividualSpendingLessThanEqual() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Albert" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("899 category Albert, Don, Betty /description");
        trip.addExpense(testExpense);
        assertEquals(299.66, testExpense.getAmountSplit().get("Albert"));
        assertEquals(299.67, testExpense.getAmountSplit().get("Don"));
        assertEquals(299.67, testExpense.getAmountSplit().get("Betty"));
    }

    //@@author lixiyuan416
    //Tests expense filtering methods
    @Test
    void findMatchingPersonExpenses_validName_printExpense() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        String correctOutput = "1. \tDinner at fancy restaurant" + System.lineSeparator()
                + "\tDate: 02 Dec 2020" + System.lineSeparator()
                + "\tAmount Spent: USD $600.00" + System.lineSeparator()
                + "\tPeople involved:" + System.lineSeparator()
                + "\t\t1) Albert, USD $100.00" + System.lineSeparator()
                + "\t\t2) Betty, USD $200.00" + System.lineSeparator()
                + "\t\t3) Chris, USD $300.00" + System.lineSeparator()
                + "\tPayer: Chris" + System.lineSeparator()
                + "\tCategory: food";
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
