package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingSchedule;

class ParserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private Member teckHwee;
    private MemberList testMemberList;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        teckHwee = new Member("Tan Teck Hwee", "A0123456A", 'F', 98765432);

        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(teckHwee);
        testMemberList = new MemberList(memberList);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void getMemberDetails() {
        final String string = "add /m /n Tan Teck Hwee /s A0123456A /g F /p 98765432";
        final String expectedOutput = "Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | Phone Number: "
                + "98765432";
        Member member = Parser.getMemberDetails(string);
        Parser.makeMemberEntry(testMemberList, string);

        assertEquals("Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | Phone Number: 98765432",
                member.toString());
    }

    @Test
    void deleteMember() {
        final String string = "delete /m 1";
        final String expectedOutput = "The following member have been deleted\n"
                + "Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | Phone Number: 98765432\r\n";
        Parser.deleteMember(testMemberList, string);
        //assertTrue(expectedOutput.equals(outContent.toString()));
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void makeMemberEntry() {
        final String string = "add /m /n Ian Wang /s A0234567B /g M /p 98441232";
        final String expectedOutput = "Added a Member: Name: Ian Wang | Student Number: A0234567B | Gender: M | "
                + "Phone Number: 98441232\r\n";
        Parser.makeMemberEntry(testMemberList, string);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void editMember() {
        final String string = "edit /m 1 /n Ian Wang /s A0234567B /g M /p 98441232";
        final String expectedOutput = "Edited member: Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | "
                + "Phone Number: 98765432\r\n"
                + "To become:  Name: Ian Wang | Student Number: A0234567B | Gender: M | Phone Number: 98441232\r\n";
        Parser.editMember(testMemberList, string);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void getTrainingDetails() {
        final String input = "add /t /n October Friday Weekly Training 1 /a 1 Oct 2021 /v MPSH 1";
        final String expectedOutput = "Training Name: October Friday Weekly Training 1 | Venue: MPSH 1 | Time: 1 Oct "
                + "2021";
        TrainingSchedule training = Parser.getTrainingDescription(input);
        assertEquals(expectedOutput, training.toString());
    }
}