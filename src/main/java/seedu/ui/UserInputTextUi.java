package seedu.ui;

import java.util.Scanner;

public class UserInputTextUi {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        String userInput = scanner.nextLine().trim();
        if (userInput.contains(",")) {
            String newUserInput = userInput.replace(",", "");
            ExceptionTextUi.forbiddenInputCommaMessage(newUserInput);
            return newUserInput;
        }
        return userInput;
    }

    public static String getUserDeleteConfirmation() {
        String userDeleteConfirmation = scanner.nextLine().trim();
        return userDeleteConfirmation;
    }
}
