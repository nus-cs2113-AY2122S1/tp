package seedu.duke.attendance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.member.Member;
import seedu.duke.training.TrainingSchedule;

public class AttendanceListTest {

    private Member teckHwee;
    private Member ianWang;
    private Member glenn;
    private Member izdiyad;

    private AttendanceList attendanceList;

    private Attendance entry1;
    private Attendance entry2;
    private Attendance entry3;
    private Attendance entry4;

    private TrainingSchedule training1;
    private TrainingSchedule training2;
    private TrainingSchedule training3;
    private TrainingSchedule training4;

    @BeforeEach
    public void setUp() throws Exception {

        teckHwee = new Member("Tan Teck Hwee", "A0123456A", "F", "98765432");
        ianWang = new Member("Ian Wang", "A0234567B", "M", "98441232");
        glenn = new Member("Glenn", "A0345678C", "M", "91233344");
        izdiyad = new Member("Izdiyad", "A0456789D", "M", "94376452");

        training1 = new TrainingSchedule("October Friday Weekly Training 1", "MPSH1", "1 Oct 2021");
        training2 = new TrainingSchedule("October Friday Weekly Training 2", "MPSH1", "8 Oct 2021");
        training3 = new TrainingSchedule("October Friday Weekly Training 3", "MPSH1", "15 Oct 2021");
        training4 = new TrainingSchedule("October Friday Weekly Training 4", "MPSH1", "22 Oct 2021");

        entry1 = new Attendance(teckHwee.getName(), training1.getTrainingName(), "1");
        entry2 = new Attendance(ianWang.getName(), training2.getTrainingName(), "1");
        entry3 = new Attendance(glenn.getName(), training3.getTrainingName(), "1");
        entry4 = new Attendance(izdiyad.getName(), training4.getTrainingName(), "1");

        ArrayList<Attendance> inputAttendanceList = new ArrayList<Attendance>();
        inputAttendanceList.add(entry1);
        inputAttendanceList.add(entry2);
        inputAttendanceList.add(entry3);
        inputAttendanceList.add(entry4);
        attendanceList = new AttendanceList(inputAttendanceList);
    }

    @Test
    void deleteAllAttendance() {
        attendanceList.deleteAttendance(4);
        attendanceList.deleteAttendance(3);
        attendanceList.deleteAttendance(2);
        attendanceList.deleteAttendance(1);
        assertTrue(attendanceList.getAttendanceList().size() == 0);

    }

}
