package seedu.duke.parser;

import seedu.duke.Person;
import seedu.duke.trip.Trip;

import java.util.ArrayList;
import java.util.HashMap;

//@@author leeyikai
interface PaymentOptimizer {
    double EPSILON = 0.001;

    static void optimizePayments(Trip trip) {
        ArrayList<Person> listOfPersons = trip.getListOfPersons();
        ArrayList<Double> totalExpenses = new ArrayList<>();
        boolean isAllPaid = false;
        getTotalAmountForPerson(totalExpenses, listOfPersons);

        int currentIndex;
        while (!isAllPaid) {
            for (Person person : listOfPersons) {
                currentIndex = listOfPersons.indexOf(person);
                if (totalExpenses.get(currentIndex) < 0) {
                    findNextPersonToPay(totalExpenses, currentIndex, listOfPersons);
                }
            }
            isAllPaid = checkIfAllPaid(totalExpenses);
        }
    }

    private static boolean checkIfAllPaid(ArrayList<Double> totalExpenses) {
        for (Double i : totalExpenses) {
            if (!isZero(i)) {
                return false;
            }
        }
        return true;
    }

    private static void findNextPersonToPay(ArrayList<Double> totalExpenses, int indexOfPersonPaying,
            ArrayList<Person> listOfPersons) {
        Double expensesOfCurrentPerson;
        Double expensesOfPersonPaying;
        Person personPaying;
        Person personReceiving;
        String nameOfPersonPaying;
        String nameOfPersonReceiving;
        for (Person person : listOfPersons) {
            int indexOfPersonReceiving = listOfPersons.indexOf(person);
            expensesOfCurrentPerson = totalExpenses.get(indexOfPersonReceiving);
            expensesOfPersonPaying = -totalExpenses.get(indexOfPersonPaying);
            personPaying = listOfPersons.get(indexOfPersonPaying);
            personReceiving = listOfPersons.get(indexOfPersonReceiving);
            nameOfPersonPaying = personPaying.getName();
            nameOfPersonReceiving = personReceiving.getName();
            if (totalExpenses.get(indexOfPersonReceiving) > 0) {
                if (isMoreThanOrEqual(expensesOfPersonPaying, expensesOfCurrentPerson)) {
                    personPaying.getOptimizedMoneyOwed().put(nameOfPersonReceiving, -expensesOfCurrentPerson);
                    personReceiving.getOptimizedMoneyOwed().put(nameOfPersonPaying, expensesOfCurrentPerson);
                    totalExpenses.set(indexOfPersonReceiving, 0.0);
                    totalExpenses.set(indexOfPersonPaying, -(expensesOfPersonPaying - expensesOfCurrentPerson));
                } else {
                    personPaying.getOptimizedMoneyOwed().put(nameOfPersonReceiving, -expensesOfPersonPaying);
                    personReceiving.getOptimizedMoneyOwed().put(nameOfPersonPaying, expensesOfPersonPaying);
                    totalExpenses.set(indexOfPersonPaying, 0.0);
                    totalExpenses.set(indexOfPersonReceiving, expensesOfCurrentPerson - expensesOfPersonPaying);
                }
            }
            if (isZero(totalExpenses.get(indexOfPersonPaying))) {
                return;
            }
        }
    }

    private static void getTotalAmountForPerson(ArrayList<Double> totalExpenses, ArrayList<Person> listOfPersons) {
        Double totalAmountPerPerson;
        for (Person person : listOfPersons) {
            totalAmountPerPerson = 0.0;
            HashMap<String, Double> personExpenses = person.getMoneyOwed();
            String otherPersonName;
            for (Person otherPerson : listOfPersons) {
                if (!otherPerson.equals(person)) {
                    otherPersonName = otherPerson.getName();
                    totalAmountPerPerson += personExpenses.get(otherPersonName);
                    person.setOptimizedMoneyOwed(otherPerson);
                }

            }
            totalExpenses.add(totalAmountPerPerson);
        }
    }

    private static boolean isMoreThanOrEqual(double firstValue, double secondValue) {
        if (isEqual(firstValue, secondValue)) {
            return true;
        }
        return firstValue > secondValue;
    }

    private static boolean isEqual(double firstValue, double secondValue) {
        double difference = firstValue - secondValue;
        return difference < EPSILON && difference > -EPSILON;
    }

    private static boolean isZero(double value) {
        return value < EPSILON && value > -EPSILON;
    }
}
