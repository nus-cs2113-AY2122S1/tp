package scene.clue;

import scene.clue.firstscene.FatherInsurance;
import scene.clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;
import scene.suspect.Suspect;
import scene.suspect.SuspectList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchedClueTrackerTest {
    @Test
    public void viewSearcherdCLues_Father_empty() {
        SuspectList suspects = new SuspectList();

        suspects.addSuspect("Father", new Suspect());

        Clue fatherInsurance = new FatherInsurance();
        Clue fatherTextMessage = new FatherTextMessage();

        suspects.addClueForSuspect("Father", fatherInsurance);
        suspects.addClueForSuspect("Father", fatherTextMessage);

        SearchedClueTracker tracker = new SearchedClueTracker(suspects);
        ArrayList<Clue> actualResult = tracker.searcherdClues("Father");

        assertEquals(0, actualResult.size());
    }

}
