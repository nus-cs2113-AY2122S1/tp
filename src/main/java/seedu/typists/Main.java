package seedu.typists;

import seedu.typists.content.WikiImport;
import seedu.typists.exception.InvalidArticleException;
import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.parser.StringParser;

import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        // The following code test on the JUnit test on StringParser.
        StringParser sp = new StringParser();
        String str = "Hello|World|How are|you all";
        List<String> stringParts = null;
        try {
            stringParts = sp.splitString(str, "\\|");
        } catch (InvalidStringInputException e) {
            e.printStackTrace();
        }

        // Test for WikiImport.
        WikiImport wiki = new WikiImport();
        try {
            String article = wiki.getArticle("GitHub");
        } catch (InvalidArticleException e) {
            e.printStackTrace();
        }
    }
}
