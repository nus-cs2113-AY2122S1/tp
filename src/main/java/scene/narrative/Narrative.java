package scene.narrative;

import investigation.Investigation;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Narrative {

    String filePath;
    String fileName;

    public Narrative() {
        filePath = "";
    }

    public Narrative(String fileName) {
        this.fileName = fileName;
        this.filePath = "";
    }

    /**
     * Reads the text file from the file path.
     *
     * @return Contents of the text file.
     * @throws FileNotFoundException If the file could not be found.
     */
    public String getNarrative() throws FileNotFoundException {
        InputStream file = getClass().getResourceAsStream(filePath + fileName);
        if (file == null) {
            throw new FileNotFoundException();
        }
        Scanner in = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (in.hasNext()) {
            content.append("\n").append(in.nextLine());
        }
        return content.toString();
    }

    /**
     * Displays the narrative based on the scene.
     *
     * @throws FileNotFoundException If the file could not be found.
     */
    public void displayNarrative() throws FileNotFoundException {
        if (fileName.toLowerCase(Locale.ROOT).contains("scene")) {
            displaySceneNarrative();
        } else {
            System.out.println(this.getNarrative());
        }
    }

    /** Clears the user console. */
    public static void clearConsole() {
        try {
            // Check the current operating system
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the narrative while prompting user to press enter key every #num of lines.
     *
     * @throws FileNotFoundException If the file could not be found.
     */
    private void displaySceneNarrative() throws FileNotFoundException {
        String content = this.getNarrative();
        while (content.contains("\n")) {
            clearConsole();
            content = getNarrativeToPrint(content);
            promptUserEnterKey(content);
        }
        System.out.println(content + "\n");
    }

    /**
     * Gets the content to be printed in current iteration.
     *
     * @param content Remaining content to print.
     * @return Content to be printed in current iteration.
     */
    private String getNarrativeToPrint(String content) {
        int lines = Investigation.numLinesToPrintForNarrative * 2;
        for (int i = 0; i < lines && content.contains("\n"); i++) {
            System.out.print(content.substring(0, 1 + content.indexOf("\n")));
            content = content.substring(1 + content.indexOf("\n"));
        }
        return content;
    }

    /**
     * Prompts user to press enter key if narrative still contains lines which haven't been printed.
     * If the narrative reaches the end, no need to prompt user to press enter key.
     *
     * @param content Remaining content to be printed.
     */
    public void promptUserEnterKey(String content) {
        if (content.contains("\n")) {
            System.out.println("Please press the Enter key");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }
}
