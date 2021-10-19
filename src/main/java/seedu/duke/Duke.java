package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.ModuleStorage;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.storage.UniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.UniversityList;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
            ModuleList moduleMasterList = new ModuleList(ModuleStorage.load());
            UniversityList universitySelectedList = new UniversityList(
                    SelectedUniversityStorage.load());
            ModuleList moduleSelectedList = new ModuleList(
                    SelectedModuleStorage.load());
            Parser mainParser = new Parser(universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
            Command cmd = null;
            Ui.welcome();

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