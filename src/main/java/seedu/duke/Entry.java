package seedu.duke;

import static java.lang.System.exit;

import seedu.duke.attendance.*;
import seedu.duke.command.*;
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
        int trainingIndex = -1;
        int memberIndex = -1;
        int attendanceIndex = -1;

        if (flag == 0) {
            MemberStorage.setupMemberFile(members);
            AttendanceStorage.setUpAttendanceStorage(attendanceList);
        }

        switch (keyword) {
        case LIST_MEMBER_KEYWORD:
            Ui.printList(members);
            break;
        case LIST_TRAINING_KEYWORD:
            Ui.printList(trainings);
            break;
        case LIST_ATTENDANCE_KEYWORD:
            Ui.printList(Parser.getFilteredAttendanceList(attendanceList, entry));
            Parser.askToListAll(attendanceList);
            break;
        case ADD_MEMBER_KEYWORD:
            Member member = Parser.getMemberDetails(entry);
            new AddMember(members, member);
            //Parser.makeMemberEntry(members, entry);
            break;
        case ADD_TRAINING_KEYWORD:
            TrainingSchedule training = Parser.getTrainingDescription(entry);
            new AddTraining(trainings, training);
            break;
        case ADD_ATTENDANCE_KEYWORD:
            Attendance attendance = Parser.getAttendanceDetails(entry);
            new AddAttendance(attendanceList, attendance);
            //Parser.makeAttendanceEntry(attendanceList, entry);
            break;
        case DELETE_MEMBER_KEYWORD:
            memberIndex = Parser.getIndex(entry);
            new DeleteMember(members, memberIndex);
            //Parser.deleteMember(members, entry);
            break;
        case DELETE_TRAINING_KEYWORD:
            trainingIndex = Parser.getIndex(entry);
            new DeleteTraining(trainings, trainingIndex);
            break;
        case DELETE_ATTENDANCE_KEYWORD:
            attendanceIndex = Parser.getLastIndex(entry);
            new DeleteAttendance(attendanceList, entry, Parser.getFilteredAttendanceList(attendanceList, entry), attendanceIndex);
            //Parser.deleteAttendance(attendanceList, entry);
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
            trainingIndex = Parser.getIndex(entry);
            TrainingSchedule newTrainingDetail = Parser.getTrainingDescription(entry);
            new EditTraining(trainings, trainingIndex, newTrainingDetail);
            break;
        case EDIT_MEMBER_KEYWORD:
            memberIndex = Parser.getIndex(entry);
            Member newMemberDetail = Parser.getMemberDetails(entry);
            new EditMember(members, memberIndex, newMemberDetail);
            //Parser.editMember(members, entry);
            break;
        case NO_KEYWORD:
            Parser.wrongInputTypeMessage();
            break;
        case HELP_KEYWORD:
            Ui.printHelp();
            break;
        case EXIT_KEYWORD:
            Ui.printExitMessage();
            break;
        default:
            assert false : keyword;
        }
    }
}