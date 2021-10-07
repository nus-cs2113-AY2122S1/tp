package seedu.duke.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberListTest {

    private MemberList fullMemberList;

    private Member teckHwee;
    private Member ianWang;
    private Member glen;
    private Member izdiyad;
    private Member xingYuan;

    @BeforeEach
    public void setUp() throws Exception {

        teckHwee = new Member("Tan Teck Hwee", "A0123456A", 'F', 98765432);
        ianWang = new Member("Ian Wang", "A0234567B", 'M', 98441232);
        glen = new Member("Glenn", "A0345678C", 'M', 91233344);
        izdiyad = new Member("Izdiyad", "A0456789D", 'M', 94376452);
        xingYuan = new Member("Xing Yuan", "A0567891E", 'M', 96987132);

        ArrayList<Member>  memberList = new ArrayList<Member>();
        memberList.add(teckHwee);
        memberList.add(ianWang);
        memberList.add(glen);
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
}