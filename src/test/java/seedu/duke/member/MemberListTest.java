package seedu.duke.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Keyword;
import seedu.duke.Parser;
import seedu.duke.command.AddMember;
import seedu.duke.command.DeleteMember;
import seedu.duke.command.EditMember;
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

        teckHwee = new Member("TAN TECK HWEE", "A0123456A", "F", "98765432", true);
        teckHwee.setIndex(1);
        ianWang = new Member("IAN WANG", "A0234567B", "M", "98441232", true);
        ianWang.setIndex(2);
        glenn = new Member("GLENN", "A0345678C", "M", "91233344", true);
        glenn.setIndex(3);
        izdiyad = new Member("IZDIYAD", "A0456789D", "M", "94376452", true);
        izdiyad.setIndex(4);
        xingYuan = new Member("XING YUAN", "A0567891E", "M", "96987132", true);
        xingYuan.setIndex(5);

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
        try {
            fullMemberList.deleteMemberByIndex(5);
            fullMemberList.deleteMemberByIndex(4);
            fullMemberList.deleteMemberByIndex(3);
            fullMemberList.deleteMemberByIndex(2);
            fullMemberList.deleteMemberByName("TAN TECK HWEE");
            assertTrue(fullMemberList.getMemberList().size() == 0);
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getExistingMemberDetails() {
        assertEquals(fullMemberList.getMemberName(1), "TAN TECK HWEE");
        assertEquals(fullMemberList.getMemberStudentNumber(1), "A0123456A");
        assertEquals(fullMemberList.getMemberGender(1), "F");
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
        final String string = "add /m /n Lorem Ipsum /s A1231234B /g M /p 91118888";
        Member newMember = Parser.getMemberDetails(string, Keyword.ADD_MEMBER_KEYWORD);
        new AddMember(fullMemberList, newMember);
        //Parser.makeMemberEntry(fullMemberList, string);

        assertEquals(fullMemberList.getMemberName(6), "LOREM IPSUM");
        assertEquals(fullMemberList.getMemberStudentNumber(6), "A1231234B");
        assertEquals(fullMemberList.getMemberGender(6), "M");
        assertEquals(fullMemberList.getMemberPhoneNumber(6), "91118888");
    }

    @Test
    void editMember() {
        final String string = "edit /m 1 /n Ian Hwang";
        int index = Parser.getIndex(string);
        Member memberDetail = Parser.getMemberDetails(string, Keyword.EDIT_MEMBER_KEYWORD);
        new EditMember(fullMemberList, index, memberDetail);

        try {
            assertEquals(fullMemberList.getMember(1).getName(), "IAN HWANG");
            assertEquals(fullMemberList.getMember(1).getStudentNumber(), "A0123456A");
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        }
    }
}