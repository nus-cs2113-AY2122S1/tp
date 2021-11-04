//@@author Teckwhye

package seedu.duke.command;

import static seedu.duke.storage.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.member.exception.InvalidMemberException;

/**
 * Deletes a Member from the MemberList.
 */
public class DeleteMember {

    /**
     * Constructor. Deletes a Member from the MemberList given its index.
     *
     * @param members   MemberList to delete Member from.
     * @param parameter Index of Member object to delete. Note that the actual index is index -1.
     */
    public DeleteMember(MemberList members, Object parameter) {
        if (parameter instanceof Integer) {
            deleteMemberByIndex(members, (Integer) parameter);
        } else if (parameter instanceof String) {
            deleteMemberByString(members, (String) parameter);
        } else {
            System.out.println("Error in processing parameter, please input either name or index of member to delete.");
        }
    }

    public void deleteMemberByIndex(MemberList members, int index) {
        try {
            assert index >= 1;
            Member toDelete = members.deleteMemberByIndex(index);
            Ui.printDeletedMemberMessage(toDelete);
            File dukeMemberFile = new File("CCAMembers.csv");
            writeMemberFile(dukeMemberFile, members);
        } catch (IndexOutOfBoundsException | AssertionError e) {
            System.out.println("please input a valid member index or member name");
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMemberByString(MemberList members, String name) {
        try {
            assert !name.equals("");
            Member toDelete = members.deleteMemberByName(name);
            Ui.printDeletedMemberMessage(toDelete);
            File dukeMemberFile = new File("CCAMembers.csv");
            writeMemberFile(dukeMemberFile, members);
        } catch (InvalidMemberException e) {
            Ui.printDeleteMemberErrorMessage(e.getMessage(), e.getMembers(), name);
        } catch (AssertionError e) {
            System.out.println("please input a valid member index or member name");
        }
    }
}
//@@author