package terminus.ui;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import terminus.module.ModuleManager;
import terminus.parser.CommandParser;

public class Ui {

    private static final String PROMPT = "[%s] >>> ";
    private static final Ui UI = new Ui();

    private final Scanner scanner;

    private Ui() {
        this(System.in);
    }
    
    public Ui(InputStream in) {
        this.scanner = new Scanner(in);
    }

    /**
     * Prints the banner for a workspace, which includes all the commands in the parser.
     */
    public void printParserBanner(CommandParser parser, ModuleManager moduleManager) {
        printSection(
            "",
            parser.getWorkspaceBanner(moduleManager),
            parser.getCommandList()
                .stream()
                .reduce("\nType any of the following to get started:\n",
                    (x, y) -> String.format("%s> %s\n", x, y))
        );
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
        return getUserInput(String.format(PROMPT, validatedWorkspaceName));
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

    /**
     * Get the input of the user through the Scanner.
     * 
     * @return The user input from the Scanner.
     */
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static Ui getInstance() {
        return UI;
    }
}
