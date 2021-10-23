package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.items.characteristics.Member;
import seedu.duke.items.mainlists.MemberRoster;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberEncoderTest {

    @Test
    void encodeMembersList_listOfTwoMembers_expectListOfTwoStrings() {
        // Setting up
        Member member1 = new Member("John Doe");
        Member member2 = new Member("Jane Doe");
        MemberRoster memberRoster = MemberRoster.getInstance();
        memberRoster.add(member1);
        memberRoster.add(member2);
        List<String> encodedMemberRoster = MemberEncoder.encodeMembersList(memberRoster);

        // Check number of items in encodedMemberList
        assertEquals(2, encodedMemberRoster.size());

        // Check the members are saved in the correct String format
        String expectedResult1 = "m | John_Doe";
        assertEquals(expectedResult1, encodedMemberRoster.get(0));
        String expectedResult2 = "m | Jane_Doe";
        assertEquals(expectedResult2, encodedMemberRoster.get(1));
    }
}