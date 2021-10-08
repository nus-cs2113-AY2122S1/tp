package seedu.duke;

public class Ui {

    public static void printWelcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

    }

    public static void goodBye() {
        System.out.println("Goodbye!");
    }

    /**
     * Prints a line for each person who owes the user money.
     *
     * @param person a person in the given trip
     */
    public static void printWhoOwesMe(Person person) {
        System.out.println(person.getName() + " | " + person.getAmtOwedToUser());
    }

}
