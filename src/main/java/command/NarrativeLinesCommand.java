package command;

import exceptions.InvalidSuspectException;
import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class NarrativeLinesCommand extends Command {
    private int parsedUserInput;

    public NarrativeLinesCommand(int parsedUserInput) {
        this.parsedUserInput = parsedUserInput;
    }

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws InvalidSuspectException {
        Investigation.numLinesToPrintForNarrative = parsedUserInput;
        ui.printSuccessChangeNarrativeLines(parsedUserInput);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
