package seedu.duke.storage;

import seedu.duke.items.characteristics.Member;
import seedu.duke.items.mainlists.MemberRoster;

import java.util.ArrayList;
import java.util.List;

public class MemberEncoder {

    private static final String INTER_MEMBER_DELIMITER = " +";
    private static final String NAME_SPACER = "_";

    public static List<String> encodeMembersList(MemberRoster membersToSave) {
        List<String> encodedMembers = new ArrayList<>();
        membersToSave.forEach(member -> encodedMembers.add(encodeMemberToString(member)));
        return encodedMembers;
    }

    private static String encodeMemberToString(Member member) {
        return "m | " + encodeMemberNameToString(member);
    }

    private static String encodeMemberNameToString(Member member) {
        String name = member.getName();
        name = name.replaceAll(INTER_MEMBER_DELIMITER, NAME_SPACER);
        return name;
    }
}
