package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.items.characteristics.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberDecoderTest {

    @Test
    void decodeMemberFromString_stringValidFormat_expectOneMember() {
        // Setting up
        String encodedMember = "m | JOHN_DOE";
        Member decodedMember = MemberDecoder.decodeMemberFromString(encodedMember);

        String expectedName = "JOHN DOE";
        assertEquals(expectedName, decodedMember.getName());
    }
}
