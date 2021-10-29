package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiGeneral;
import seedu.duke.universities.UniversityList;

import java.util.Scanner;

public class Seplanner {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage();
        try {
            UiGeneral.welcome();
            ModuleList moduleMasterList = new ModuleList(storage.readModuleList());
            UniversityList universityMasterList = new UniversityList(storage.readUniversityList(moduleMasterList));
            UniversityList universitySelectedList = new UniversityList(
                    storage.readSelectedUniversityList(universityMasterList, moduleMasterList));
            ModuleList moduleSelectedList = new ModuleList(
                    storage.readSelectedModuleList(moduleMasterList));
            Parser mainParser = new Parser(universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
            Command cmd = null;
            do {
                try {
                    UiGeneral.printLineSeparator();
                    System.out.println();
                    UiGeneral.promptInput();
                    String userInput = in.nextLine();
                    cmd = mainParser.parseCommand(userInput);
                    System.out.println();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println();

                }
            } while (!(cmd instanceof ExitCommand));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}