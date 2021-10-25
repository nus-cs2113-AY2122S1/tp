package seedu.duke.items.mainlists;

import seedu.duke.items.characteristics.Member;

import java.util.ArrayList;

public class MemberRoster extends ArrayList<Member> {

    private static MemberRoster memberRoster = null;

    private MemberRoster() {
    }

    public static MemberRoster getInstance() {
        if (memberRoster == null) {
            memberRoster = new MemberRoster();
        }
        return memberRoster;
    }

    public void sortRoster() {
        memberRoster.sort(Member.NameComparator);
    }
}
