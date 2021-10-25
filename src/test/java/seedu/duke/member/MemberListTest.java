package seedu.duke.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.command.*;
import seedu.duke.member.exception.InvalidMemberException;

class MemberListTest {

    private MemberList fullMemberList;

    private Member teckHwee;
    private Member ianWang;
    private Member glenn;
    private Member izdiyad;
    private Member xingYuan;

    @BeforeEach
    public void setUp() throws Exception {

        teckHwee = new Member("Tan Teck Hwee", "A0123456A", 'F', 98765432);
        ianWang = new Member("Ian Wang", "A0234567B", 'M', 98441232);
        glenn = new Member("Glenn", "A0345678C", 'M', 91233344);
        izdiyad = new Member("Izdiyad", "A0456789D", 'M', 94376452);
        xingYuan = new Member("Xing Yuan", "A0567891E", 'M', 96987132);

        ArrayList<Member> memberList = new ArrayList<Member>();
        memberList.add(teckHwee);
        memberList.add(ianWang);
        memberList.add(glenn);
        memberList.add(izdiyad);
        memberList.add(xingYuan);
        fullMemberList = new MemberList(memberList);

    }

    @Test
    void deleteMember() {
        fullMemberList.deleteMember(5);
        fullMemberList.deleteMember(4);
        fullMemberList.deleteMember(3);
        fullMemberList.deleteMember(2);
        fullMemberList.deleteMember(1);
        assertTrue(fullMemberList.getMemberList().size() == 0);
    }

    @Test
    void getExistingMemberDetails() {
        assertEquals(fullMemberList.getMemberName(1), "Tan Teck Hwee");
        assertEquals(fullMemberList.getMemberStudentNumber(1), "A0123456A");
        assertEquals(fullMemberList.getMemberGender(1), 'F');
        assertEquals(fullMemberList.getMemberPhoneNumber(1), "98765432");
    }

    @Test
    void deleteOneMember() {
        final String string = "delete /m 1";
        int index = Parser.getIndex(string);
        new DeleteMember(fullMemberList, index);
        //Parser.deleteMember(fullMemberList, string);
        assertEquals(4, fullMemberList.getMemberList().size());
    }

    @Test
    void makeMemberEntry() {
        final String string = "add /m /n Lorem Ipsum /s A1231234B";
        Member newMember = Parser.getMemberDetails(string);
        new AddMember(fullMemberList, newMember);
        //Parser.makeMemberEntry(fullMemberList, string);

        assertEquals(fullMemberList.getMemberName(6), "Lorem Ipsum");
        assertEquals(fullMemberList.getMemberStudentNumber(6), "A1231234B");
    }

    @Test
    void editMember() {
        final String string = "edit /m 1 /n Ian Wang";
        int index = Parser.getIndex(string);
        Member memberDetail = Parser.getMemberDetails(string);
        new EditMember(fullMemberList, index, memberDetail);

        try {
            assertEquals(fullMemberList.getMember(1).getName(), "Ian Wang");
            assertEquals(fullMemberList.getMember(1).getStudentNumber(), "A0123456A");
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        }
    }
}