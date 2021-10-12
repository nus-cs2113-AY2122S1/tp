package seedu.ui;

import seedu.contact.Contact;

import java.util.Scanner;

public class TextUi {
    private static final String LOGO = "   _____         _______        _     \n"
            + "  / ____|       |__   __|      | |    \n" + " | |     ___  _ __ | | ___  ___| |__  \n"
            + " | |    / _ \\| '_ \\| |/ _ \\/ __| '_ \\ \n" + " | |___| (_) | | | | |  __/ (__| | | |\n"
            + "  \\_____\\___/|_| |_|_|\\___|\\___|_| |_|\n" + "                                      \n";

    private static final String LINE = "____________________________________________________________\n";
    private final Scanner scanner;

    public TextUi() {
        scanner = new Scanner(System.in);
        initMessage();
    }

    public String getUserInput() {
        String userInput = scanner.nextLine().trim();
        if (userInput.contains(",")) {
            String newUserInput = userInput.replace(",", "");
            ExceptionTextUi.forbiddenInputCommaMessage(newUserInput);
            return newUserInput;
        }
        return userInput;
    }

    // Used for print messages after user inputs
    private static void printDoubleLineMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    // Used for system messages without user inputs
    private static void printBottomLineMessage(String message) {
        System.out.println(message + "\n" + LINE);
    }

    private static void printTopLineMessage(String message) {
        System.out.println(LINE + "\n" + message);
    }

    // Used to print a line after displayed data
    public static void printBottomLine() {
        System.out.println("\n" + LINE);
    }

    private static void initMessage() {
        printDoubleLineMessage(LOGO);
        System.out.println("Welcome to ConTech, your personal contact tracker.\n" + LINE);
    }

    public static void createNewContactFileMessage(String contactFilePath) {
        String message = "As ConTech is unable to find your saved data, \n" + " it has created a new one for you at: \n"
                + contactFilePath;
        printBottomLineMessage(message);
    }

    public static void addContactMessage(Contact addedContact, int listSize) {
        String message = "ConTech has added the specified contact: \n"
                + "Name:     " + addedContact.getName()
                + formatContactFields(addedContact)
                + "\n\nYou now have " + listSize + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void editContactMessage(String contactName) {
        String message = "ConTech has edited the specified contact: " + contactName;
        printDoubleLineMessage(message);
    }

    public static String formatContactFields(Contact addedContact) {
        String viewGithub = ViewMessageFormatterUi.viewGithubFormatter(addedContact);
        String viewEmail = ViewMessageFormatterUi.viewEmailFormatter(addedContact);
        String viewTwitter = ViewMessageFormatterUi.viewTwitterFormatter(addedContact);
        String viewTelegram = ViewMessageFormatterUi.viewTelegramFormatter(addedContact);
        String viewLinkedin = ViewMessageFormatterUi.viewLinkedinFormatter(addedContact);
        return viewGithub + viewEmail + viewTelegram + viewLinkedin + viewTwitter;
    }

    public static void viewContactMessage(Contact viewingContact, int index) {
        String viewName = ViewMessageFormatterUi.viewNameFormatter(viewingContact);
        String message = index + ". " + viewName + formatContactFields(viewingContact);
        printDoubleLineMessage(message);
    }

    public static void printContactWithIndex(int index, String contactName) {
        System.out.println(index + ". " + contactName);
    }

    public static void contactsListMessage(int listSize) {
        String message = "ConTech has " + listSize + " contacts stored.\n" + "Here's the list :";
        printDoubleLineMessage(message);
    }

    public static void contactsEmptyListMessage() {
        String message = "You have not stored any contacts in ConTech";
        printDoubleLineMessage(message);
    }

    public static void deleteContactMessage(String contactName, int listSize) {
        String message = "ConTech has removed the specified contact: " + contactName + "\n" + "You now have " + listSize
                + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void exitMessage() {
        String message = "ConTech will now shutdown.\n" + "We hope you have enjoyed using it.";
        printDoubleLineMessage(message);
    }

}
