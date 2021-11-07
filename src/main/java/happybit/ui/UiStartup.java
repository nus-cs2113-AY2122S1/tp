package happybit.ui;

import happybit.exception.HaBitUiException;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class UiStartup extends UiManager {

    private static final String UG_URI = "https://ay2122s1-cs2113t-f14-1.github.io/tp/UserGuide.html";
    private static final String DG_URI = "https://ay2122s1-cs2113t-f14-1.github.io/tp/DeveloperGuide.html";
    private static final String DEMO_URI = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"; // To be changed (lel)

    private static final String MENU = "Select from one of the following menu items:"
            + LS + S_TAB + "[1] About HappyBit"
            + LS + S_TAB + "[2] User Guide"
            + LS + S_TAB + "[3] Developer Guide"
            + LS + S_TAB + "[4] Demo Video"
            + LS + S_TAB + "[5] Start Application"
            + LS + S_TAB + "[6] Exit Application\n";

    private static final String WELCOME_MESSAGE = "Howdy! Welcome to Ha(ppy)Bit!\n";
    private static final String BROWSER_MESSAGE = "Opening in a web browser...\n";
    private static final String RETURN_MESSAGE = "Press enter to return to main menu...\n";
    private static final String START_MESSAGE = "Starting application...\nType 'return' to return to main menu\n";
    private static final String INVALID_MESSAGE = "Invalid command detected\n";

    private static final String ABOUT_MESSAGE = "Ha(ppy)Bit is a desktop app aimed to improve both the physical and "
            + "mental lifestyle of students,\n"
            + "through the setting and tracking of goals to cultivate good habits. The app operates on a Command\n"
            + "Line Interface (CLI) while still comprising features typically found in a Graphical User Interface\n"
            + "(GUI). Students who type fast will find that Ha(ppy)Bit performs habit tracking more efficiently\n"
            + "than your conventional GUI apps.\n";

    private static final String TEAM_MESSAGE = "Meet The Team\n"
            + "=================================================================\n"
            + "||  Tan Kah Heng       ||  Creative Director, Visionary        ||\n"
            + "||  Swaminathan Varun  ||  Business Manager, Technical Advisor ||\n"
            + "||  Tan Jun Heng Daren ||  Frontend Developer                  ||\n"
            + "||  Tan Hui En         ||  Backend Developer                   ||\n"
            + "||  Swann Tet Aung     ||  Backend Developer                   ||\n"
            + "=================================================================\n";

    private static final String VERSION_MESSAGE = "Current Version: v2.0 (updated 28-Oct-2021)\n";
    private static boolean isExitFromStartup = false;

    /**
     * Main function to execute main menu interface.
     *
     * @throws HaBitUiException If browser fails to open for scenario 2, 3 and 4.
     */
    public void run() throws HaBitUiException {
        defaultDisplay();
        Scanner in = new Scanner(System.in);
        String userInput = readUserInput(in);
        while (!userInput.equals("5") && !userInput.equals("6")) {
            scenario1To4(userInput);
            userInput = readUserInput(in);
        }

        if (userInput.equals("5")) {
            System.out.println(START_MESSAGE);
            waitApp(1);
            clearConsoleScreen();
        }
        if (userInput.equals("6")) {
            setIsExitFromStartup(true);
        }
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Prints the default display.
     */
    private void defaultDisplay() {
        clearConsoleScreen();
        printLogo();
        System.out.println(WELCOME_MESSAGE + MENU);
    }

    /**
     * Reads in the user input.
     *
     * @param in Scanner that obtains the user input.
     * @return String of the user input.
     */
    private String readUserInput(Scanner in) {
        System.out.print("Option: ");
        String userInput = in.next();
        System.out.print(LS);
        return userInput;
    }

    /**
     * Executes the corresponding case based on user input.
     *
     * @param number String containing case number.
     * @throws HaBitUiException If browser fails to open for case 2, 3 and 4.
     */
    private void scenario1To4(String number) throws HaBitUiException {
        switch (number) {
        case "1":
            System.out.println(ABOUT_MESSAGE + LS + TEAM_MESSAGE + LS + VERSION_MESSAGE + LS);
            break;
        case "2":
            openBrowser(UG_URI);
            break;
        case "3":
            openBrowser(DG_URI);
            break;
        case "4":
            openBrowser(DEMO_URI);
            break;
        default:
            defaultDisplay();
            System.out.println(INVALID_MESSAGE);
        }
        waitForEnter();
        defaultDisplay();
    }

    /**
     * Wait for user to press Enter.
     */
    private void waitForEnter() {
        System.out.println(RETURN_MESSAGE);
        Scanner in = new Scanner(System.in);
        String userInput = null;
        while (userInput == null) {
            userInput = in.nextLine();
        }
    }

    /**
     * Opens a web browser.
     *
     * @param link String of the link to be opened.
     * @throws HaBitUiException If the link provided is invalid or webpage does not exist.
     */
    private void openBrowser(String link) throws HaBitUiException {
        try {
            System.out.println(BROWSER_MESSAGE);
            URI uri = new URI(link);
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException uriSyntaxException) {
            throw new HaBitUiException("Webpage is either down or does not exist");
        }
    }

    /**
     * Setter for boolean variable isExitFromStartup.
     *
     * @param isExitFromStartup True meaning that user has chosen to exit app from startup page. Otherwise, false.
     */
    public static void setIsExitFromStartup(boolean isExitFromStartup) {
        UiStartup.isExitFromStartup = isExitFromStartup;
    }

    /**
     * Getter for boolean variable isExitFromStartup.
     *
     * @return Boolean value of isExitFromStartup.
     */
    public static boolean getIsExitFromStartup() {
        return isExitFromStartup;
    }
}
