package seedu.duke.items.mainlists;

import seedu.duke.items.Event;
import seedu.duke.items.characteristics.Member;

import java.util.ArrayList;

public class MemberRoster extends ArrayList<Member> {

    private static ArrayList<Member> memberRoster;
    private static MemberRoster theOne = null;

    private MemberRoster() {
        memberRoster = new ArrayList<>();
    }

    public static MemberRoster getInstance() {
        if (theOne == null) {
            theOne = new MemberRoster();
        }
        return theOne;
    }
}
