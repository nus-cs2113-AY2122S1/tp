package command;

import scene.clue.Clue;
import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

import java.util.ArrayList;

public class ViewCommand extends Command {
    private final String[] suspects;
    private boolean hasNoSpecifiedSuspects;

    public ViewCommand() {
        suspects = new String[]{"Father", "Kevin", "Wendy", "Ling", "Zack"};
        hasNoSpecifiedSuspects = true;
    }

    public ViewCommand(String[] args) {
        suspects = args;
        hasNoSpecifiedSuspects = false;
    }

    /**
     * Executes the view command. If there are suspect(s) specified by the user,
     * prints already searched clues relating to them. Else print all clues that
     * have been searched.
     *
     * @param ui Used to communicate with the user.
     * @param investigation Contains method to query the already searched clues.
     * @param sceneList Not used in this method but passed in as a standard of
     *                      command execution.
     */
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) {
        ui.printViewingCheckedCluesMessage();
        findSearchedClues(ui, investigation);
    }

    /**
     * Finds the already searched clues for suspects specified by the user.
     * 
     * @param ui Used to communicate with the user.
     * @param investigation Contains method to query the already searched clues.
     */
    private void findSearchedClues(Ui ui, Investigation investigation) {
        boolean hasSearchedClues = false;
        for (String name : suspects) {
            ArrayList<Clue> clues = investigation.getSuspectCheckedClues(name);
            if (clues.isEmpty()) {
                ui.printNoSearchedClues(hasNoSpecifiedSuspects, name);
                continue;
            }
            ui.printSearchedClues(name, clues);
            hasSearchedClues = true;
        }
        if (hasNoSpecifiedSuspects & !hasSearchedClues) {
            ui.printNoSearchedClues();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
