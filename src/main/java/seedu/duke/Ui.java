package seedu.duke;

import seedu.duke.exceptions.LoadException;
import java.util.Scanner;

import static seedu.duke.ClickfitMessages.CALCULATOR_PROMPT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_N_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_PROMPT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_Y_INPUT;
import static seedu.duke.ClickfitMessages.MESSAGE_A;


public class Ui {

    private Scanner uiScanner;
    public static final String HORIZONTAL_BAR_LONG = "____________________________________________________________"
            + "____________________________________________________________";
    public static final String HORIZONTAL_BAR_SHORT = "_________________________________________________________";
    public static final String USER_PROMPT = "Enter command: ";
    protected String sex;
    protected double weight;
    protected double height;
    protected int age;
    protected int activityLevel;

    //@@author pragyan01
    /**
     * Constructor of UI class.
     *
     */
    public Ui() {
        uiScanner = new Scanner(System.in);
    }

    //@@author EdwardZYWang
    /**
     * Print method with clickFit's logo.
     *
     */
    public static void welcomeMessage() {
        String logo = "   ______  _____     _____            __       ________  _   _\n"
                + " .' ___  ||_   _|   |_   _|          [  |  _  |_   __  |(_) / |_\n"
                + "/ .'   \\_|  | |       | |      .---.  | | / ]   | |_ \\_|__ `| |-'\n"
                + "| |         | |   _   | |     / /'`\\] | '' <    |  _|  [  | | |\n"
                + "\\ `.___.'\\ _| |__/ | _| |_  _ | \\__.  | |`\\ \\  _| |_    | | | |,\n"
                + " `.____ .'|________||_____|(_)'.___.'[__|  \\_]|_____|  [___]\\__/";

        System.out.println("Greetings from\n" + logo + "\n");
        System.out.println(MESSAGE_A);
    }

    //@@author pragyan01
    /**
     * This method obtains user's answers to BMI related questions and then calculates user's BMI.
     *
     * @author pragyan01
     */
    public void getInfo() {
        System.out.println(CALCULATOR_PROMPT);
        String uiInput;
        boolean answerIsCorrect = false;
        boolean flag = false;

        while (!flag) {
            uiInput = uiScanner.nextLine();
            if (uiInput.isEmpty()) {
                System.out.println("Calculator closed!");
                return;
            } else if (uiInput.trim().equals("y")) {
                flag = true;
            } else {
                System.out.println("wrong input!");
            }
        }
        answerIsCorrect = checkGender(answerIsCorrect);

        answerIsCorrect = checkWeight(answerIsCorrect);

        answerIsCorrect = checkHeight(answerIsCorrect);

        answerIsCorrect = checkAge(answerIsCorrect);

        checkActivityLevel(answerIsCorrect);

        Calculator calculator = new Calculator(sex, weight, height, age, activityLevel);
        calculator.getBmi();
        calculator.getIdealCalories();
    }

    //@@author pragyan01
    /**
     * This method obtains user's answers to BMI related questions and then calculates user's BMI.
     *
     * @author pragyan01
     */
    private void checkActivityLevel(boolean answerIsCorrect) {
        String uiInput;
        while (!answerIsCorrect) {
            try {
                System.out.println("what is your activity level from a scale of 1 - 5? Enter an integer from 1 to 5!");
                uiInput = uiScanner.nextLine();
                if (uiInput.matches("^\\d+(\\.\\d+)?")) {
                    activityLevel = Integer.parseInt(uiInput);
                    if ((activityLevel >= 1) && (activityLevel <= 5)) {
                        answerIsCorrect = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid input!");
            }
        }
    }

    //@@author pragyan01
    /**
     * This method obtains user's age.
     *
     * @author pragyan01
     */
    private boolean checkAge(boolean answerIsCorrect) {
        String uiInput;
        while (!answerIsCorrect) {
            try {
                System.out.println("what is your age in years? Enter an integer!");
                uiInput = uiScanner.nextLine();
                if (uiInput.matches("^\\d+(\\.\\d+)?")) {
                    age = Integer.parseInt(uiInput);
                    answerIsCorrect = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid input!");
            }
        }
        answerIsCorrect = false;
        return answerIsCorrect;
    }

    //@@author pragyan01
    /**
     * This method obtains user's height.
     *
     * @author pragyan01
     */
    private boolean checkHeight(boolean answerIsCorrect) {
        String uiInput;
        while (!answerIsCorrect) {
            System.out.println("what is your height in cm? Enter a valid number!");
            uiInput = uiScanner.nextLine();
            if (uiInput.matches("^\\d+(\\.\\d+)?")) {
                height = Double.parseDouble(uiInput);
                answerIsCorrect = true;
            }
        }
        answerIsCorrect = false;
        return answerIsCorrect;
    }

    //@@author VishalJeyaram
    /**
     * This method obtains the user's weight.
     *
     * @param answerIsCorrect The boolean that checks if the answer is acceptable.
     * @return answerIsCorrect.
     */
    private boolean checkWeight(boolean answerIsCorrect) {
        String uiInput;
        while (!answerIsCorrect) {
            System.out.println("what is your weight in kg? Enter a valid number!");
            uiInput = uiScanner.nextLine();
            if (uiInput.matches("^\\d+(\\.\\d+)?")) {
                weight = Double.parseDouble(uiInput);
                answerIsCorrect = true;
            }
        }
        answerIsCorrect = false;
        return answerIsCorrect;
    }

    //@@author VishalJeyaram
    /**
     * This method obtains the user's gender.
     *
     * @param answerIsCorrect The boolean that checks if the answer is acceptable.
     * @return answerIsCorrect.
     */
    private boolean checkGender(boolean answerIsCorrect) {
        String uiInput;
        while (!answerIsCorrect) {
            System.out.println("what is your SEX : M / F ?");
            uiInput = uiScanner.nextLine();
            sex = uiInput;
            if ((uiInput.equals("M")) || (uiInput.equals("F"))) {
                answerIsCorrect = true;
            }
        }
        answerIsCorrect = false;
        return answerIsCorrect;
    }

    //@@author EdwardZYWang
    /**
     * Checks with the user whether he wishes to load the data of the previous session.
     * if "y" is input, the data from the previous session is deleted . if enter keystroke is registered
     * the previous session's data is loaded.
     *
     * @throws LoadException If there are missing text files.
     */
    public boolean memoryStartup() throws LoadException {
        System.out.println(MEMORY_STARTUP_PROMPT);
        String uiInput;
        boolean flag = false;
        boolean result = false;
        while (!flag) {
            uiInput = uiScanner.nextLine();
            if (uiInput.isEmpty()) {
                System.out.println(MEMORY_STARTUP_Y_INPUT);
                System.out.println(System.lineSeparator() + "What would you like to do?");
                result = true;
                flag = true;
            } else if (uiInput.trim().equals("y")) {
                System.out.println(MEMORY_STARTUP_N_INPUT);
                result = false;
                flag = true;
            } else {
                System.out.println("wrong input!");
            }
        }
        return result;
    }
}