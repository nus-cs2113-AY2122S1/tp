package seedu.duke.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.command.AddAttendance;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.attendance.exception.InvalidAttendanceException;


/**
 * Test cases for functionalities related to AttendanceList. Namely:
 * AddAttendace, DeleteAttendance
 * To Test:
 * Add 1 attendance - Should Pass
 * Add 1 attendance with non-existing member - Should Fail
 * Add 1 attendance with blank member field - Should Fail
 * Add 1 attendance with non-existing training - Should Fail
 * Add 1 attendance with blank training field - Should Fail
 * Add 1 attendance with non-existing status - Should Fail
 * Add 1 attendance with blank status field - Should Fail
 * Add 1 duplicate attendance entry - Should Fail
 * Delete all attendance entries - Should Pass
 * Delete 1 attendance - Should Pass
 */
class AttendanceListTest {

    private MemberList fullMemberList;
    private Member teckHwee;
    private Member ianWang;
    private Member glenn;
    private Member izdiyad;

    private TrainingList fullTrainingList;
    private TrainingSchedule training1;
    private TrainingSchedule training2;
    private TrainingSchedule training3;
    private TrainingSchedule training4;

    private AttendanceList fullAttendanceList;
    private Attendance entry1;
    private Attendance entry2;
    private Attendance entry3;
    private Attendance entry4;

    @BeforeEach
    public void setUp() throws Exception {

        teckHwee = new Member("TAN TECK HWEE", "A0123456A", "F", "98765432", true);
        ianWang = new Member("IAN WANG", "A0234567B", "M", "98441232", true);
        glenn = new Member("GLENN", "A0345678C", "M", "91233344", true);
        izdiyad = new Member("IZDIYAD", "A0456789D", "M", "94376452", true);

        ArrayList<Member> memberList = new ArrayList<Member>();
        memberList.add(teckHwee);
        memberList.add(ianWang);
        memberList.add(glenn);
        memberList.add(izdiyad);
        fullMemberList = new MemberList(memberList);

        training1 = new TrainingSchedule("October Friday Weekly Training 1", "MPSH1", "1 Oct 2021");
        training2 = new TrainingSchedule("October Friday Weekly Training 2", "MPSH1", "8 Oct 2021");
        training3 = new TrainingSchedule("October Friday Weekly Training 3", "MPSH1", "15 Oct 2021");
        training4 = new TrainingSchedule("October Friday Weekly Training 4", "MPSH1", "22 Oct 2021");

        ArrayList<TrainingSchedule> trainingList = new ArrayList<TrainingSchedule>();
        trainingList.add(training1);
        trainingList.add(training2);
        trainingList.add(training3);
        trainingList.add(training4);
        fullTrainingList = new TrainingList(trainingList);


        entry1 = new Attendance(teckHwee.getName(), training1.getTrainingName(), "1");
        entry1.setIndex(1);
        entry2 = new Attendance(ianWang.getName(), training2.getTrainingName(), "1");
        entry2.setIndex(2);
        entry3 = new Attendance(glenn.getName(), training3.getTrainingName(), "1");
        entry3.setIndex(3);
        entry4 = new Attendance(izdiyad.getName(), training4.getTrainingName(), "1");
        entry4.setIndex(4);

        ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
        attendanceList.add(entry1);
        attendanceList.add(entry2);
        attendanceList.add(entry3);
        attendanceList.add(entry4);
        fullAttendanceList = new AttendanceList(attendanceList);
    }

    @Test
    public void addOneAttendanceEntry() {
        String details = "add /att /m izdiyad /n October Friday Weekly Training 2 /d 1";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd + 1);
    }

    @Test
    public void addOneInvalidMemberAttendanceEntry() {
        String details = "add /att /m mark /n October Friday Weekly Training 2 /d 1";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd);
    }

    @Test
    public void addOneEmptyMemberAttendanceEntry() {
        String details = "add /att /m /n October Friday Weekly Training 2 /d 1";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd);
    }

    @Test
    public void addOneInvalidTrainingAttendanceEntry() {
        String details = "add /att /m izdiyad /n October Friday Weekly Training 5 /d 1";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd);
    }

    @Test
    public void addOneEmptyTrainingAttendanceEntry() {
        String details = "add /att /m izdiyad /n /d 1";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd);
    }

    @Test
    public void addOneInvalidStatusAttendanceEntry() {
        String details = "add /att /m izdiyad /n October Friday Weekly Training 2 /d 2";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd);
    }

    @Test
    public void addOneEmptyStatusAttendanceEntry() {
        String details = "add /att /m izdiyad /n October Friday Weekly Training 2";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        entryToAdd.setIndex(sizeBeforeAdd + 1);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd);
    }

    @Test
    public void addOneDuplicateAttendanceEntry() {
        String details = "add /att /m izdiyad /n October Friday Weekly Training 2 /d 1";

        int sizeBeforeAdd = fullAttendanceList.getAttendanceListSize();
        Attendance entryToAdd = Parser.getAttendanceDetails(details);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        new AddAttendance(fullAttendanceList, entryToAdd, fullMemberList, fullTrainingList);
        int sizeAfterAdd = fullAttendanceList.getAttendanceListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd + 1);
    }

    @Test
    void deleteAllAttendanceEntries() {
        try {
            fullAttendanceList.deleteAttendance(4);
            fullAttendanceList.deleteAttendance(3);
            fullAttendanceList.deleteAttendance(2);
            fullAttendanceList.deleteAttendance(1);
            assertEquals(0,fullAttendanceList.getAttendanceListSize());
        } catch (InvalidAttendanceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteOneAttendance() {
        try {
            fullAttendanceList.deleteAttendance(4);
            assertEquals(3,fullAttendanceList.getAttendanceListSize());
        } catch (InvalidAttendanceException e) {
            System.out.println(e.getMessage());
        }
    }
}