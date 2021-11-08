package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import exceptions.InvalidInputException;
import exceptions.MissingSceneFileException;
import org.junit.jupiter.api.Test;
import parser.Parser;
import scene.SceneList;
import scene.SceneListBuilder;
import scene.clue.CheckedClueTrackerBuilder;
import scene.suspect.SuspectList;
import storage.GameDataFileDecoder;
import ui.Ui;
import investigation.Investigation;
import scene.suspect.SuspectList;

public class CommandTest {
    @Test
    public void commandTest() throws MissingSceneFileException, DukeCorruptedFileException,
            DukeFileNotFoundException, InvalidInputException {
        Ui ui = new Ui();
        SuspectList clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        Investigation investigation = new Investigation(clueTracker);

        Parser parser = new Parser();
        GameDataFileDecoder dataFile = new GameDataFileDecoder("Data.txt");
        SceneList sceneList = SceneListBuilder.buildSceneList(dataFile);

        sceneList.resetAllScenes();
        boolean hasExited;

        String userEntersNext = "/next";
        Command nextCommand = parser.getCommandFromUser(userEntersNext);
        hasExited = nextCommand.exit();
        nextCommand.execute(ui, investigation, sceneList);
        assertEquals(false, hasExited);

        String userEntersSuspectName = "/investigate father";
        Command investigateCommand = parser.getCommandFromUser(userEntersSuspectName);
        hasExited = investigateCommand.exit();
        investigateCommand.execute(ui, investigation, sceneList);
        assertEquals(false, hasExited);

    }

    @Test
    public void restartCommandTest() throws MissingSceneFileException, DukeCorruptedFileException,
            DukeFileNotFoundException, InvalidInputException {
        Ui ui = new Ui();
        SuspectList clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        Investigation investigation = new Investigation(clueTracker);

        Parser parser = new Parser();
        GameDataFileDecoder dataFile = new GameDataFileDecoder("Data.txt");
        SceneList sceneList = SceneListBuilder.buildSceneList(dataFile);

        sceneList.resetAllScenes();
        boolean hasExited;

        String userEntersRestart = "/restart";
        Command restartCommand = parser.getCommandFromUser(userEntersRestart);
        restartCommand.execute(ui, investigation, sceneList);
        hasExited = restartCommand.exit();
        assertEquals(false, hasExited);

    }

    @Test
    public void exitCommandTest() throws MissingSceneFileException, DukeCorruptedFileException,
            DukeFileNotFoundException, InvalidInputException {
        Ui ui = new Ui();
        SuspectList clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        Investigation investigation = new Investigation(clueTracker);

        Parser parser = new Parser();
        GameDataFileDecoder dataFile = new GameDataFileDecoder("Data.txt");
        SceneList sceneList = SceneListBuilder.buildSceneList(dataFile);

        sceneList.resetAllScenes();
        boolean hasExited;

        String userEntersExit = "/exit";
        Command exitCommand = parser.getCommandFromUser(userEntersExit);
        exitCommand.execute(ui, investigation, sceneList);
        hasExited = exitCommand.exit();
        assertEquals(true, hasExited);

    }

    @Test
    public void backCommandTest() throws MissingSceneFileException, DukeCorruptedFileException,
            DukeFileNotFoundException, InvalidInputException {
        Ui ui = new Ui();
        SuspectList clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
        Investigation investigation = new Investigation(clueTracker);

        Parser parser = new Parser();
        GameDataFileDecoder dataFile = new GameDataFileDecoder("Data.txt");
        SceneList sceneList = SceneListBuilder.buildSceneList(dataFile);

        sceneList.resetAllScenes();
        boolean hasExited;

        String userEntersBack = "/back";
        Command backCommand = parser.getCommandFromUser(userEntersBack);
        backCommand.execute(ui, investigation, sceneList);
        hasExited = backCommand.exit();
        assertEquals(false, hasExited);

    }
}
