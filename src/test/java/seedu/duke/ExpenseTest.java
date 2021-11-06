package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.expense.Expense;
import seedu.duke.trip.Trip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    static Expense exp;

    @BeforeAll
    static void setUp() throws ForceCancelException, SameNameException {
        String[] stringArray = {"", "USA", "01-12-2020", "USD", "0.74", "Albert, Betty, Chris, Don, Evan"};
        Trip trip = new Trip(stringArray);
        Storage.getListOfTrips().add(trip);
        Storage.setOpenTrip(0);
        double amountSpent = 600.0;
        String description = "Dinner at fancy restaurant";
        Person person1 = new Person("Albert");
        Person person2 = new Person("Betty");
        Person person3 = new Person("Chris");
        ArrayList<Person> personsList = new ArrayList<>();
        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        String category = "food";
        LocalDate date = LocalDate.parse("02-12-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Person payer = person3;
        HashMap<String, Double>amountSplit = new HashMap<>();
        amountSplit.put("Albert", 100.0);
        amountSplit.put("Betty", 200.0);
        amountSplit.put("Chris", 300.0);
        exp = new Expense(amountSpent, description, personsList, category, date, payer, amountSplit);
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
                + "\t\t3) Chris, USD $300.00" + System.lineSeparator()
                , exp.getPersonExpense());
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

}
