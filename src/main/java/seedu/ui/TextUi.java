package seedu.ui;

import seedu.contact.Contact;

import java.util.Scanner;

public abstract class TextUi {
    private static final String LOGO = "   _____         _______        _     \n"
            + "  / ____|       |__   __|      | |    \n" + " | |     ___  _ __ | | ___  ___| |__  \n"
            + " | |    / _ \\| '_ \\| |/ _ \\/ __| '_ \\ \n" + " | |___| (_) | | | | |  __/ (__| | | |\n"
            + "  \\_____\\___/|_| |_|_|\\___|\\___|_| |_|\n" + "                                      \n";

    private static final String LINE = "____________________________________________________________\n";

    private static final Scanner scanner = new Scanner(System.in);

//    public TextUi(boolean isFirstRun, Contact personalContact) {
//        initMessage(isFirstRun, personalContact);
//    }

    public static String getUserInput() {
        String userInput = scanner.nextLine().trim();
        if (userInput.contains(",")) {
            String newUserInput = userInput.replace(",", "");
            ExceptionTextUi.forbiddenInputCommaMessage(newUserInput);
            return newUserInput;
        }
        return userInput;
    }

    public static String getUserDeleteConfirmation(Contact deletedContact, int deletedIndex) {
        String message = "Delete this contact?  (y/n)\n"
                + deletedIndex + ". " + deletedContact.getName() + formatContactFields(deletedContact);
        printDoubleLineMessage(message);

        String userDeleteConfirmation = scanner.nextLine().trim();
        return userDeleteConfirmation;
    }

    public static String getUserPersonalContact() {
        String message = "Whats you name?";
        System.out.println(message);
        System.out.println(LINE);
        return scanner.nextLine().trim();
    }

    public static String getNameMessage() {
        printDoubleLineMessage(LOGO);
        System.out.println("Welcome to ConTech, your personal contact tracker.\n");
        String personalContact = getUserPersonalContact();
        return personalContact;
    }

    public static void welcomeBackMessage(Contact personalContact) {
        printDoubleLineMessage(LOGO);
        System.out.println(personalContact.getName() + ", welcome back to ConTech, your personal contact tracker.\n");
        System.out.println(LINE);
    }

    public static void greetingMessage(Contact personalContact) {
        String message = "Hello there " + personalContact.getName();
        printDoubleLineMessage(message);
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

//    private static void initMessage(boolean isFirstRun, Contact personalContact) {
//        printDoubleLineMessage(LOGO);
//        if (isFirstRun) {
//            welcomeBackMessage();
//        } else {
//            System.out.println("Welcome back " + personalContact.getName());
//        }
//
//    }

    public static void createNewContactFileMessage(String contactFilePath) {
        String message = "As ConTech is unable to find your saved data, \n" + " it has created a new one for you at: \n"
                + contactFilePath;
        printBottomLineMessage(message);
    }

    public static void createNewPersonalContactFileMessage(String personalContactFilePath) {
        String message = "As ConTech is unable to find any saved personal data, \n"
                + " it has created a new one for you at: \n"
                + personalContactFilePath;
        printBottomLineMessage(message);
    }

    public static void addContactMessage(Contact addedContact, int listSize) {
        String message = "ConTech has added the specified contact: \n"
                + "Name:     " + addedContact.getName()
                + formatContactFields(addedContact)
                + "\n\nYou now have " + listSize + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void editContactMessage(Contact editedContact) {
        String message = "ConTech has edited the specified contact: \n"
                + "Name:     " + editedContact.getName()
                + formatContactFields(editedContact);
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

    public static void cancelDeleteContactMessage() {
        String message = "Delete has been cancelled.";
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
