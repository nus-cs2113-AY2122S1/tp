package command;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class BackCommand extends Command {

    /**
     * Checks if the user has started investigation on a suspect or a clue.
     * If the user has not started investigation, we go to the previous scene.
     * Else we reset the scene to ask the user which suspect they want to inspect.
     *
     * @param investigation Investigation object
     * @param sceneList SceneList object
     */
    private void backToCorrectScene(Investigation investigation, SceneList sceneList) 
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        boolean hasStartedInvestigation = investigation.hasStartedInvestigation();
        if (hasStartedInvestigation) {
            investigation.setSuspectStage();
        } else {
            sceneList.previousScene();
        }
    }

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList)
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        // If the user is currently in the INVESTIGATE_SCENE and enters
        // /back as the input,
        // We should bring the user back to the start of the investigation scene
        SceneTypes sceneType = sceneList.getCurrentSceneType();
        if (sceneType != SceneTypes.INVESTIGATE_SCENE) {
            investigation.setSuspectStage();
            sceneList.previousScene();
        } else {
            backToCorrectScene(investigation, sceneList);
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
