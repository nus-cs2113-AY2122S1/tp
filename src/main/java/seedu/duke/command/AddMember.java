package seedu.duke.command;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import seedu.duke.Ui;
import seedu.duke.command.exception.InvalidAddMemberException;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

/**
 * Adds a Member object to MemberList.
 */
public class AddMember {

    String invalidNameErrorMessage = "Blank name provided. Please input a proper name.";
    String invalidStudentNumberErrorMessage = "Invalid student number provided. Please give it in the following "
            + "format: \n A1234567X where it consist of 1 letter at the start and 7 digits after and ends off "
            + "with an alphabet";
    String invalidGenderErrorMessage = "Invalid gender provided. Please enter M for male, F for female.";
    String invalidPhoneNumberErrorMessage = "Invalid phone number given. Please enter a 8 phone digit number";

    /**
     * Constructor. Adds a Member to the MemberList and saves it to hard disk.
     *
     * @param members MemberList containing all members.
     * @param member  Member to be added to MemberList.
     */
    public AddMember(MemberList members, Member member) {
        try {
            boolean validMember = verifyMemberDetails(member);
            if (validMember) {
                members.addMember(member);
                Ui.printAddedMemberMessage(member);
                File dukeMemberFile = new File("dukeMembers.csv");
                writeMemberFile(dukeMemberFile, members);
            }
        } catch (InvalidAddMemberException e) {
            System.out.println(e);
        }
    }

    /**
     * Verify member details. Ensure name, student number, gender and phone number is in the correct format else throw
     * exception
     *
     * @param member Member to be added to MemberList.
     * @return true if all parameters given are valid
     * @throws InvalidAddMemberException If there is an error with any of the parameters given.
     */
    public boolean verifyMemberDetails(Member member) throws InvalidAddMemberException {
        String name = member.getName();
        String studentNumber = member.getStudentNumber();
        String gender = member.getGender();
        String phoneNumber = member.getPhoneNumber();

        String validStudentNumberRegex = "^[A-Z]\\d{7}[A-Z]";
        String validGenderRegex = "^[M|F]";
        String validPhoneNumberRegex = "^\\d{8}";

        boolean validName = name != null && !name.trim().isEmpty();
        boolean validStudentNumber = studentNumber.matches(validStudentNumberRegex);
        boolean validGender = gender.matches(validGenderRegex);
        boolean validPhoneNumber = phoneNumber.matches(validPhoneNumberRegex);

        if (!validName) {
            throw new InvalidAddMemberException(invalidNameErrorMessage);
        }
        if (!validStudentNumber) {
            throw new InvalidAddMemberException(invalidStudentNumberErrorMessage);
        }
        if (!validGender) {
            throw new InvalidAddMemberException(invalidGenderErrorMessage);
        }
        if (!validPhoneNumber) {
            throw new InvalidAddMemberException(invalidPhoneNumberErrorMessage);
        }

        return true;
    }
}
