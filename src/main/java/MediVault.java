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

}
