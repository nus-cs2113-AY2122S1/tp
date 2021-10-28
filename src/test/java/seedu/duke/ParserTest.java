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

    @Test
    void invalidKeyword() {
        final String input = "ad ads dwd";
        assertEquals(Parser.getKeywordStatus(input), Keyword.NO_KEYWORD);
    }
}