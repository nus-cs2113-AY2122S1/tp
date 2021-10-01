package terminus.ui;

import java.util.Arrays;
import java.util.Scanner;

public class Ui {
        
    private static final String BANNER = "Welcome to TermiNUS!\n" 
            + "\n" 
            + "Type any of the following to get started:\n"
            + "> notes\n"
            + "> schedules\n"
            + "> help\n"
            + "> exit\n";
    private static final String PROMPT = "[%s] >>> ";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the banner.
     */
    public void printBanner() {
        System.out.println(BANNER);
    }

    /**
     * Prints a prompt and requests a command from the user.
     * 
     * @param workspaceName The string to place within the brackets.
     * @return The command the user inputted.
     */
    public String requestCommand(String workspaceName) {
        String validatedWorkspaceName = workspaceName;
        if (validatedWorkspaceName == null) {
            validatedWorkspaceName = "";
        }
        System.out.printf((PROMPT) + "%n", validatedWorkspaceName);
        return scanner.nextLine();
    }

    /**
     * Prints multiple strings at once, separated by a new line.
     * 
     * @param strings The strings to print.
     */
    public void printSection(String... strings) {
        Arrays.stream(strings).forEach(System.out::println);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        System.out.println("Goodbye!");
    }
    
}
