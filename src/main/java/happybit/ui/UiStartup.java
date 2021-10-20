package happybit.ui;

import happybit.exception.HaBitUiException;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UiStartup {

    private static final String UG_URI = "https://ay2122s1-cs2113t-f14-1.github.io/tp/UserGuide.html";
    private static final String DG_URI = "https://ay2122s1-cs2113t-f14-1.github.io/tp/DeveloperGuide.html";
    private static final String DEMO_URI = "https://github.com/AY2122S1-CS2113T-F14-1/tp"; // To be changed (currently links to github repo)

    private static final String S_TAB = "    ";
    private static final String LS = System.lineSeparator();
    private static final String BAR = "=============================================================================\n";
    private static final String LOGO = " _  _   __   ____  ____  _  _  ____  __  ____ \n"
            + "/ )( \\ / _\\ (  _ \\(  _ \\( \\/ )(  _ \\(  )(_  _)\n"
            + ") __ (/    \\ ) __/ ) __/ )  /  ) _ ( )(   )(  \n"
            + "\\_)(_/\\_/\\_/(__)  (__)  (__/  (____/(__) (__) ";
    private static final String SLOGAN = "tracking habits made fun...\n";

    private static final String MENU = "Select from one of the following menu items:"
            + LS + S_TAB + "[1] About HappyBit"
            + LS + S_TAB + "[2] User Guide"
            + LS + S_TAB + "[3] Developer Guide"
            + LS + S_TAB + "[4] Demo Video"
            + LS + S_TAB + "[5] Start Application\n";

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

    /**
     * Main function to execute main menu interface.
     *
     * @throws HaBitUiException If browser fails to open for scenario 2, 3 and 4.
     */
    public void run() throws HaBitUiException {
        defaultDisplay();
        Scanner in = new Scanner(System.in);
        String userInput = readUserInput(in);
        while (!userInput.equals("5")) {
            scenario1To4(userInput);
            userInput = readUserInput(in);
        }
        System.out.println(START_MESSAGE);
        waitApp(2);
        clearConsoleScreen();
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Prints the logo enclosed between double bars.
     */
    private void printLogo() {
        System.out.println(BAR + LOGO + S_TAB + SLOGAN + BAR);
    }

    /**
     * Prints the default display.
     */
    private void defaultDisplay() {
        clearConsoleScreen();
        printLogo();
        System.out.println(WELCOME_MESSAGE + MENU);
    }

    /**
     * Clears the console screen.
     */
    private void clearConsoleScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // do nothing
        }
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
     * Waits for 'time' seconds before proceeding with the next method.
     */
    private void waitApp(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            // Do nothing
        }
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

}
