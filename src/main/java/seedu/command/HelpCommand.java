package seedu.command;

import seedu.ui.TextUi;

import java.util.ArrayList;

public class HelpCommand extends Command {

    public static final String commandSyntax = "help";
    public static final String commandAction = "Displays all the available commands";

    private static final String FIXED_SYNTAX_FORMAT = "| %-32.32s";
    private static final String FIXED_ACTION_FORMAT = "| %-55.55s|";
    private static final String FIXED_SERIAL_FORMAT = "| %-3.3s";

    private ArrayList<String> commandList = new ArrayList<>();

    public HelpCommand() {

    }

    public void execute() {
        TextUi.printHelpHeader();
        printHeader();
        printCommandsHelp();
        TextUi.printHelpFooter();
    }

    private void printHeader() {
        String str = "\t\t| No.";
        str += String.format(FIXED_SYNTAX_FORMAT, "Command Syntax");
        str += String.format(FIXED_ACTION_FORMAT, "Command Action");
        System.out.println(str);
    }

    private void printCommandHelps(int serial, String syntax, String action) {
        String str = "\t\t";
        String ser = String.format("%d.",serial);
        str += String.format(FIXED_SERIAL_FORMAT, ser);
        str += String.format(FIXED_SYNTAX_FORMAT, syntax);
        str += String.format(FIXED_ACTION_FORMAT, action);
        System.out.println(str);
    }

    private void printCommandsHelp() {
        printCommandHelps(1, HelpCommand.commandSyntax, HelpCommand.commandAction);
        printCommandHelps(2, SearchCommand.commandSyntax, SearchCommand.commandAction);
        printCommandHelps(3, ShowCommand.commandSyntax, ShowCommand.commandAction);
        printCommandHelps(4, UpdateCommand.commandSyntax, UpdateCommand.commandAction);
        printCommandHelps(5, AddCommand.commandSyntax, AddCommand.commandAction);
        printCommandHelps(6, DeleteCommand.commandSyntax, DeleteCommand.commandAction);
        printCommandHelps(7, ClearCommand.commandSyntax, ClearCommand.commandAction);
        printCommandHelps(8, EditCommand.commandSyntax, EditCommand.commandAction);
        printCommandHelps(9, TimetableCommand.commandSyntax, TimetableCommand.commandAction);
        printCommandHelps(10, ChangeSemesterCommand.commandSyntax,
                ChangeSemesterCommand.commandAction);
        printCommandHelps(11, CheckCommand.commandSyntax, CheckCommand.commandAction);
        printCommandHelps(12, StoreResultsCommand.commandSyntax, StoreResultsCommand.commandAction);
        printCommandHelps(13, RemoveCommand.commandSyntax, RemoveCommand.commandAction);
        printCommandHelps(14, CalculateCapCommand.commandSyntax, CalculateCapCommand.commandAction);
        printCommandHelps(15, TranscriptCommand.commandSyntax, TranscriptCommand.commandAction);
        printCommandHelps(16, ExitCommand.commandSyntax, ExitCommand.commandAction);
    }
}

