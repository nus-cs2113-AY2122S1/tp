package seedu.duke.member;

import java.util.ArrayList;

public class MemberList {

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

    /**
     * Add member as request by user
     *
     * @param member member to be added
     */
    public void addMember(Member member) {
        memberList.add(member);
    }

    /**
     * Delete member as request by user.
     *
     * @param memberNumber member number given by user
     * @throws IndexOutOfBoundsException When an invalid member is selected to be deleted
     */
    public Member deleteMember(int memberNumber) throws IndexOutOfBoundsException {
        try {
            int index = memberNumber - 1;
            Member task = memberList.get(index);
            memberList.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

}
