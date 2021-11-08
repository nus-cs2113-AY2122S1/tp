package scene.suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import scene.clue.Clue;
import scene.clue.firstscene.FatherInsurance;
import scene.clue.firstscene.FatherMap;
import scene.clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;
import scene.clue.secondscene.KevinGift;

import java.util.ArrayList;

public class SuspectListTest {

    @Test
    public void listOfSuspects() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        suspects.addSuspect("Wendy", new Suspect());

        assertEquals("[Father, Wendy]", suspects.getSuspects().keySet().toString());
    }

    @Test
    public void listOfSuspectsWithSuspectClues() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        suspects.addSuspect("Wendy", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();
        Clue fatherMap = new FatherMap();

        suspects.addClueForSuspect("Father", fatherInsurance);
        suspects.addClueForSuspect("Father", fatherTextMessage);
        suspects.addClueForSuspect("Wendy", fatherMap);

        assertEquals(2, suspects.getSuspectAvailableClues("Father").size());

        assertFalse(suspects.getSuspectAllClues("Father").get(0).isChecked());
        suspects.setClueChecked("Father", fatherInsurance);
        assertTrue(suspects.getSuspectAllClues("Father").get(0).isChecked());

        assertEquals(1, suspects.getSuspectAvailableClues("Father").size());

        ArrayList<Clue> checkedClues = new ArrayList<>();
        checkedClues.add(fatherInsurance);
        assertEquals(checkedClues, suspects.getSuspectCheckedClues("Father"));
    }

    @Test
    public void listOfSuspectsWithAllClues() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        suspects.addSuspect("Wendy", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();
        Clue fatherMap = new FatherMap();

        suspects.addClueForSuspect("Father", fatherInsurance);
        suspects.addClueForSuspect("Father", fatherTextMessage);
        suspects.addClueForSuspect("Wendy", fatherMap);

        ArrayList<Clue> allClues = new ArrayList<>();
        allClues.add(fatherMap);
        allClues.add(fatherInsurance);
        allClues.add(fatherTextMessage);

        assertEquals(allClues, suspects.getAllClues());

        suspects.setClueChecked("Father", fatherInsurance);

        ArrayList<Clue> availableClues = new ArrayList<>();
        availableClues.add(fatherMap);
        availableClues.add(fatherTextMessage);
        assertEquals(availableClues, suspects.getAllAvailableClues());
    }

    @Test
    public void suspectListClueNotFound() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        Clue fatherInsurance = new FatherInsurance();

        suspects.addClueForSuspect("Father", fatherInsurance);

        Clue kevinGift = new KevinGift();
        int index = suspects.getClueIndex("Father", kevinGift.getClueName());
        assertEquals(-1, index);

        index = suspects.getClueIndex("Father", fatherInsurance.getClueName());
        assertEquals(0, index);
    }

    @Test
    public void suspectListToString() {
        SuspectList suspects = new SuspectList();
        suspects.addSuspect("Father", new Suspect());
        suspects.addSuspect("Wendy", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();
        Clue fatherMap = new FatherMap();

        suspects.addClueForSuspect("Father", fatherInsurance);
        suspects.addClueForSuspect("Father", fatherTextMessage);
        suspects.addClueForSuspect("Wendy", fatherMap);

        String suspectsString = "1. Father\n2. Wendy\n";
        assertEquals(suspectsString, suspects.toString());
    }
}
