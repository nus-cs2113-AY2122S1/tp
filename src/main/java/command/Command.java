package command;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import exceptions.InvalidInputException;
import scene.SceneList;
import ui.Ui;
import investigation.Investigation;

public abstract class Command {
    public abstract void execute(Ui ui, Investigation investigation, SceneList sceneList)
            throws InvalidInputException, DukeCorruptedFileException, DukeFileNotFoundException;

    public abstract boolean exit();

}