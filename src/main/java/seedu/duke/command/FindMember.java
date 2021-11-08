package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.command.exception.InvalidFindMemberException;
import seedu.duke.member.MemberList;

public class FindMember {


    /**
     * Constructor. Adds a Member to the MemberList and saves it to hard disk.
     *
     * @param name    Name to be searched for
     * @param members MemberList containing all members.
     */
    public FindMember(MemberList members, String name) {
        try {
            if (name.equals("")) {
                throw new InvalidFindMemberException("Cannot find an empty name.");
            }
            MemberList membersThatMatchFind = members.findMember(name);
            Ui.printMatchingMemberList(membersThatMatchFind, name);
        } catch (InvalidFindMemberException e) {
            Ui.printFindMemberErrorMessage(e.getMessage());
        }
    }

}
