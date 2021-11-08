package command;

import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        ui.printListOfCommands();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
