package seedu.duke.command;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

/**
 * Adds a Member object to MemberList.
 */
public class AddMember {

    /**
     * Constructor. Adds a Member to the MemberList and saves it to hard disk.
     * @param members MemberList containing all members.
     * @param member Member to be added to MemberList.
     */
    public AddMember(MemberList members, Member member) {
        members.addMember(member);
        Ui.printAddedMemberMessage(member);
        File dukeMemberFile = new File("dukeMembers.csv");
        writeMemberFile(dukeMemberFile, members);
    }
}

//NumberFormatException is thrown when /s 123123123123 is put in
