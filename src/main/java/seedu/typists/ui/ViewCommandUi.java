package seedu.typists.ui;

import seedu.typists.game.GameRecord;

import java.util.ArrayList;

public class ViewCommandUi {
    public static void displayGameRecords(ArrayList<GameRecord> gameRecords) {
        for (GameRecord gameRecord:gameRecords) {
            SummaryUi.displaySummary(gameRecord);
        }
    }

    public static void displayHelp() {
        //do after user guide
        System.out.print("display help placeholder\n");
    }
}
