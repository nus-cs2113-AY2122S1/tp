package command;

import exceptions.InvalidSuspectException;
import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        ui.printInvalidCommandMessage();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
