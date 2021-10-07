package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.member.Member;

class ParserTest {

    @Test
    void getMemberDetails() {
        final String string = "add /m Tan Teck Hwee /s A0123456A /g F /p 98765432";
        Member member = Parser.getMemberDetails(string);
        assertEquals("Name: Tan Teck Hwee | Student Number: A0123456A | Gender: F | Phone Number: 98765432",
                member.toString());
    }
}