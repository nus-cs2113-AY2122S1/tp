//@@author Teckwhye

package seedu.duke.member;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;
import seedu.duke.member.exception.InvalidMemberException;

public class MemberList {

    /* Error message for duplicate names */
    private final String duplicateNameErrorMessage = "Duplicate name found. Please enter a different name";
    private final String duplicatePhoneNumberErrorMessage = "Duplicate phone number found. Please enter a different "
            + "phone number";
    private final String duplicateStudentNumberErrorMessage = "Duplicate student number found. Please enter a "
            + "different student number ";

    private final ArrayList<Member> memberList;

    public MemberList() {
        memberList = new ArrayList<Member>();
    }

    public MemberList(ArrayList<Member> memberList) {
        this.memberList = memberList;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public int getMemberListSize() { //added by xy
        return memberList.size();
    }

    public String getMemberName(int index) { //added by xy
        return memberList.get(index - 1).name;
    }

    public String getMemberStudentNumber(int index) { //added by xy
        return memberList.get(index - 1).studentNumber;
    }

    public String getMemberGender(int index) { //added by xy
        return memberList.get(index - 1).gender;
    }

    public String getMemberPhoneNumber(int index) { //added by xy
        return String.valueOf(memberList.get(index - 1).phoneNumber);
    }

    /**
     * Add member as request by user.
     *
     * @param member member to be added
     */
    public void addMember(Member member) {
        memberList.add(member);
    }

    /**
     * Get member by index.
     *
     * @param memberNumber member number according to member list
     * @return member request by memberNumber
     * @throws InvalidMemberException If member do not exist
     */
    public Member getMember(int memberNumber) throws InvalidMemberException {
        Member member = new Member();
        boolean isWithinMemberList = (memberNumber > 0) && (memberNumber <= memberList.size());
        if (isWithinMemberList) {
            int index = memberNumber - 1;
            member = memberList.get(index);

        } else {
            throw new InvalidMemberException("Member do not exist, please select the right member index.");
        }
        return member;
    }

    /**
     * Delete member as request by user by name.
     *
     * @param memberName member name given by user
     * @return member to be displayed as deleted
     * @throws InvalidMemberException When unable to find member with the same name to be deleted
     */
    public Member deleteMemberByName(String memberName) throws InvalidMemberException {
        memberName = memberName.toUpperCase(Locale.ROOT);
        Pattern findRegex = Pattern.compile("^" + memberName + "$");

        boolean foundNameMatch;
        boolean hasDeletedMember = false;

        Member removedMember = new Member();
        Member memberToBeRemoved = new Member();

        for (Member member : memberList) {
            foundNameMatch = findRegex.matcher(member.getName()).matches();
            if (hasDeletedMember) {
                member.setIndex(member.getIndex() - 1);
            }
            if (foundNameMatch) {
                memberToBeRemoved = member;
                removedMember = new Member(member);
                hasDeletedMember = true;
            }
        }
        MemberList membersThatMatchFind;
        if (!hasDeletedMember) {
            membersThatMatchFind = this.findMember(memberName);
            throw new InvalidMemberException("Unable to find a member that is called: " + memberName,
                    membersThatMatchFind);
        }
        memberList.remove(memberToBeRemoved);
        return removedMember;
    }

    /**
     * Delete member as request by user by index.
     *
     * @param memberNumber member number given by user
     * @return member to be displayed as deleted
     * @throws IndexOutOfBoundsException When an invalid member is selected to be deleted
     */
    public Member deleteMemberByIndex(int memberNumber) throws IndexOutOfBoundsException, InvalidMemberException {
        int index = memberNumber - 1;
        Member member = memberList.get(index);
        Member removedMember = new Member(member);
        for (int i = memberNumber; i < this.getMemberListSize(); i++) {
            Member memberToChangeIndex = memberList.get(i);
            memberToChangeIndex.setIndex(i);
        }
        memberList.remove(index);
        return removedMember;
    }

    /**
     * Find member as request by user.
     *
     * @param name member name given by user to search for
     * @return MemberList class with members that matches the search
     */
    public MemberList findMember(String name) {
        Pattern findRegex = Pattern.compile(".*" + name.toUpperCase(Locale.ROOT) + ".*");
        ArrayList<Member> membersThatMatchFind = new ArrayList<Member>();
        for (Member member : memberList) {
            boolean foundNameMatch = findRegex.matcher(member.getName()).matches();
            if (foundNameMatch) {
                membersThatMatchFind.add(member);
            }
        }
        return new MemberList(membersThatMatchFind);
    }

    /**
     * Checks for any duplicate names, student number and phone number.
     *
     * @param member Member to be added to MemberList.
     * @return true if there are no duplicates given are valid.
     */
    public boolean verifyNoDuplicates(Member member) throws InvalidMemberException {
        for (Member memberInList : memberList) {
            if (member.getName().equals(memberInList.getName())) {
                throw new InvalidMemberException(duplicateNameErrorMessage);
            }
            if (member.getPhoneNumber().equals(memberInList.getPhoneNumber())) {
                throw new InvalidMemberException(duplicatePhoneNumberErrorMessage);
            }
            if (member.getStudentNumber().equals(memberInList.getStudentNumber())) {
                throw new InvalidMemberException(duplicateStudentNumberErrorMessage);
            }
        }
        return true;
    }

}
//@@author
