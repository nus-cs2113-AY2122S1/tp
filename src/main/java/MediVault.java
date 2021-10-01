import errors.InvalidCommand;
import inventory.Medicine;
import inventory.Stock;
import parser.DateParser;
import parser.Parser;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps to start the application, and initialise all variables.
 * It will continuously prompt for input from the user until "EXIT" is received.
 */

public class MediVault {
    private ArrayList<Stock> stocks;
    private Ui ui;

    public MediVault() {
        stocks = new ArrayList<>();
        ui = new Ui();
        generateData(); // To be removed once add function is complete.
    }

    public static void main(String[] args) {
        new MediVault().run();
    }

    /**
     * Prompts input from user and processes it indefinitely until "EXIT" is received.
     */
    private void run() {
        ui.printWelcomeMessage();

        String userInput = "";
        Scanner in = new Scanner(System.in);

        // Loops till exit is received
        while (true) {
            System.out.print("> ");
            // Reads user input
            userInput = in.nextLine();
            try {
                if (Parser.processCommand(ui, userInput, stocks)) { // User entered exit
                    break;
                }
            } catch (InvalidCommand e) {
                // Invalid Command
                ui.printInvalidCommandMessage();
            }
        }
    }

    /**
     * Temporary function to generate data to test features like delete and update. To be removed once add function is
     * complete.
     */
    public void generateData() {
        try {
            stocks.add(new Medicine("PANADOL", 20, 10, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
            stocks.add(new Medicine("VICODIN", 10, 20, DateParser.stringToDate("30-9-2021"),
                    "POPULAR DRUG FOR TREATING ACUTE OR CHRONIC MODERATE TO MODERATELY SEVERE PAIN",
                    500));
            stocks.add(new Medicine("SIMVASTATIN", 20, 25, DateParser.stringToDate("10-10-2021"),
                    "TREATS HIGH CHOLESTEROL AND REDUCES THE RISK OF STROKE", 800));
            stocks.add(new Medicine("LISINOPRIL", 20, 25, DateParser.stringToDate("15-10-2021"),
                    "USED FOR TREATING HYPOTHYROIDISM", 800));
            stocks.add(new Medicine("AZITHROMYCIN", 20, 35, DateParser.stringToDate("15-10-2021"),
                    "USED FOR TREATING EAR, THROAT, AND SINUS INFECTIONS", 100));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
    }

}
