package seedu.duke;


import seedu.duke.command.MenuCommand;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String MENULINE = "-----------------------------------------------------"
                                           + "---|----------------------------------------------------";
    private static final String LINE = "----------------------------------------------------";
    private static final String BORDER = "=================================="
            + "============================="
            + "=============================";
    private static final Scanner SC = new Scanner(System.in);

    public static void printWelcome() {
        String welcomeMsg = "Welcome to L.O.T.S! What would you like to track today?";
        printWithBorder(welcomeMsg);
    }

    public static void printGoodbye() {
        String byeMsg = "Thank you for using L.O.T.S! Good bye!";
        printWithBorder(byeMsg);
    }

    /**
     * Prints the border.
     */
    public static void printBorder() {
        System.out.println(BORDER);
    }

    /**
     * Prints the string provided followed by the border.
     *
     * @param stringToPrint The string to output to user.
     */
    public static void printWithBorder(String stringToPrint) {
        System.out.println(stringToPrint);
        System.out.println(BORDER);
    }

    public static void printWithoutBorder(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    /**
     * Returns the user's input with the leading and
     * trailing spaces stripped.
     *
     * @return The User's input in String.
     */
    public static String readInput() {
        String input = SC.nextLine();
        return input.replaceAll("\\s{2,}", " ").strip();
    }

    //@@author mohamad-adam8991
    /**
     * Prints a list of all the orders made so far.
     *
     * @param peopleManager List of people who are ordering.
     */
    public static void printOrdersList(PeopleManager peopleManager) {
        int totalNumOfPeopleOrdered = peopleManager.getSize();
        if (totalNumOfPeopleOrdered == 0) {
            printWithBorder("Your order list is empty!");
        } else {
            assert totalNumOfPeopleOrdered != 0 : "Order list cannot be empty.";
            printSummaryForList(peopleManager);
            for (int i = 0; i < totalNumOfPeopleOrdered; i++) {
                String currentPersonName = peopleManager.getName(i);
                assert currentPersonName != null : "Person must exist.";
                printWithoutBorder((i + 1) + ") " + currentPersonName + ":");
                printIndividualPersonOrder(peopleManager.getPerson(i));
            }
            printBorder();
        }
    }

    /**
     * Prints a list of the food items that an individual person has ordered, and displays the total
     * cost of the items.
     *
     * @param currentPerson Person entry in the list that is currently being accessed.
     */
    public static void printIndividualPersonOrder(Person currentPerson) {
        double totalCost = 0;
        int totalMenuItems = Menu.TOTAL_MENU_ITEMS;
        Order[] currentIndividualOrders = currentPerson.individualFoodOrders;
        assert currentIndividualOrders != null : "Person must have food orders.";
        int index = 1;
        for (int i = 0; i < totalMenuItems; i++) {
            Order currentOrder = currentIndividualOrders[i];
            if (currentOrder.getQuantity() != 0) {
                double currentCost = currentOrder.getCost();
                printWithoutBorder("\t(" + index + ") " + currentOrder);
                totalCost = totalCost + currentCost;
                index++;
            }
        }
        if (totalCost > 0.00) {
            String printTotalCost = "[Total Cost = $" + String.format("%.2f", totalCost) + "]";
            int individualQuantity = currentPerson.getTotalNumberOfOrders();
            String printIndividualQuantity = "[Total Quantity = "
                    + Integer.toString(individualQuantity) + "]";
            printWithoutBorder(printTotalCost + " " + printIndividualQuantity);
        }
    }
    //@@author

    /**
     * Prints a message to notify the user of the deletion of an order.
     */
    public static void printDeleteMessage(Person person, int foodIndex) {
        printWithoutBorder("Alright, order " + (foodIndex + 1) + " has been deleted from " + person.personName + "!");
        printIndividualPersonOrder(person);
        printBorder();
    }

    /**
     * Prints a message to notify the user of the edit made to his order.
     * Additionally, user's edited orders will printed to show changes made.
     */
    public static void printEditMessage(Person person, int foodIndex) {
        printWithoutBorder("Order " + (foodIndex + 1) + " from " + person.personName + "'s order has been edited");
        printIndividualPersonOrder(person);
        printBorder();
    }

    /**
     * Prints a message to notify the user of an empty order list.
     */
    public static void printEmptyMessage() {
        printWithBorder("There are currently no orders!");
    }

    /**
     * Prints the details of the order that has just been added.
     *
     * @param person The person object that just added an order.
     */
    public static void printAddedOrderMessage(Person person) {
        assert person != null : "Person object cannot be null when adding!";
        printWithoutBorder("We have updated the order list for: " + person.personName);
        printIndividualPersonOrder(person);
        printBorder();
    }

    public static void printMenuHeader() {
        printWithoutBorder("index | Food Name                           | Price     | "
                           + "index | Food Name                           | Price");
        printWithoutBorder(MENULINE);
    }

    /**
     * Prints the menu of which user can order.
     * @param priceList contains prices of the food.
     * @param foodList contains the food in the menu.
     */
    public static void printMenu(ArrayList<Double> priceList, ArrayList<String> foodList) {
        Ui.printMenuHeader();
        for (int i = 0; i < 24; i++) {
            System.out.format("%-8d%-38s%-10.2f| %-8d%-35s%7.2f%n", i + 1, foodList.get(i),
                               priceList.get(i), i + 25, foodList.get(i + 24), priceList.get(i + 24));
            printWithoutBorder(MENULINE);
        }
        Ui.printWithBorder("");
    }

    /**
     * Prints a summary of all the orders.
     *
     * @param peopleManager List of people who are ordering.
     */
    public static void printSummaryForList(PeopleManager peopleManager) {
        String summary = "SUMMARY: [Total number of People: " +  peopleManager.getSize()
                + "] [Total quantity of food: " + peopleManager.getTotalNumberOfOrdersEveryone() + "]";
        printWithoutBorder(summary);
        printWithoutBorder(LINE);
    }

}
