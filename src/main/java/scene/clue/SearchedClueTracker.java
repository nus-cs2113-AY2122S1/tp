package scene.clue;

import scene.suspect.SuspectList;

import java.util.ArrayList;

public class SearchedClueTracker {

    private SuspectList suspects;

    public SearchedClueTracker(SuspectList suspects) {
        this.suspects = suspects;
    }

    /**
     * Gets an ArrayList of already searched clues corresponding
     * to a specific suspect.
     *
     * @param name The name of one of the suspects.
     * @return An array list of searched clues that correspond to
     *     the name of the suspect given.
     */
    public ArrayList<Clue> searcherdClues(String name) {
        assert suspects.getSuspectAvailableClues(name).size() > 0;
        ArrayList<Clue> checkedClues = new ArrayList<>();
        for (Clue clue : suspects.getSuspectAvailableClues(name)) {
            if (clue.isChecked()) {
                checkedClues.add(clue);
            }
        }
        return checkedClues;
    }
}
