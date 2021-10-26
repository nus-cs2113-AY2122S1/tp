package seedu.duke.command;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;

import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class DeleteMember {
    public DeleteMember(MemberList members, int index) {
        try {
            assert index >= 1;
            Member toDelete = members.deleteMember(index);
            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);
            Ui.printDeletedMemberMessage(toDelete);
            //Update save file
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such member number...");
        } catch (AssertionError e) {
            System.out.println("The index to delete must be an integer >= 1");
        }
    }
}
