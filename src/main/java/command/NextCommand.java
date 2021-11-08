package command;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList)
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        SceneTypes sceneType = sceneList.getCurrentSceneType();
        // If the current scene type is correct killer scene,
        // we set hasCompleted to true.
        // If the current scene type is wrong killer scene,
        // we update scene number and run the new scene.
        // If the current scene type is the truth scene,
        // we set hasCompleted to true and run the last scene.
        // If the current scene type is the guess killer scene,
        // we print the message to ask user to enter suspect name
        // Else we set the investigation to the suspectStage,
        // updates scene number and run the new scene.
        switch (sceneType) {
        case CORRECT_KILLER_SCENE:
            hasCompleted = true;
            break;
        case WRONG_KILLER_SCENE:
            sceneList.updateSceneNumber();
            sceneList.runCurrentScene();
            break;
        case TRUTH_SCENE:
            hasCompleted = true;
            sceneList.runCurrentScene();
            break;
        case GUESS_KILLER_SCENE:
            ui.printEnterKillerName();
            break;
        default:
            investigation.setSuspectStage();
            sceneList.updateSceneNumber();
            sceneList.runCurrentScene();
        }
    }

    @Override
    public boolean exit() {
        return this.hasCompleted;
    }
}
