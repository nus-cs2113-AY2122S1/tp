package storage;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import exceptions.MissingSceneFileException;

public class GameDataFileDecoder extends GameFileManager {
    private static final int MAX_SCENE_NUMBER = 3;
    private static final String FACTORY_SETTING = "The Great Detective Data File\nCurrentSceneIndex: ";

    public GameDataFileDecoder(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
    }


    public void setCurrentSceneIndex(int index) throws DukeFileNotFoundException {
        this.writeFile(FACTORY_SETTING + index);
    }

    /**
     * Gets an int of the CurrentSceneIndex read from the data file.
     * Resets the current index to 0 if the file is corrupted.
     *
     * @return An int value of the CurrentSceneIndex.
     * @throws DukeFileNotFoundException If the file is missing.
     */
    public int getCurrentSceneIndex() throws DukeFileNotFoundException {
        int currentSceneIndex = 0;
        try {
            if (isValidFile()) {
                String lines = this.readFile();
                currentSceneIndex = Integer.parseInt(lines.substring(FACTORY_SETTING.length()));
                if (currentSceneIndex <= MAX_SCENE_NUMBER) {
                    return currentSceneIndex;
                }
            }
        } catch (DukeCorruptedFileException e) {
            System.out.println(e.getMessage());
            setCurrentSceneIndex(0);
        }
        setCurrentSceneIndex(0);
        return 0;
    }

    /**
     * Builds the scene list containing the scenes.
     *
     * @return true if the file is compliant with FACTORY_SETTING.
     * @throws DukeCorruptedFileException If the file is corrupted.
     * @throws DukeFileNotFoundException If the file is missing.
     */
    public boolean isValidFile() throws DukeCorruptedFileException, DukeFileNotFoundException {
        String lines = this.readFile();
        return lines.contains("The Great Detective Data File");
    }

}
