package seedu.duke.command;

import static seedu.duke.storage.MemberStorage.writeMemberFile;

import java.io.File;

import seedu.duke.Ui;
import seedu.duke.command.exception.InvalidAddMemberException;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

/**
 * Adds a Member object to MemberList.
 */
public class AddMember {

    String invalidNameErrorMessage = "Please input a valid name.Name cannot contain numbers or be empty.";
    String invalidStudentNumberErrorMessage = "Invalid student number provided. Please give it in the following "
            + "format: \n A1234567X where it consist of capital letter 'A' at the start and 7 digits after and ends off "
            + "with an capital letter";
    String invalidGenderErrorMessage = "Invalid gender provided. Please enter M for male, F for female.";
    String invalidPhoneNumberErrorMessage = "Invalid phone number given. Please enter a 8 digit Singapore phone number";
    String duplicateNameErrorMessage = "Duplicate name found.Please enter a different name";
    String duplicatePhoneNumberErrorMessage = "Duplicate phone number found.Please enter a different phone number";
    String duplicateStudentNumberErrorMessage = "Duplicate student number found.Please enter a different student number ";

    String validStudentNumberRegex = "^[A]\\d{7}[A-Z]";
    String validGenderRegex = "^[M|F]";
    String validPhoneNumberRegex = "^[8|9]\\d{7}";

    String name;
    String studentNumber;
    String gender;
    String phoneNumber;

    /**
     * Constructor. Adds a Member to the MemberList and saves it to hard disk.
     *
     * @param members MemberList containing all members.
     * @param member  Member to be added to MemberList.
     */
    public AddMember(MemberList members, Member member) {
        try {
            boolean validMember = verifyMemberDetails(member) && verifyNoDuplicates(member, members);
            if (validMember) {
                int newMemberIndex = members.getMemberListSize() + 1;
                member.setIndex(newMemberIndex);
                members.addMember(member);
                Ui.printAddedMemberMessage(member);
                File dukeMemberFile = new File("CCAMembers.csv");
                writeMemberFile(dukeMemberFile, members);
            }
        } catch (InvalidAddMemberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks for any duplicate names,student number and phone number.
     *
     * @param member         Member to be added to MemberList.
     * @param members MemberList containing all members.
     * @return true if there are no duplicates given are valid.
     */
    private boolean verifyNoDuplicates(Member member, MemberList members) throws InvalidAddMemberException {
        int memberListSize = members.getMemberListSize();
        for (int i = 1 ; i <= memberListSize; i++) {
            if(member.getName().equals(members.getMemberName(i))){
                throw new InvalidAddMemberException(duplicateNameErrorMessage);
            }
            if(member.getPhoneNumber().equals(members.getMemberPhoneNumber(i))){
                throw  new InvalidAddMemberException(duplicatePhoneNumberErrorMessage);
            }
            if(member.getStudentNumber().equals(members.getMemberStudentNumber(i))){
                throw new InvalidAddMemberException(duplicateStudentNumberErrorMessage);
            }
        }
        return true;
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
        name = member.getName();
        studentNumber = member.getStudentNumber();
        gender = member.getGender();
        phoneNumber = member.getPhoneNumber();

        boolean validName = verifyMemberName(name);
        if (!validName) {
            throw new InvalidAddMemberException(invalidNameErrorMessage);
        }

        boolean validStudentNumber = studentNumber.matches(validStudentNumberRegex);
        if (!validStudentNumber) {
            throw new InvalidAddMemberException(invalidStudentNumberErrorMessage);
        }

        boolean validGender = gender.matches(validGenderRegex);
        if (!validGender) {
            throw new InvalidAddMemberException(invalidGenderErrorMessage);
        }

        boolean validPhoneNumber = phoneNumber.matches(validPhoneNumberRegex);
        if (!validPhoneNumber) {
            throw new InvalidAddMemberException(invalidPhoneNumberErrorMessage);
        }

        return true;
    }

    /**
     * Checks whether the string is empty or contain digits.
     *
     * @param name the member  name to be verified.
     * @return true if member name is valid.
     */
    public boolean verifyMemberName(String name) {
        boolean nameEmpty = (name == null || name.trim().isEmpty());
        boolean nameContainDigits = name.matches(".*\\d.*");
        boolean validName = !nameEmpty && !nameContainDigits;
        return validName;
    }
}
