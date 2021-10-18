package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CardLiUi {
    private final Scanner in;
    private final PrintStream out;

    public CardLiUi() {
        this(System.in, System.out);
    }

    public CardLiUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserMessage() {
        return in.nextLine();
    }

    public void showMessage(String input) {
        System.out.println(input);
    }

    public void printByeMessage() {
        System.out.println("\tYou did well today! Goodbye!");
    }

    public static void helpMessage() {
        String help = "\n"
                + "................................................................................"
                + "....................................... \n"
                + "Here is the list  of commands! \n"
                + "1. adddeck \n"
                + "Description: Adds a flashcard deck \n"
                + "Format: adddeck <name of deck> \n\n"
                + "2. add \n"
                + "Description: Adds a flashcard to a deck \n"
                + "Format: add <deck index> /fro <word/phrase on front of flashcard> /bac"
                +  " <word/phrase on back of flashcard> \n\n"
                + "3. delete \n"
                + "Description: Deletes a flashcard \n"
                + "Format: delete <deck index> /car <word/phrase/index> \n\n"
                + "4. editcard \n"
                + "Description: Edits a flashcard \n"
                + "Format: editcard /deck <index of deck> /card <index of card> /side <front"
                + " or back of card> /input <word/phrase> \n\n"
                + "5. editdeck \n"
                + "Description: Edits a flashcard deck \n"
                + "Format: editdeck /deck <index of deck> /input <word/phrase> \n\n"
                + "6. viewdecks \n"
                + "Description: List flashcard decks \n"
                + "Format: viewdecks \n\n"
                + "7. viewdeck \n"
                + "Description: List flashcards in a deck \n"
                + "Format: viewdeck <deck index> \n\n"
                + "8. test \n"
                + "Description: Testing flashcards within a deck \n"
                + "Format: test \n\n"
                + "9. viewfc \n"
                + "Description: view overall results for flashcards \n"
                + "Format: viewfc \n\n"
                + "10. viewtests \n"
                + "Description: view results of all the test \n"
                + "Format: viewtests \n\n"
                + "11. viewtest \n"
                + "Description: view result of a test \n"
                + "Format: viewtest <test index> \n\n"
                + "12. review \n"
                + "Description: Enter review mode, which is same as test mode but tests cards "
                + "that the user got wrong more often \n"
                + "Format: review \n\n"
                + "13. find \n"
                + "Description: finds card using word/phrase of the query \n"
                + "Format: find <word/phrase> \n\n"
                + "14. save \n"
                + "Description: saves the current status of the cards to a text file \n"
                + "Format: save \n"
                + "................................................................................"
                + "....................................... \n";
        System.out.println(help);
    }

    public void printGreetingMessage() {
        String logo = "\n"
                + " .----------------.  .----------------.  .----------------.  .----------------. "
                + " .----------------.  .----------------.\n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. |"
                + "| .--------------. || .--------------. |\n"
                + "| |     ______   | || |      __      | || |  _______     | || |  ________    | ||"
                + " |   _____      | || |     _____    | |\n"
                + "| |   .' ___  |  | || |     /  \\     | || | |_   __ \\    | || | |_   ___ `.  | ||"
                + " |  |_   _|     | || |    |_   _|   | |\n"
                + "| |  / .'   \\_|  | || |    / /\\ \\    | || |   | |__) |   | || |   | |   `. \\ |"
                + " || |    | |       | || |      | |     | |\n"
                + "| |  | |         | || |   / ____ \\   | || |   |  __ /    | || |   | |    | | | ||"
                + " |    | |   _   | || |      | |     | |\n"
                + "| |  \\ `.___.'\\  | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |  _| |___.' "
                + "/ | || |   _| |__/ |  | || |     _| |_    | |\n"
                + "| |   `._____.'  | || ||____|  |____|| || | |____| |___| | || | |________.'  | || "
                + "|  |________|  | || |    |_____|   | |\n"
                + "| |              | || |              | || |              | || |              | || "
                + "|              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' |"
                + "| '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------' "
                + " '----------------'  '----------------'\n";
        System.out.println("Welcome to\n" + logo);
        System.out.println("Let's get started!");
    }
}
