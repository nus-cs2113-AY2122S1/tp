package seplanner.commands;

import seplanner.ui.UiGeneral;

public class ExitCommand extends Command {
    public ExitCommand() {
        UiGeneral.printExit();
    }
}