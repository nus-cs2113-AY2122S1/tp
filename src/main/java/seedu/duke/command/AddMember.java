package seedu.duke.command;
import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class AddMember {
    public AddMember(MemberList members, Member member) {
        members.addMember(member);
        Ui.printAddedMemberMessage(member);
        File dukeMemberFile = new File("dukeMembers.csv");
        writeMemberFile(dukeMemberFile, members);
    }
}

//NumberFormatException is thrown when /s 123123123123 is put in
