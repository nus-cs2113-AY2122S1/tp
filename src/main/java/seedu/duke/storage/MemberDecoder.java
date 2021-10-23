package seedu.duke.storage;

import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.MemberParser;

public class MemberDecoder {

    // Index 0 is reserved for indicator of event/task
    private static final int INDEX_OF_NAME = 1;

    protected static Member decodeMemberFromString(String encodedMember) {
        String[] memberDetails = encodedMember.trim().split(Member.MEMBER_DATA_ARGS_DELIMITER);
        String unformattedName = memberDetails[INDEX_OF_NAME];
        // Replace the "_" in the unformatted name with proper whitespaces.
        String formattedName = MemberParser.parseMemberCommand(unformattedName)[0];

        return new Member(formattedName);
    }
}
