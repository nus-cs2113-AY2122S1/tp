package seplanner;

import seplanner.commands.Command;
import seplanner.commands.ExitCommand;
import seplanner.exceptions.ParserClassException;
import seplanner.log.Log;
import seplanner.modules.ModuleList;
import seplanner.parser.Parser;
import seplanner.storage.Storage;
import seplanner.ui.UiGeneral;
import seplanner.ui.UiInvalid;
import seplanner.universities.UniversityList;

import java.io.IOException;
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
            UiGeneral.printWelcome();
            Parser mainParser = setupData(storage);
            Command cmd = null;
            do {
                cmd = readInput(in, mainParser, cmd);
            } while (!(cmd instanceof ExitCommand));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Command readInput(Scanner in, Parser mainParser, Command cmd) {
        try {
            UiGeneral.printLineSeparator();
            System.out.println();
            UiGeneral.promptInput();
            String userInput = in.nextLine();
            cmd = mainParser.parseCommand(userInput);
            System.out.println();
        } catch (ParserClassException e) {
            UiInvalid.printParseException(e.getMessage());
            UiInvalid.printFormat(e.getFormat());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        return cmd;
    }

    private static Parser setupData(Storage storage) throws IOException {
        ModuleList moduleMasterList = new ModuleList(storage.readModuleList());
        UniversityList universityMasterList = new UniversityList(storage.readUniversityList(moduleMasterList));
        UniversityList universitySelectedList = storage.readSelectedUniversityList(
                universityMasterList, moduleMasterList);
        ModuleList moduleSelectedList = storage.readSelectedModuleList(
                moduleMasterList);
        return new Parser(universityMasterList, moduleMasterList, universitySelectedList, moduleSelectedList);
    }
}