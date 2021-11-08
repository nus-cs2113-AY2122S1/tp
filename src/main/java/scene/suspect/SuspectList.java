package scene.suspect;

import scene.clue.Clue;
import scene.SceneListBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class SuspectList {
    protected LinkedHashMap<String, Suspect> suspects;
    protected final String[] suspectsNames = new String[]{"Father", "Kevin", "Wendy", "Ling", "Zack"};

    public SuspectList() {
        this.suspects = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Suspect> getSuspects() {
        return suspects;
    }

    public int getNumSuspects() {
        return suspects.size();
    }

    /**
     * Adds a suspect to the list of suspects.
     *
     * @param name Name of suspect.
     * @param suspect Suspect.
     */
    public void addSuspect(String name, Suspect suspect) {
        suspects.put(name, suspect);
    }

    /**
     * Adds a clue for the specified subject.
     *
     * @param name Name of suspect.
     * @param clue Clue to be added for suspect.
     */
    public void addClueForSuspect(String name, Clue clue) {
        assert suspects.containsKey(name);
        suspects.get(name).addClue(clue);
    }

    /**
     * Sets the clue specified for the given suspect to be checked.
     *
     * @param name Name of suspect.
     * @param clueInScene Clue to be marked as checked.
     */
    public void setClueChecked(String name, Clue clueInScene) {
        int indexInClueTracker = this.getClueIndex(name, clueInScene.getClueName());
        Clue clueInTracker = this.getSuspectAllClues(name).get(indexInClueTracker);
        assert Arrays.asList(suspectsNames).contains(name);
        this.suspects.get(name).setChecked(clueInTracker);
    }

    /**
     * Gets all the clues for the given suspect.
     *
     * @param name Name of suspect.
     * @return List of clues belonging to the given suspect.
     */
    public ArrayList<Clue> getSuspectAllClues(String name) {
        assert Arrays.asList(suspectsNames).contains(name);
        return suspects.get(name).getClues();
    }

    /**
     * Gets all the unchecked clues for the given suspect.
     *
     * @param name Name of suspect.
     * @return List of unchecked clues belonging to the given suspect.
     */
    public ArrayList<Clue> getSuspectAvailableClues(String name) {
        assert Arrays.asList(suspectsNames).contains(name);
        return suspects.get(name).getAvailableClues();
    }

    /**
     * Gets all the checked clues for the given suspect.
     *
     * @param name Name of suspect.
     * @return List of checked clues belonging to the given suspect.
     */
    public ArrayList<Clue> getSuspectCheckedClues(String name) {
        assert Arrays.asList(suspectsNames).contains(name);
        return suspects.get(name).getCheckedClues();
    }

    /**
     * Gets all the clues from all suspects.
     *
     * @return List of clues.
     */
    public ArrayList<Clue> getAllClues() {
        ArrayList<Clue> clues = new ArrayList<>();
        for (Map.Entry<String, Suspect> suspectEntry : suspects.entrySet()) {
            clues.addAll(0, suspectEntry.getValue().getClues());
        }
        return clues;
    }

    /**
     * Gets all the unchecked clues from all suspects.
     *
     * @return List of unchecked clues.
     */
    public ArrayList<Clue> getAllAvailableClues() {
        ArrayList<Clue> clues = new ArrayList<>();
        for (Map.Entry<String, Suspect> suspectEntry : suspects.entrySet()) {
            clues.addAll(0, suspectEntry.getValue().getAvailableClues());
        }
        return clues;
    }

    /**
     * Gets the names of all the suspects.
     *
     * @return Array of Strings of suspect names.
     */
    public String[] getSuspectNames() {
        String[] suspectNames = new String[getNumSuspects()];
        for (int i = 0; i < getNumSuspects(); i++) {
            suspectNames[i] = (String) suspects.keySet().toArray()[i];
        }
        return suspectNames;
    }

    /**
     * Gets the index of the clue of the specified suspect.
     *
     * @param suspectName Name of suspect.
     * @param clueName Name of clue.
     * @return Index of the clue of the specified suspect.
     */
    public int getClueIndex(String suspectName, String clueName) {
        ArrayList<Clue> clues = this.getSuspectAllClues(suspectName);
        for (int i = 0; i < clues.size(); i++) {
            if (clues.get(i).getClueName().equals(clueName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < getNumSuspects(); i++) {
            toReturn.append(i + 1).append(". ").append((String) suspects.keySet().toArray()[i]).append("\n");
        }
        return toReturn.toString();
    }

}
