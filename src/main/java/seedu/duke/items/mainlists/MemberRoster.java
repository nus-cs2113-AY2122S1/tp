package seedu.duke.items.mainlists;

import seedu.duke.items.Event;
import seedu.duke.items.characteristics.Member;

import java.util.ArrayList;
import java.util.Collections;

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

    public static void sortRoster() {
        Collections.sort(memberRoster, Member.NameComparator);
    }
}
