package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.expense.Expense;
import seedu.duke.trip.Trip;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
        String input = "02-12-2020" + System.lineSeparator() + "Chris" + System.lineSeparator() + "100"
                + System.lineSeparator() + "200" + System.lineSeparator() + "y";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        exp = new Expense("600 food Albert, Betty, Chris /Dinner at fancy restaurant");
        trip.addExpense(exp);
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
        assertEquals("[Albert, Betty, Chris]",exp.getPersonsList().toString());
    }

    @Test
    void testGetAmountSplit() {
        assertEquals("{Chris=300.0, Betty=200.0, Albert=100.0}", exp.getAmountSplit().toString());
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
    void testUpdateIndividualSpendingEqual() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Evan" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("900 category Chris, Evan, Betty /description");
        trip.addExpense(testExpense);
        assertEquals(300.0, testExpense.getAmountSplit().get("Chris"));
        assertEquals(300.0, testExpense.getAmountSplit().get("Evan"));
        assertEquals(300.0, testExpense.getAmountSplit().get("Betty"));
    }

    @Test
    void testUpdateIndividualSpendingAlmostEqual() throws ForceCancelException {
        String input = "02-12-2020" + System.lineSeparator() + "Albert" + System.lineSeparator() + "equal";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Storage.setScanner(new Scanner(System.in));
        Expense testExpense = new Expense("901 category Albert, Don, Betty /description");
        trip.addExpense(testExpense);
        assertEquals(300.34, testExpense.getAmountSplit().get("Albert"));
        assertEquals(300.33, testExpense.getAmountSplit().get("Don"));
        assertEquals(300.33, testExpense.getAmountSplit().get("Betty"));
    }
}
