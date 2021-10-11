package seedu.mtracker.error;

public abstract class ErrorMessage {

    public static String invalidInstrumentGivenError = "Invalid Instrument given!";
    public static String invalidCommandGivenError = "Oops, I do not understand you...";

    public static void displayAddInstrumentNameError(String instrumentType) {
        System.out.println("Sorry " + instrumentType + " cannot have an empty name!");
    }

    public static void displayAddForexNameError() {
        System.out.println("Sorry forex pair codes must contain 6 letters!");
    }

    public static void displayAddInstrumentCurrentPriceError() {
        System.out.println("Sorry current price cannot be empty and must be a number!");
    }

    public static void displayAddInstrumentSentimentError() {
        System.out.println("Sorry sentiment cannot be empty and must be either positive, negative or neutral");
    }
}
