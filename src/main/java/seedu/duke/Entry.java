package seedu.duke;

import java.util.ArrayList;
import seedu.duke.member.MemberList;

public class Entry {
    private static final ArrayList<TrainingSchedule> trainings = new ArrayList<>();
    private static final MemberList members = new MemberList();

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param entry user raw data input
     * @throws IndexOutOfBoundsException if user keys in done [number] when there is no such task.
     */
    public static void addEntry(String entry) throws NullPointerException {
        Keyword keyword = Parser.getKeywordStatus(entry);
        switch (keyword) {
        case MEMBER_ENTRY:
            Parser.doDoneTask(members, entry);
            break;
        case TRAINING_SCHEDULE_ENTRY:
            Parser.makeTodoTask(trainings, entry);
            break;
        case LIST_MEMBER_KEYWORD:
            Ui.printList(members);
            break;
        case LIST_TRAINING_KEYWORD:
            Ui.printList(trainings);
        case ADD_MEMBER_KEYWORD:
            Parser.makeMemberEntry(members, entry);
            break;
        case ADD_TRAINING_KEYWORD:
            Parser.addItem(trainings, entry);
        case DELETE_MEMBER_KEYWORD:
            Parser.deleteMember(members, entry);
            break;
        case DELETE_TRAINING_KEYWORD:
            Parser.deleteItem(trainings, entry);
        case FIND_KEYWORD:
            Parser.findMatching(members, entry);
            break;
        case NO_KEYWORD:
            Parser.wrongInputTypeMessage();
            break;
        case EXIT_KEYWORD:
            Ui.printGoodbyeMessage();
            break;
        }
    }
}