package seedu.duke;

import seedu.duke.member.*;
import seedu.duke.training.*;

public class Ui {

    public static void printGoodbyeMessage() {
        System.out.println("Bye!");
    }

    public static void printList(MemberList members) {
        System.out.println("Members test");
    }

    public static void printList(TrainingList trainings) {
        int display = 1;
        for (TrainingSchedule trainingEntries : trainings.getTrainingList()) {
            System.out.println("[" + display + "] " + trainingEntries.toString());
            display++;
        }
    }
}
