package seplanner.commands;

import seplanner.ui.UiGeneral;

/**
 * Exit command class to exit the program.
*/
public class ExitCommand extends Command {
    public ExitCommand() {
        UiGeneral.printExit();
    }
}
