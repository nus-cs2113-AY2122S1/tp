package command;


import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        ui.printExitMessage();
    }

    @Override
    public boolean exit() {
        return true;
    }
}
