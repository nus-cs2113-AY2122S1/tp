package scene.clue;

import scene.suspect.SuspectList;

import static scene.suspect.SuspectListBuilder.suspectListBuilder;

import java.io.FileNotFoundException;

public class CheckedClueTrackerBuilder {

    /**
     * Builds a tracker for already checked clues according
     * to a text file that records relevant information.
     *
     * @return A SuspectList object representing searched
     *     clues and the corresponding suspects.
     */
    public static SuspectList buildClueTracker() {
        SuspectList suspects = new SuspectList();

        try {
            suspectListBuilder("/clueTracker.txt", suspects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return suspects;
    }
}
