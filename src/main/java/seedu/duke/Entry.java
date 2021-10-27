package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.command.AddAttendance;
import seedu.duke.command.AddMember;
import seedu.duke.command.AddTraining;
import seedu.duke.command.DeleteAttendance;
import seedu.duke.command.DeleteMember;
import seedu.duke.command.DeleteTraining;
import seedu.duke.command.EditMember;
import seedu.duke.command.EditTraining;
import seedu.duke.command.FindMember;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

public class Entry {

    private static final TrainingList trainings = new TrainingList();
    private static final MemberList members = new MemberList();
    private static final AttendanceList attendanceList = new AttendanceList();

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param entry user raw data input
     * @param flag  indicates whether the program is run the first time
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
            break;
        case ADD_TRAINING_KEYWORD:
            TrainingSchedule training = Parser.getTrainingDescription(entry);
            new AddTraining(trainings, training);
            break;
        case ADD_ATTENDANCE_KEYWORD:
            Attendance attendance = Parser.getAttendanceDetails(entry);
            new AddAttendance(attendanceList, attendance);
            break;
        case DELETE_MEMBER_KEYWORD:
            Object parameter = Parser.getParameter(entry);
            new DeleteMember(members, parameter);
            break;
        case DELETE_TRAINING_KEYWORD:
            trainingIndex = Parser.getIndex(entry);
            new DeleteTraining(trainings, trainingIndex);
            break;
        case DELETE_ATTENDANCE_KEYWORD:
            //delete /att /t Friday Training /i 2

            attendanceIndex = Parser.getAttendanceIndex(entry);
            //havent handle if i is not a number aka number format exception

            String trainingName = Parser.getAttendanceTrainingName(entry);
            new DeleteAttendance(attendanceList, trainingName, attendanceIndex);
            break;
        case FIND_MEMBER_KEYWORD:
            String name = Parser.findInMembers(entry);
            new FindMember(members, name);
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