package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import seedu.duke.Person;
import seedu.duke.Storage;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.SameNameException;
import seedu.duke.trip.Trip;
import seedu.duke.expense.Expense;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class PaymentOptimizerTest {

    static Expense exp1;
    static Expense exp2;
    static Expense exp3;

    @BeforeAll
    static void setUp() throws SameNameException, ForceCancelException {
        String[] stringArray = {"", "Canada", "02-03-2021", "cad", "0.123", "ben,jerry,tom"};
        Trip trip = new Trip(stringArray);
        ArrayList<Trip> listOfTrips = new ArrayList<>();
        listOfTrips.add(trip);
        Storage.setListOfTrips(listOfTrips);
        Storage.setOpenTrip(0);
        ArrayList<Person> listOfPersons = trip.getListOfPersons();
        Person person1 = listOfPersons.get(0);
        Person person2 = listOfPersons.get(1);
        Person person3 = listOfPersons.get(2);
        Expense exp1 = new Expense(8.00, "food", listOfPersons, "chicken nuggers");
        exp1.setPayer(person1);
        person1.setMoneyOwed(person1, -8.0);
        person1.setMoneyOwed(person2, 5.0);
        person2.setMoneyOwed(person1, -5.0);
        person1.setMoneyOwed(person3, 3.0);
        person3.setMoneyOwed(person1, -3.0);
        exp1.setAmountSplit(person2, 4.0);
        exp1.setAmountSplit(person3, 4.0);
        Expense exp2 = new Expense(16.00, "food", listOfPersons, "chicken");
        exp1.setPayer(person2);
        person2.setMoneyOwed(person2, -16.0);
        person2.setMoneyOwed(person1, 10.0);
        person1.setMoneyOwed(person2, -10.0);
        person2.setMoneyOwed(person3, 6.0);
        person3.setMoneyOwed(person2, -6.0);
        exp2.setAmountSplit(person2, 10.0);
        exp1.setDate("11-02-2021");
        exp2.setAmountSplit(person3, 6.0);
        exp2.setDate("11-02-2021");
        trip.addExpense(exp1);
        trip.addExpense(exp2);
    }

    @Test
    public void testOptimizePayments() throws ForceCancelException {
        Trip openTrip = Storage.getOpenTrip();
        Person person1 = openTrip.getListOfPersons().get(0);
        Person person2 = openTrip.getListOfPersons().get(1);
        Person person3 = openTrip.getListOfPersons().get(2);
        HashMap<String, Double> hashMapBen = person1.getOptimizedMoneyOwed();
        HashMap<String, Double> hashMapJerry = person2.getOptimizedMoneyOwed();
        HashMap<String, Double> hashMapTom = person3.getOptimizedMoneyOwed();
        PaymentOptimizer.optimizePayments(openTrip);
        assertEquals(hashMapBen.get("jerry"), -2.0);
        assertEquals(hashMapJerry.get("ben"), 2.0);
        assertEquals(hashMapTom.get("jerry"), -9.0);
        assertEquals(hashMapJerry.get("tom"), 9.0);

    }


}