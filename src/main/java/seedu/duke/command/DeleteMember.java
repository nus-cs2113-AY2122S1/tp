package seedu.duke.command;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

/**
 * Deletes a Member from the MemberList.
 */
public class DeleteMember {

    /**
     * Constructor. Deletes a Member from the MemberList given its index.
     * @param members MemberList to delete Member from.
     * @param index Index of Member object to delete. Note that the actual index is index -1.
     */
    public DeleteMember(MemberList members, int index) {
        try {
            assert index >= 1;
            Member toDelete = members.deleteMember(index);
            Ui.printDeletedMemberMessage(toDelete);
            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);
            //Update save file
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such member number...");
        } catch (AssertionError e) {
            System.out.println("The index to delete must be an integer >= 1");
        }
    }
}
