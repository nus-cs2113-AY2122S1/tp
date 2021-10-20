package seedu.duke;

import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.*;
import seedu.duke.training.*;

public class Entry {
    private static final TrainingList trainings = new TrainingList();
    private static final MemberList members = new MemberList();
    private static final AttendanceList attendanceList = new AttendanceList();

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param entry user raw data input
     * @throws IndexOutOfBoundsException if user keys in done [number] when there is no such task.
     */
    public static void addEntry(String entry) throws NullPointerException {
        Keyword keyword = Parser.getKeywordStatus(entry);
        switch (keyword) {
        case LIST_MEMBER_KEYWORD:
            Ui.printList(members);
            break;
        case LIST_TRAINING_KEYWORD:
            Ui.printList(trainings);
            break;
        case LIST_ATTENDANCE_KEYWORD:
            Ui.printList(attendanceList);
            break;
        case ADD_MEMBER_KEYWORD:
            Parser.makeMemberEntry(members, entry);
            break;
        case ADD_TRAINING_KEYWORD:
            Parser.makeTrainingEntry(trainings, entry);
            break;
        case ADD_ATTENDANCE_KEYWORD:
            Parser.makeAttendanceEntry(attendanceList, entry);
            break;
        case DELETE_MEMBER_KEYWORD:
            Parser.deleteMember(members, entry);
            break;
        case DELETE_TRAINING_KEYWORD:
            Parser.deleteTraining(trainings, entry);
        case FIND_MEMBER_KEYWORD:
            Parser.findInMembers(members, entry);
            break;
        case FIND_TRAINING_KEYWORD:
            Parser.findInTraining(trainings, entry);
            break;
        case EDIT_TRAINING_KEYWORD:
            Parser.editTraining(trainings, entry);
            break;
        case NO_KEYWORD:
            Parser.wrongInputTypeMessage();
            break;
        case HELP_KEYWORD:
            Ui.printHelp();
            break;
        case EXIT_KEYWORD:
            Ui.printGoodbyeMessage();
            break;
        }
    }
}