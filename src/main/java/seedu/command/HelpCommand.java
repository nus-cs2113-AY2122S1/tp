package seedu.command;

import seedu.ui.TextUi;

public class HelpCommand extends Command {

    public static final String commandSyntax = "help";
    public static final String commandAction = "Displays all the available commands";

    private static final String FIXED_SYNTAX_FORMAT = "| %-32.32s";
    private static final String FIXED_ACTION_FORMAT = "| %-55.55s|";
    private static final String FIXED_SERIAL_FORMAT = "| %-3.3s";
    private static final String FIXED_SPACING = "%8.8s";
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
        printCommandHelps(4, AddCommand.commandSyntax, AddCommand.commandAction);
        printCommandHelps(5, DeleteCommand.commandSyntax, DeleteCommand.commandAction);
        printCommandHelps(6, ClearCommand.commandSyntax, ClearCommand.commandAction);
        printCommandHelps(7, EditCommand.commandSyntax, EditCommand.commandAction);
        printCommandHelps(8, TimetableCommand.commandSyntax, TimetableCommand.commandAction);
        printCommandHelps(9, ChangeSemesterCommand.commandSyntax,
                ChangeSemesterCommand.commandAction);
        printCommandHelps(10, CheckCommand.commandSyntax, CheckCommand.commandAction);
        printCommandHelps(11, StoreResultsCommand.commandSyntax, StoreResultsCommand.commandAction);
        printCommandHelps(12, RemoveCommand.commandSyntax, RemoveCommand.commandAction);
        printCommandHelps(13, CalculateCapCommand.commandSyntax, CalculateCapCommand.commandAction);
        printCommandHelps(14, TranscriptCommand.commandSyntax, TranscriptCommand.commandAction);
        printCommandHelps(15, ExitCommand.commandSyntax, ExitCommand.commandAction);
    }
}

