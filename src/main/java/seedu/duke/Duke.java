package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.parser.ListCommandParser;
import seedu.duke.parser.RemoveCommandParser;
import seedu.duke.parser.SepPlannerParser;
import seedu.duke.storage.Storage;
import seedu.duke.universities.UniversityList;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            UniversityList universityList = new UniversityList(Storage.loadUniversities());
            SepPlannerParser mainParser = new SepPlannerParser(universityList);
            while (true) {
                String userInput = in.nextLine();
                Command cmd = mainParser.parseCommand(userInput);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

