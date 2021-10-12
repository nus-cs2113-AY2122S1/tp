package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.universities.UniversityList;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            ModuleList moduleMasterList = new ModuleList(Storage.loadModules());//ArrayList<>(Storage.loadModules());
            UniversityList universitySelectedList = new UniversityList();
            ModuleList moduleSelectedList = new ModuleList();
            Parser mainParser = new Parser(universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
            Command cmd = null;

            do {
                try {
                    System.out.println("Enter a command");
                    String userInput = in.nextLine();
                    cmd = mainParser.parseCommand(userInput);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!(cmd instanceof ExitCommand));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
