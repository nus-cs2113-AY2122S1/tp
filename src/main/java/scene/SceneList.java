package scene;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import storage.GameDataFileDecoder;
import ui.Ui;
import java.io.FileNotFoundException;

public class SceneList {
    private static final Ui ui = new Ui();
    private Scene[] scenes;
    private int currentSceneIndex;
    private static final int STARTING_INDEX_FOR_FILE = 0;
    private static final int INTRODUCTION_SCENE_INDEX = 0;
    private static final int GUESS_KILLER_SCENE_INDEX = 4;
    private static final int CORRECT_KILLER_SCENE_INDEX = 5;
    private static final int WRONG_KILLER_SCENE_INDEX = 6;
    GameDataFileDecoder dataFile;

    /**
     * Creates a SceneList that contains an array of scenes, with its currentSceneIndex obtained from the dataFile.
     * The scenes in the array will be arranged based on the order they were taken in the parameter.
     *
     * @param dataFile Game data file.
     * @param scenes The scenes that are to be contained in this SceneList
     */
    public SceneList(GameDataFileDecoder dataFile, Scene... scenes)
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.dataFile = dataFile;
        this.currentSceneIndex = dataFile.getCurrentSceneIndex();
        this.scenes = scenes;
    }

    /**
     * Sets scene number based on killer being found.
     *
     * @param killerFound is a boolean input.
     *      Update the scene number based on if the killer was found or not.
     */
    public void setSceneNumberAfterSuspecting(boolean killerFound)
            throws DukeFileNotFoundException {
        if (killerFound) {
            this.currentSceneIndex = CORRECT_KILLER_SCENE_INDEX;
        } else {
            this.currentSceneIndex = WRONG_KILLER_SCENE_INDEX;
        }
        dataFile.setCurrentSceneIndex(this.currentSceneIndex);
    }

    public Scene getCurrentScene() {
        assert currentSceneIndex <= 7;
        return this.scenes[currentSceneIndex];
    }

    private void resetToIntroductionScene()
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex = STARTING_INDEX_FOR_FILE;
        updateDataFileSceneIndex(STARTING_INDEX_FOR_FILE);
    }

    /**
     * Returns the current scene index.
     *
     * @return The current scene's index.
     */
    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    /**
     * Increases the scene number by 1.
     */
    public void updateSceneNumber()
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex++;
        assert currentSceneIndex <= 7;
        updateDataFileSceneIndex(currentSceneIndex);
    }

    /**
     * Gets the current SceneType.
     */
    public SceneTypes getCurrentSceneType() {
        Scene currentScene = this.getCurrentScene();
        return currentScene.getSceneType();
    }

    /**
     * Runs the current scene.
     */
    public void runCurrentScene() {
        Scene currentScene = this.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            ui.printFileErrorMessage();
        }
    }
   
    /**
     * Resets the scene to the introduction scene and run the introduction scene.
     */
    public void resetAllScenes()
           throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.resetToIntroductionScene();
        runCurrentScene();
    }

    private void resetToGuessKillerScene()
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex = GUESS_KILLER_SCENE_INDEX;
        updateDataFileSceneIndex(GUESS_KILLER_SCENE_INDEX);
    }

    private void goBackOneScene()
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex--;
        assert this.currentSceneIndex >= 0;
        updateDataFileSceneIndex(currentSceneIndex);
    }

    private void updateDataFileSceneIndex(int sceneIndex)
            throws DukeFileNotFoundException {
        dataFile.setCurrentSceneIndex(sceneIndex);
    }

    /**
     * Decreases the scene number based on the current scene type.
     * If the current scene is the introduction scene,
     * we do not reduce the scene index.
     * If it is either the wrong or correct killer guessed scene,
     * we reset the scene number back to the guess killer scene number.
     * Else we reduce to the introduction scene.
     */
    private void decreaseSceneNumber()
            throws DukeCorruptedFileException, DukeFileNotFoundException {

        // We do not allow users to go back to any scene with
        // scene number less than 0

        SceneTypes sceneType = getCurrentSceneType();
        switch (sceneType) {
        case INTRODUCTION_SCENE:
            break;
        case WRONG_KILLER_SCENE:
            //fallthrough
        case CORRECT_KILLER_SCENE:
            this.resetToGuessKillerScene();
            break;
        default:
            this.goBackOneScene();
        }
    }

    /**
     * Sets the current scene number to the previous scene number,
     * and run the current scene.
     */
    public void previousScene()
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        decreaseSceneNumber();
        runCurrentScene();
    }
}
