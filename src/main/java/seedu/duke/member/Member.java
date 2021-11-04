//@@author Teckwhye

package seedu.duke.member;

import java.util.Locale;
import seedu.duke.member.exception.InvalidMemberException;

public class Member {

    protected String name;
    /* Only allow 'A' at the start and 7 digits after and ends off with a capital letter */
    protected String studentNumber;

    /* Gender of member: M = Male, F = Female */
    protected String gender;

    /* Only allow Singapore 8 digit phone number */
    protected String phoneNumber;

    /* Index of member from the arraylist. */
    protected int index;

    /* Regex for validity check of member details */
    String validStudentNameRegex = "^[A-Z ]+$";
    String validStudentNumberRegex = "^[A]\\d{7}[A-Z]$";
    String validGenderRegex = "^[M|F]$";
    String validPhoneNumberRegex = "^[8|9]\\d{7}$";

    /* Error message to throw */
    String invalidNameErrorMessage = "Please input a valid name.Name cannot contain numbers or be empty.";
    String invalidStudentNumberErrorMessage = "Invalid student number provided. Please give it in the following "
            + "format: \n A1234567X where it consist of capital letter 'A' at the start and 7 digits after and ends "
            + "off with a capital letter";
    String invalidGenderErrorMessage = "Invalid gender provided. Please enter M for male, F for female.";
    String invalidPhoneNumberErrorMessage = "Invalid phone number given. Please enter a 8 digit Singapore phone number";

    /**
     * Constructor for member with validation checking or without according to needsValidation.
     *
     * @param name            Name of member
     * @param studentNumber   Student number of member
     * @param gender          Gender of student M/F
     * @param phoneNumber     Phone number of student
     * @param needsValidation set member details with validation if True is given
     */
    public Member(String name, String studentNumber, String gender, String phoneNumber, boolean needsValidation)
            throws InvalidMemberException {
        if (needsValidation) {
            setName(name);
            setStudentNumber(studentNumber);
            setGender(gender);
            setPhoneNumber(phoneNumber);
        } else {
            this.name = name;
            this.studentNumber = studentNumber;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
        }
    }

    // Constructor for empty member class.
    public Member() {
    }

    /**
     * Constructor for copying a member class.
     *
     * @param member member to be copied
     */
    public Member(Member member) throws InvalidMemberException {
        setName(member.name);
        setStudentNumber(member.studentNumber);
        setGender(member.gender);
        setPhoneNumber(member.phoneNumber);
        setIndex(member.index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    /**
     * Verify name is not empty and contain only letters and spaces and set member's name if valid.
     *
     * @param name name given by user input
     * @throws InvalidMemberException If name given is not valid
     */
    public void setName(String name) throws InvalidMemberException {
        name = name.toUpperCase(Locale.ROOT);
        boolean isEmptyName = (name.trim().isEmpty()) || name.equals("");
        boolean isOnlyAlphabetsAndSpace = name.matches(validStudentNameRegex);
        boolean isValidName = !isEmptyName && isOnlyAlphabetsAndSpace;
        if (!isValidName) {
            throw new InvalidMemberException(invalidNameErrorMessage);
        } else {
            this.name = name;
        }
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * Verify student number starts with A with 7 digits and ends with any letter and set member's student number if
     * valid.
     *
     * @param studentNumber student number given by user input
     * @throws InvalidMemberException If student number given is not valid
     */
    public void setStudentNumber(String studentNumber) throws InvalidMemberException {
        studentNumber = studentNumber.toUpperCase(Locale.ROOT);
        boolean isValidStudentNumber = studentNumber.matches(validStudentNumberRegex);
        if (!isValidStudentNumber) {
            throw new InvalidMemberException(invalidStudentNumberErrorMessage);
        } else {
            this.studentNumber = studentNumber;
        }
    }

    public String getGender() {
        return gender;
    }

    /**
     * Verify gender only contains M or F and set member's gender if valid.
     *
     * @param gender gender given by user input
     * @throws InvalidMemberException If name given is not valid
     */
    public void setGender(String gender) throws InvalidMemberException {
        gender = gender.toUpperCase(Locale.ROOT);
        boolean validGender = gender.matches(validGenderRegex);
        if (!validGender) {
            throw new InvalidMemberException(invalidGenderErrorMessage);
        } else {
            this.gender = gender;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Verify phone number is a Singapore 8-digit number and set member's phone number if valid.
     *
     * @param phoneNumber phone number given by user input
     * @throws InvalidMemberException If phone number given is not valid
     */
    public void setPhoneNumber(String phoneNumber) throws InvalidMemberException {
        boolean validPhoneNumber = phoneNumber.matches(validPhoneNumberRegex);
        if (!validPhoneNumber) {
            throw new InvalidMemberException(invalidPhoneNumberErrorMessage);
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    /**
     * Formats description of member to be displayed to user.
     *
     * @return Formatted string of a member
     */
    @Override
    public String toString() {
        return String.format("[%d] Name: %s | Student Number: %s | Gender: %s | Phone Number: %s", this.index,
                this.name, this.studentNumber, this.gender, this.phoneNumber);
    }

}
//@@author
