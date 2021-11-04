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
    public void setUp() throws Exception {

        teckHwee = new Member("Tan Teck Hwee", "A0123456A", "F", "98765432", true);

        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(teckHwee);
        testMemberList = new MemberList(memberList);
    }

    @Test
    void invalidKeyword() {
        final String input = "ad ads dwd";
        assertEquals(Parser.getKeywordStatus(input), Keyword.NO_KEYWORD);
    }

    @Test
    void validTrainingName() {
        final String entry = "Friday Training /d 0";
        final String correctOutput = "Friday Training ";
        assertEquals(Parser.getTrainingName(entry), correctOutput);
    }

    @Test
    void validAttendanceIndex() {
        final String entry = "delete /att /t Friday Training /i 2";
        final int correctOutput = 2;
        assertEquals(Parser.getAttendanceIndex(entry), correctOutput);
    }

    @Test
    void validAttendanceTrainingName() {
        final String entry = "delete /att /t Friday Training /i 2";
        final String correctOutput = "Friday Training";
        assertEquals(Parser.getAttendanceTrainingName(entry), correctOutput);
    }
}