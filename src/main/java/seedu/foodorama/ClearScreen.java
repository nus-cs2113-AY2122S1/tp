package seedu.foodorama;

/**
 * Allows the application to clear the user's terminal.
 *
 * @author renzocanare
 */
public class ClearScreen {

    /**
     * Clears the user's terminal.
     * The method checks if the user's terminal is running on Windows or MacOS/Linux, and sends the corresponding
     * clear terminal command to their terminal to clear their terminal.
     *
     * @author renzocanare
     */
    public static void clearConsole() {
        try {
            // Get current operating system
            String getOS = System.getProperty("os.name");

            if (getOS.contains("Windows")) {
                // Try clear for Windows
                // "/c" - execute string as command, "cls" -  Clear terminal
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                // Try clear for MacOS/Linux
                // "clear" - Clear terminal
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
