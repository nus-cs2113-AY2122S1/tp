package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

class ParserTest {

    private Member teckHwee;
    private MemberList testMemberList;

    @BeforeEach
    public void setUp() {

        teckHwee = new Member("Tan Teck Hwee", "A0123456A", "F", "98765432");

        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(teckHwee);
        testMemberList = new MemberList(memberList);
    }

    /*@Test
    void failedMakeMemberEntry() {
        final String input = "add /m asd";
        Parser.makeMemberEntry(testMemberList, input);
        try {
            assertEquals(testMemberList.getMember(2).getName(), "");
            assertEquals(testMemberList.getMember(2).getStudentNumber(), "");
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        }
    }*/

    @Test
    void invalidKeyword() {
        final String input = "ad ads dwd";
        assertEquals(Parser.getKeywordStatus(input), Keyword.NO_KEYWORD);
    }
}