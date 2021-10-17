package seedu.ui;

import seedu.contact.Contact;

import java.util.Scanner;

public abstract class TextUi {
    private static final String LOGO = "   _____         _______        _     \n"
            + "  / ____|       |__   __|      | |    \n" + " | |     ___  _ __ | | ___  ___| |__  \n"
            + " | |    / _ \\| '_ \\| |/ _ \\/ __| '_ \\ \n" + " | |___| (_) | | | | |  __/ (__| | | |\n"
            + "  \\_____\\___/|_| |_|_|\\___|\\___|_| |_|\n" + "                                      \n";

    private static final String LINE = "____________________________________________________________\n";

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

    public static void welcomeMessage() {
        printDoubleLineMessage(LOGO);
        String message = "Welcome to ConTech, your personal contact tracker.\n"
                + "Can I get your name?";
        printBottomLineMessage(message);
    }

    public static void welcomeBackMessage(Contact personalContact) {
        printDoubleLineMessage(LOGO);
        String message = "Hello, " + personalContact.getName() + ". Welcome back to ConTech, "
                + "your personal \ncontact tracker.";
        printBottomLineMessage(message);
    }

    public static void greetingMessage(Contact personalContact) {
        String message = "Hello there, " + personalContact.getName() + ".\n\n"
                + "This is ConTech, your very own contact tracking application\n"
                + "to manage computing-related contacts, like GitHub Accounts\n"
                + "or even Emails.\n\n"
                + "Enter \"help\" to see what you can do with ConTech.";
        printDoubleLineMessage(message);
    }

    public static void promptPersonalGithubUsernameMessage(String personalName) {
        String message = "Hello, " + personalName + ".\n\n"
                + "Please provide us with your Github Username\n"
                + "or press ENTER if you would like to skip.";
        printDoubleLineMessage(message);
    }

    public static void promptPersonalTelegramUsernameMessage() {
        String message = "Please provide us with your Telegram Handle\n"
                + "or press ENTER if you would like to skip.";
        printDoubleLineMessage(message);
    }

    public static void promptPersonalTwitterUsernameMessage() {
        String message = "Please provide us with your Twitter Username\n"
                + "or press ENTER if you would like to skip.";
        printDoubleLineMessage(message);
    }

    public static void promptPersonalEmailMessage() {
        String message = "Please provide us with your Email Address\n"
                + "or press ENTER if you would like to skip.";
        printDoubleLineMessage(message);
    }

    public static void promptPersonalLinkedInUsernameMessage() {
        String message = "Please provide us with your LinkedIn Username\n"
                + "or press ENTER if you would like to skip.";
        printDoubleLineMessage(message);
    }

    public static void createNewContactFileMessage(String contactFilePath) {
        String message = "As ConTech is unable to find your saved data,\n" + " it has created a new one for you at:\n"
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
        String message = "ConTech has added the specified contact:\n"
                + "Name:     " + addedContact.getName()
                + formatContactFields(addedContact)
                + "\n\nYou now have " + listSize + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void editContactMessage(Contact editedContact) {
        String message = "ConTech has edited the specified contact:\n"
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

    public static void confirmDeleteMessage(Contact deletedContact, int deletedIndex) {
        String message = "Delete this contact?  (y/n)\n"
                + deletedIndex + ". " + deletedContact.getName() + formatContactFields(deletedContact);
        printDoubleLineMessage(message);
    }

    public static void deleteContactMessage(String contactName, int listSize) {
        String message = "ConTech has removed the specified contact: " + contactName + "\n" + "You now have " + listSize
                + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void cancelDeleteContactMessage() {
        String message = "Delete has been cancelled.";
        printDoubleLineMessage(message);
    }

    public static void exitMessage() {
        String message = "ConTech will now shutdown.\n" + "We hope you have enjoyed using it.";
        printDoubleLineMessage(message);
    }

    public static void helpMessage() {
        String message = "add: ConTech adds the specified contact with provided parameters.\n"
                + " Parameters: -n NAME -g GITHUB -e EMAIL -te TELEGRAM -l LINKEDIN -tw TWITTER\n"
                + " Note: Parameters need not be in order and are optional except for NAME.\n"
                + " Example: add -n John Doe -g johndoecoder -e john@email.com -te johndoe\n\n"
                + "list: Displays the name of all saved contacts.\n"
                + " Example: list\n\n"
                + "edit: Edit any parameter in an existing contact.\n"
                + " Parameters: INDEX -n NAME -g GITHUB -e EMAIL -te TELEGRAM -l LINKEDIN -tw TWITTER\n"
                + " Note: Parameters need not be in order and are optional except for INDEX.\n"
                + " Example: edit 1 -e john.doe@email.com\n\n"
                + "view: Displays all details for index specified contact.\n"
                + " Parameter: INDEX\n"
                + " Note: Index starts from 0.\n"
                + " Example: view 2\n\n"
                + "rm: Deletes the index specified contact.\n"
                + " Parameter: INDEX\n"
                + " Note: Index starts from 0.\n"
                + " Example: rm 0\n\n"
                + "help: Displays application usage instructions.\n"
                + " Example: help";
        printDoubleLineMessage(message);
    }

}
