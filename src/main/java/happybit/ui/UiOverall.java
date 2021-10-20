package happybit.ui;

public class UiOverall {

    protected static final String S_TAB = "    ";
    protected static final String LS = System.lineSeparator();
    protected static final String BAR = "======================================="
            + "=======================================\n";
    protected static final String LOGO = " _  _   __   ____  ____  _  _  ____  __  ____ \n"
            + "/ )( \\ / _\\ (  _ \\(  _ \\( \\/ )(  _ \\(  )(_  _)\n"
            + ") __ (/    \\ ) __/ ) __/ )  /  ) _ ( )(   )(  \n"
            + "\\_)(_/\\_/\\_/(__)  (__)  (__/  (____/(__) (__) ";
    protected static final String SLOGAN = "tracking habits made fun...\n";

    /**
     * Prints the logo enclosed between double bars.
     */
    protected void printLogo() {
        System.out.println(BAR + LOGO + S_TAB + SLOGAN + BAR);
    }

    /**
     * Clears the console screen.
     */
    protected void clearConsoleScreen() {
        try {
            new ProcessBuilder("clear", "cls").inheritIO().start().waitFor();
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

}
