//@@author Teckwhye

package seedu.duke.command;

import static seedu.duke.storage.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.member.exception.InvalidMemberException;

/**
 * Edits a Member located in MemberList.
 */
public class EditMember {

    private Member oldMember;

    /**
     * Constructor. Edits a Member in MemberList. Member is identified by its index.
     *
     * @param members  MemberList containing all members.
     * @param index    Index of the member to edit. Note that the actual index is index-1.
     * @param toChange Member containing details that needs to be changed.
     */
    public EditMember(MemberList members, int index, Member toChange) {
        try {
            System.out.println();
            if (toChange == null || !members.verifyNoDuplicates(toChange)) {
                return;
            }
            assert index >= 1;

            Member memberToChange = members.getMemberList().get(index - 1);

            oldMember = new Member(memberToChange);
            if (!toChange.getName().equals("")) {
                memberToChange.setName(toChange.getName());
            }

            if (!toChange.getStudentNumber().equals("")) {
                memberToChange.setStudentNumber(toChange.getStudentNumber());
            }

            if (!toChange.getGender().equals("")) {
                memberToChange.setGender(toChange.getGender());
            }

            if (!toChange.getPhoneNumber().equals("")) {
                memberToChange.setPhoneNumber(toChange.getPhoneNumber());
            }

            members.getMemberList().set(index - 1, memberToChange);
            Ui.printEditMessage(oldMember, memberToChange);
            File dukeMemberFile = new File("CCAMembers.csv");
            writeMemberFile(dukeMemberFile, members);

        } catch (AssertionError e) {
            Ui.printEditMemberErrorMessage("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            Ui.printEditMemberErrorMessage("Unfortunately, the index you entered is invalid.");
        } catch (InvalidMemberException e) {
            Ui.printEditMemberErrorMessage(e.getMessage());
        }
    }
}
//@@author
