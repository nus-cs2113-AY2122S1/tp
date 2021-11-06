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
        exp1.setDate("11-02-2021");
        trip.addExpense(exp1);
    }

    @Test
    public void testOptimizePayments() throws ForceCancelException {
        Trip openTrip = Storage.getOpenTrip();
        Person person1 = openTrip.getListOfPersons().get(0);
        Person person2 = openTrip.getListOfPersons().get(1);
        Person person3 = openTrip.getListOfPersons().get(2);
        HashMap<String, Double> hashmap = person1.getOptimizedMoneyOwed();
        PaymentOptimizer.optimizePayments(openTrip);
        System.out.println(hashmap.get("tom"));
    }


}