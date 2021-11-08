package scene.suspect;

import scene.clue.Clue;

import java.util.ArrayList;

public class Suspect {
    protected ArrayList<Clue> clues;

    public Suspect() {
        this.clues = new ArrayList<>();
    }

    public ArrayList<Clue> getClues() {
        return clues;
    }

    public int getNumClues() {
        return clues.size();
    }

    public void addClue(Clue clue) {
        this.clues.add(clue);
    }

    /**
     * Sets the clue specified as checked.
     *
     * @param clue Clue to be set as checked.
     */
    public void setChecked(Clue clue) {
        int index = clues.indexOf(clue);
        try {
            clues.get(index).setChecked();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to find clue to set checked");
        }
    }

    /**
     * Gets the list of clues which haven't been marked as checked.
     *
     * @return List of unchecked clues.
     */
    public ArrayList<Clue> getAvailableClues() {
        ArrayList<Clue> availableClues = new ArrayList<>();
        for (Clue clue : clues) {
            if (!clue.isChecked()) {
                availableClues.add(clue);
            }
        }
        return availableClues;
    }

    /**
     * Gets the list of clues which have been marked as checked.
     *
     * @return List of checked clues.
     */
    public ArrayList<Clue> getCheckedClues() {
        ArrayList<Clue> checkedClues = new ArrayList<>();
        for (Clue clue : clues) {
            if (clue.isChecked()) {
                checkedClues.add(clue);
            }
        }
        return checkedClues;
    }
}
