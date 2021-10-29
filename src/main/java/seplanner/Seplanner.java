package seplanner;

import seplanner.commands.Command;
import seplanner.commands.ExitCommand;
import seplanner.log.Log;
import seplanner.modules.ModuleList;
import seplanner.parser.Parser;
import seplanner.storage.Storage;
import seplanner.ui.UiGeneral;
import seplanner.universities.UniversityList;

import java.util.Scanner;

public class Seplanner {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage();
        try {
            Log.setupLogger();
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