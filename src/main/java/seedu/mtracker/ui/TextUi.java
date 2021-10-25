package seedu.mtracker.ui;

import seedu.mtracker.model.Instrument;

import java.util.ArrayList;

public class TextUi {

    private static final String LINE_DECORATOR = "_".repeat(80);
    private static final String CONSOLE_PROMPTER = "mTracker$> ";
    private static final String LOGO = "            _________                      __\n"
            + "           |  _   _  |                    [  |  _\n"
            + " _ .--..--.|_/ | | \\_| .--.  ,--.   .---.  | | / ] .---.  _ .--.\n"
            + "[ `.-. .-. |   | |  [ `/'`\\]`'_\\ : / /'`\\] | '' < / /__\\\\[ `/'`\\]\n"
            + " | | | | | |  _| |_  | |    /| | |,| \\__.  | |`\\ \\| \\__., | |\n"
            + "[___||__||__]|_____|[___]   \\'-;__/'.___.'[__|  \\_]'.__.'[___]\n";
    private static final String BYE_LOGO = " ______            _______  _\n"
            + "(  ___ \\ |\\     /|(  ____ \\( )\n"
            + "| (   ) )( \\   / )| (    \\/| |\n"
            + "| (__/ /  \\ (_) / | (__    | |\n"
            + "|  __ (    \\   /  |  __)   | |\n"
            + "| (  \\ \\    ) (   | (      (_)\n"
            + "| )___) )   | |   | (____/| _\n"
            + "|/ \\___/    \\_/   (_______/(_)";

    private static final String TAB = "\t";
    private static final String INDEX_BRACKET = ") ";
    private static final String TYPE_HEADER = "Please key in the type of instrument: ";
    private static final String CURRENT_PRICE_HEADER = "Current Price: ";
    private static final String SENTIMENT_HEADER = "Sentiment for instrument: ";
    private static final String EXPIRY_HEADER = "Expiry: ";
    private static final String REMARKS_HEADER = "Remarks (optional): ";
    private static final String ENTRY_PRICE_HEADER = "Entry Price: ";
    private static final String EXIT_PRICE_HEADER = "Exit Price: ";
    private static final String RETURNS_HEADER = "Past Returns (optional): ";
    private static final String EDIT_NAME_MESSAGE = "Enter new name:";
    private static final String EDIT_CURRENTPRICE_MESSAGE = "Enter new Current price:";
    private static final String EDIT_SENTIMENT_MESSAGE = "Enter new Sentiment:";
    private static final String EDIT_REMARKS_MESSAGE = "Enter new Remark:";
    private static final String EDIT_RETURN_MESSAGE = "Enter new Past Returns:";
    private static final String EDIT_ENTRY_MESSAGE = "Enter new Entry Price:";
    private static final String EDIT_EXIT_MESSAGE = "Enter new Exit Price:";
    private static final String EDIT_EXPIRY_MESSAGE = "Enter new Expiry:";

    private static final int NONE_FOUND = 0;

    public static void displayInstrumentAdded(Instrument newInstrument) {
        System.out.println(TAB + newInstrument.getGeneralParams() + " - has been added to list.");
    }

    public static void displayAddInstrumentFirstInstruction() {
        System.out.println(TAB + TYPE_HEADER);
    }

    public static void displayAddInstrumentNameInstruction(String instrumentType) {
        System.out.println(TAB + "Name of " + instrumentType + ": ");
    }

    public static void displayAddInstrumentCurrentPriceInstruction() {
        System.out.println(TAB + CURRENT_PRICE_HEADER);
    }

    public static void displayAddInstrumentSentimentInstruction() {
        System.out.println(TAB + SENTIMENT_HEADER);
    }

    public static void displayAddRemarksInstruction() {
        System.out.println(TAB + REMARKS_HEADER);
    }

    public static void displayAddExpiryInstruction() {
        System.out.println(TAB + EXPIRY_HEADER);
    }

    public static void displayAddEntryPriceInstruction() {
        System.out.println(TAB + ENTRY_PRICE_HEADER);
    }

    public static void displayAddExitPriceInstruction() {
        System.out.println(TAB + EXIT_PRICE_HEADER);
    }

    public static void displayAddPastReturnsInstruction() {
        System.out.println(TAB + RETURNS_HEADER);
    }

    public static void displayAllInstruments(ArrayList<Instrument> instruments) {
        System.out.println(LINE_DECORATOR);
        int idx = 0;
        for (Instrument instrument: instruments) {
            idx += 1;
            System.out.println(constructLineInList(idx, instrument));
        }
        System.out.println(LINE_DECORATOR);
    }

    private static void displayFoundMessage(int numFound, String keyword) {
        if (numFound == NONE_FOUND) {
            System.out.println("There were no instruments found for " + keyword.toUpperCase());
            return;
        }
        System.out.println("There were " + numFound + " instrument(s) found for keyword, " + keyword + ".");
    }

    public static void displayInstrumentsFound(ArrayList<Instrument> instruments, String searchString) {
        System.out.println(LINE_DECORATOR);
        int found = 0;
        int idx = 0;
        for (Instrument instrument: instruments) {
            idx += 1;
            if (instrument.getName().contains(searchString)) {
                System.out.println(constructLineInList(idx, instrument));
                found += 1;
            }
        }
        displayFoundMessage(found, searchString);
        System.out.println(LINE_DECORATOR);
    }

    private static String constructLineInList(int idx, Instrument instrument) {
        return idx + INDEX_BRACKET + instrument.getGeneralParams();
    }

    public static void displaySpecificInstrumentView(Instrument instrument) {
        System.out.println(LINE_DECORATOR);
        System.out.println(instrument.getAllParams());
        System.out.println(LINE_DECORATOR);
    }

    public static void displayDoneInstrument(Instrument instrument) {
        System.out.println(TAB + "Nice! I have marked this instrument as completed:"
                + System.lineSeparator() + TAB + TAB
                + instrument.getGeneralParams());
    }

    public static void displayInstrumentDeleted(Instrument instrument) {
        System.out.println(LINE_DECORATOR);
        System.out.println("Noted. " + instrument.getGeneralParams() + " - removed from your watchlist");
        System.out.println(LINE_DECORATOR);
    }

    public static void displayCreateFile() {
        System.out.println("Unable to find a saved file. Creating a new one now...");
    }

    public static void displayLoadingFile() {
        System.out.println("Found a saved file. Loading the saved data now...");
    }

    public static void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void displayExitMessage() {
        System.out.println(BYE_LOGO);
        System.out.println("Thank you for using mTracker.\n"
                + "☻ MAY THE MARKETS BE WITH YOU!!! ᕦ(ò_óˇ)ᕤ");
    }

    public static void displayPrompter() {
        System.out.print(CONSOLE_PROMPTER);
    }

    public static void displayEditInstrumentFirstInstruction(Instrument instrument) {
        System.out.println("Please enter one or more " + instrument.getType()
                + " parameters to edit." + System.lineSeparator()
                + instrument.editParameterInstructions());
    }

    public static void displayEditInvalidAttribute(String inputAttribute) {
        System.out.println(inputAttribute + " is an invalid attribute of this instrument and will be ignored.");
    }

    public static void displayEditName() {
        System.out.println(EDIT_NAME_MESSAGE);
    }

    public static void displayEditCurrentPrice() {
        System.out.println(EDIT_CURRENTPRICE_MESSAGE);
    }

    public static void displayEditSentiment() {
        System.out.println(EDIT_SENTIMENT_MESSAGE);
    }

    public static void displayEditRemark() {
        System.out.println(EDIT_REMARKS_MESSAGE);
    }

    public static void displayEditReturn() {
        System.out.println(EDIT_RETURN_MESSAGE);
    }

    public static void displayEditEntryPrice() {
        System.out.println(EDIT_ENTRY_MESSAGE);
    }

    public static void displayEditExitPrice() {
        System.out.println(EDIT_EXIT_MESSAGE);
    }

    public static void displayEditExpiry() {
        System.out.println(EDIT_EXPIRY_MESSAGE);
    }

    public static void displayEditBeforeAfter(Instrument beforeEdit, Instrument afterEdit) {
        System.out.println("Before:");
        displaySpecificInstrumentView(beforeEdit);
        System.out.println("Changed To:");
        displaySpecificInstrumentView(afterEdit);
    }

    public static void greetAtStartUp() {
        System.out.println(LINE_DECORATOR);
        System.out.println(LOGO);
        System.out.println("Hello! I am mTracker, your personal assistant bot that\n"
                + "helps you keep track of the markets.\nWhat should I do for you now? ☺");
        System.out.println(LINE_DECORATOR);
    }

}
