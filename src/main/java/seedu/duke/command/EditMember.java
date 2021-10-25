package seedu.duke.command;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

/**
 * Edits a Member located in MemberList.
 */
public class EditMember {

    /**
     * Constructor. Edits a Member in MemberList. Member is identified by its index.
     *
     * @param members MemberList containing all members.
     * @param index Index of the member to edit. Note that the actual index is index-1.
     * @param toChange Member containing details that needs to be changed.
     */
    public EditMember(MemberList members, int index, Member toChange) {
        try {
            assert index >= 1;

            Member memberToChange = members.getMemberList().get(index - 1);

            Member oldMember = new Member();
            oldMember.setName(memberToChange.getName());
            oldMember.setStudentNumber(memberToChange.getStudentNumber());
            oldMember.setPhoneNumber(memberToChange.getPhoneNumber());
            oldMember.setGender(memberToChange.getGender());


            if (!toChange.getName().equals("")) {
                memberToChange.setName(toChange.getName());
            }

            if (!toChange.getStudentNumber().equals("")) {
                memberToChange.setStudentNumber(toChange.getStudentNumber());
            }

            if (!String.valueOf(toChange.getGender()).equals("")) {
                memberToChange.setGender(toChange.getGender());
            }

            if (!String.valueOf(toChange.getPhoneNumber()).equals("")) {
                memberToChange.setPhoneNumber(toChange.getPhoneNumber());
            }

            members.getMemberList().set(index - 1, memberToChange);
            Ui.printEditMessage(oldMember, memberToChange);
            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);

        } catch (AssertionError e) {
            System.out.println("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unfortunately, the index you entered is invalid.");
        }
    }
}
