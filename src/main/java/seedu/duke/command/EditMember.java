package seedu.duke.command;

import static seedu.duke.storage.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

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
            assert index >= 1;

            Member memberToChange = members.getMemberList().get(index - 1);

            oldMember = new Member(memberToChange);

            if (!toChange.getName().equals("")) {
                if (Parser.isValidName(toChange.getName())) {
                    memberToChange.setName(toChange.getName());
                } else {
                    Ui.printEditNameFailed();
                }
            }

            if (!toChange.getStudentNumber().equals("")) {
                memberToChange.setStudentNumber(toChange.getStudentNumber());
            }

            if (!toChange.getGender().equals("")) {
                if (Parser.isValidGender(toChange.getGender())) {
                    memberToChange.setGender(toChange.getGender());
                } else {
                    Ui.printEditGenderFailed();
                }
            }

            if (!toChange.getPhoneNumber().equals("")) {
                if (Parser.isValidPhone(toChange.getPhoneNumber())) {
                    memberToChange.setPhoneNumber(toChange.getPhoneNumber());
                } else {
                    Ui.printEditPhoneFailed();
                }
            }

            members.getMemberList().set(index - 1, memberToChange);
            Ui.printEditMessage(oldMember, memberToChange);
            File dukeMemberFile = new File("CCAMembers.csv");
            writeMemberFile(dukeMemberFile, members);

        } catch (AssertionError e) {
            System.out.println("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unfortunately, the index you entered is invalid.");
        }
    }
}
