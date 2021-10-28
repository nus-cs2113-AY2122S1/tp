package seedu.duke.member.exception;

import seedu.duke.member.MemberList;

public class InvalidMemberException extends Exception {

    private MemberList members;

    public InvalidMemberException(String message) {
        super(message);
    }

    public InvalidMemberException(String message, MemberList members) {
        super(message);
        setMembers(members);
    }

    public MemberList getMembers() {
        return members;
    }

    public void setMembers(MemberList members) {
        this.members = members;
    }
}
