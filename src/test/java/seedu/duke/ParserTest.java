package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

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

        ArrayList<Member> memberList = new ArrayList<Member>();
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
        final String string = "add /m Tan Teck Hwee /s A0123456A /g F /p 98765432";
        Member member = Parser.getMemberDetails(string);
        assertEquals("Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | Phone Number: 98765432",
                member.toString());
    }

    @Test
    void deleteMember() {
        final String string = "delete /m 1";
        Parser.deleteMember(testMemberList, string);
        assertEquals("The following member have been deleted\nName: Tan Teck Hwee | Student Number: A0123456A "
                + "| Gender: F | Phone Number: 98765432\n", outContent.toString());
    }

    @Test
    void makeMemberEntry() {
        final String string = "add /m Ian Wang /s A0234567B /g M /p 98441232";
        Parser.makeMemberEntry(testMemberList, string);
        assertEquals("Added a Member: Name: Ian Wang | Student Number: A0234567B | Gender: M | Phone Number: "
                        + "98441232\n", outContent.toString());
    }
}