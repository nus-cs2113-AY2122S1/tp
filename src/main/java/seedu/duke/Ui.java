package seedu.duke;

import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Ui {
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void linebreak() {
        System.out.println("______________________________________________________________________");
    }

    public static void promptForDescription() {
        System.out.println("Please add an optional description for your item and press enter. ");
        linebreak();
    }

    public static void printList(ArrayList<Item> list) {
        AtomicInteger i = new AtomicInteger();

        list.stream()
                .forEach(item -> {
                    i.getAndIncrement();
                    System.out.print(i + ". " + item.getTitle() + " -> "
                            + Parser.convertDateTime(item.getDateTime())
                            + System.lineSeparator());
                });
    }

    public static void printEvent(Event item) {
        System.out.println(item.getTitle() + System.lineSeparator()
                + Parser.convertDateTime(item.getDateTime()) + System.lineSeparator()
                + item.getDescription() + System.lineSeparator()
                + item.getVenue() + System.lineSeparator()
                + item.getBudget());
    }

    public static void printTask(Task item) {
        System.out.println(item.getTitle() + System.lineSeparator()
                + Parser.convertDateTime(item.getDateTime()) + System.lineSeparator()
                + item.getDescription());
    }

}
