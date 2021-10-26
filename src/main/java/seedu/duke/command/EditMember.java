package seedu.duke.command;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class EditMember {

    public EditMember(MemberList members, int index, Member newMember) {
        try {
            assert index >= 1;

            Member memberToChange = members.getMemberList().get(index - 1);

            Member oldMember = new Member();
            oldMember.setName(memberToChange.getName());
            oldMember.setStudentNumber(memberToChange.getStudentNumber());
            oldMember.setPhoneNumber(memberToChange.getPhoneNumber());
            oldMember.setGender(memberToChange.getGender());


            if (!newMember.getName().equals("")) {
                memberToChange.setName(newMember.getName());
            }

            if (!newMember.getStudentNumber().equals("")) {
                memberToChange.setStudentNumber(newMember.getStudentNumber());
            }

            if (!String.valueOf(newMember.getGender()).equals("")) {
                memberToChange.setGender(newMember.getGender());
            }

            if (!String.valueOf(newMember.getPhoneNumber()).equals("")) {
                memberToChange.setPhoneNumber(newMember.getPhoneNumber());
            }

            members.getMemberList().set(index - 1, memberToChange);
            Ui.printEditMessage(oldMember, memberToChange);
            //TODO: Print and Save
            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);

        } catch (AssertionError e) {
            System.out.println("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unfortunately, the index you entered is invaid.");
        }
    }
}

    /* REMOVE HERE
    /**
     * Edit member by input given by user.
     *
     * @param query user raw data input.
     * @return Edited member according to user input.
     */
    /* REMOVE HERE
    public static ArrayList<Member> editMemberDetails(MemberList members, String query) throws InvalidMemberException,
            NumberFormatException {
        try {
            String regex = "(\\/[a-z])+";

            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(query);

            String[] words = query.trim().split(regex);

            Member editedMember = new Member();
            Member oldMember = new Member();
            String name = "";
            String studentNumber = "";
            char gender = ' ';
            int phoneNumber = 0;
            int memberNumber = 0;

            int wordIndex = 1;
            while (matcher.find()) {
                switch (matcher.group()) {
                case "/m":
                    memberNumber = Integer.parseInt(words[wordIndex].trim());
                    oldMember = new Member(members.getMember(memberNumber));
                    editedMember = members.getMember(memberNumber);
                    break;
                case "/n":
                    name = words[wordIndex].trim();
                    assert name != "" : "Name should not be empty";
                    editedMember.setName(name);
                    break;
                case "/s":
                    studentNumber = words[wordIndex].trim();
                    assert studentNumber != "" : "Student Number should not be empty";
                    editedMember.setStudentNumber(studentNumber);
                    break;
                case "/g":
                    gender = words[wordIndex].trim().charAt(0);
                    assert gender != ' ' : "Gender should not be empty. Put M for male, F for female";
                    editedMember.setGender(gender);
                    break;
                case "/p":
                    phoneNumber = Integer.parseInt(words[wordIndex].trim());
                    assert phoneNumber != 0 : "Phone Number should not be empty";
                    editedMember.setPhoneNumber(phoneNumber);
                    break;
                default:
                    break;
                }
                wordIndex++;
            }
            ArrayList<Member> oldMemberAndEditedMember = new ArrayList<Member>();
            oldMemberAndEditedMember.add(oldMember);
            oldMemberAndEditedMember.add(editedMember);

            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);

            return oldMemberAndEditedMember;
        } catch (InvalidMemberException e) {
            throw new InvalidMemberException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
    REMOVE HERE*/

    //TODO: Refactor
    /* REMOVE HERE
    /**
     * Function edits an existing member and shows the change to user.
     *
     * @param members MemberList which contains list of members
     * @param query   user input
     */
    /*
    public static void editMember(MemberList members, String query) {
        try {
            ArrayList<Member> oldMemberAndNewMember = editMemberDetails(members, query);
            Member oldMember = oldMemberAndNewMember.get(0);
            Member newMember = oldMemberAndNewMember.get(1);
            Ui.printEditMessage(oldMember, newMember);
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid number for member index.");
        }
    }
    REMOVE HERE
     */
