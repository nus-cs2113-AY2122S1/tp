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
import seedu.duke.command.FindTraining;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.storage.AttendanceStorage;
import seedu.duke.storage.MemberStorage;
import seedu.duke.storage.TrainingStorage;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

public class Entry {

    private static final TrainingList trainings = new TrainingList();
    private static final MemberList members = new MemberList();
    private static final AttendanceList attendanceList = new AttendanceList();

    /**
     * Method run on start-up that reads in any existing files and copies their value into the respective lists.
     */
    public static void initializeFiles() {
        MemberStorage.setupMemberFile(members);
        TrainingStorage.setupTrainingFile(trainings);
        AttendanceStorage.setUpAttendanceStorage(attendanceList);
    }

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param entry user raw data input
     * @throws IndexOutOfBoundsException if user keys in done [number] when there is no such task.
     */
    public static void addEntry(String entry) throws NullPointerException {
        Keyword keyword = Parser.getKeywordStatus(entry);
        int trainingIndex = -1;
        int memberIndex = -1;
        int attendanceIndex = -1;
        if (entry.contains(",")) {
            Ui.printNoCommasMessage();
        } else {
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
                Member member = Parser.getMemberDetails(entry, 'A');
                new AddMember(members, member);
                break;
            case ADD_TRAINING_KEYWORD:
                TrainingSchedule training = Parser.getTrainingDescription(entry);
                training.setTrainingIndex(trainings.getTrainingListSize() + 1);
                new AddTraining(trainings, training);
                break;
            case ADD_ATTENDANCE_KEYWORD:
                Attendance attendance = Parser.getAttendanceDetails(entry);
                new AddAttendance(attendanceList, attendance, members, trainings);
                break;
            case DELETE_MEMBER_KEYWORD:
                Object parameter = Parser.getParameter(entry);
                new DeleteMember(members, parameter);
                break;
            case DELETE_TRAINING_KEYWORD:
                parameter = Parser.getParameter(entry);
                new DeleteTraining(trainings, parameter);
                break;
            case DELETE_ATTENDANCE_KEYWORD:
                try {
                    attendanceIndex = Parser.getAttendanceIndex(entry);
                } catch (NumberFormatException e) {
                    System.out.println("Please key in a valid number!");
                    break;
                }
                String trainingName = Parser.getAttendanceTrainingName(entry);
                new DeleteAttendance(attendanceList, trainingName, attendanceIndex);
                break;
            case FIND_MEMBER_KEYWORD:
                String name = Parser.getQuery(entry);
                new FindMember(members, name);
                break;
            case FIND_TRAINING_KEYWORD:
                String trainingToFind = Parser.getQuery(entry);
                new FindTraining(trainings, trainingToFind);
                break;
            case EDIT_TRAINING_KEYWORD:
                try {
                    trainingIndex = Parser.getIndex(entry);
                } catch (NumberFormatException | NullPointerException e) {
                    Ui.printValidNumberNeeded();
                    break;
                }
                TrainingSchedule newTrainingDetail = Parser.getTrainingDescription(entry);
                new EditTraining(trainings, trainingIndex, newTrainingDetail);
                break;
            case EDIT_MEMBER_KEYWORD:
                try {
                    memberIndex = Parser.getIndex(entry);
                } catch (NumberFormatException | NullPointerException e) {
                    Ui.printValidNumberNeeded();
                    break;
                }
                Member newMemberDetail = Parser.getMemberDetails(entry, 'E');
                new EditMember(members, memberIndex, newMemberDetail);
                break;
            case NO_KEYWORD:
                Ui.printWrongInputMessage();
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
}