//@@author Teckwhye

package seedu.duke.command;

import static seedu.duke.storage.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.member.exception.InvalidMemberException;

/**
 * Adds a Member object to MemberList.
 */
public class AddMember {

    /**
     * Constructor. Adds a Member to the MemberList and saves it to hard disk.
     *
     * @param members MemberList containing all members.
     * @param member  Member to be added to MemberList.
     */
    public AddMember(MemberList members, Member member) {
        try {
            if (member != null) {
                boolean isNotADuplicateMember = members.verifyNoDuplicates(member);
                if (isNotADuplicateMember) {
                    int newMemberIndex = members.getMemberListSize() + 1;
                    member.setIndex(newMemberIndex);
                    members.addMember(member);
                    Ui.printAddedMemberMessage(member);
                    File dukeMemberFile = new File("CCAMembers.csv");
                    writeMemberFile(dukeMemberFile, members);
                }
            }
        } catch (InvalidMemberException e) {
            Ui.printAddMemberErrorMessage(e.getMessage());
        }
    }

}
//@@author
