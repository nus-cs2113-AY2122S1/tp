package seedu.duke;

import static java.lang.System.exit;

import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.training.TrainingList;

public class Entry {
    private static final TrainingList trainings = new TrainingList();
    private static final MemberList members = new MemberList();
    private static final AttendanceList attendanceList = new AttendanceList();

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param entry user raw data input
     * @param flag indicates whether the program is run the first time
     * @throws IndexOutOfBoundsException if user keys in done [number] when there is no such task.
     */
    public static void addEntry(String entry, int flag) throws NullPointerException {
        Keyword keyword = Parser.getKeywordStatus(entry);

        if (flag == 0) {
            MemberStorage.setupMemberFile(members);
        }

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
            break;
        case DELETE_ATTENDANCE_KEYWORD:
            Parser.deleteAttendance(attendanceList, entry);
            break;
        case FIND_MEMBER_KEYWORD:
            Parser.findInMembers(members, entry);
            break;
        case FIND_TRAINING_KEYWORD:
            Parser.findInTraining(trainings, entry);
            break;
        case FIND_ATTENDANCE_KEYWORD:
            Parser.findInAttendanceEntries(attendanceList, entry);
            break;
        case EDIT_TRAINING_KEYWORD:
            Parser.editTraining(trainings, entry);
            break;
        case EDIT_MEMBER_KEYWORD:
            Parser.editMember(members, entry);
            break;
        case NO_KEYWORD:
            Parser.wrongInputTypeMessage();
            break;
        case EXIT_KEYWORD:
            Ui.printExitMessage();
            break;
        default:
            assert false : keyword;
        }
    }
}