package medbot;

import java.util.Scanner;

public class Ui {
    private Scanner inputScanner = new Scanner(System.in);

    /**
     * Gets user input from terminal and returns it as a String.
     *
     * @return the String containing the user input.
     */
    public String readInput() {
        String line;
        line = inputScanner.nextLine();
        return line;
    }

    public void printOutput(String outputMessage) {
        System.out.println(outputMessage);
    }
}
