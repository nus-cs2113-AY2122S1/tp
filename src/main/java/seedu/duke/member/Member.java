package seedu.duke.member;

import java.util.Locale;

public class Member {

    protected String name;
    protected String studentNumber;

    /* Gender of member: M = Male, F = Female */
    protected String gender;

    protected String phoneNumber;

    /* Index of member from the arraylist. */
    protected int index;

    /**
     * Constructor for any type of member.
     *
     * @param name          Name of member
     * @param studentNumber Student number of member
     * @param gender        Gender of student M/F
     * @param phoneNumber   Phone number of student
     */
    public Member(String name, String studentNumber, String gender, String phoneNumber) {
        setName(name);
        setStudentNumber(studentNumber);
        setGender(gender);
        setPhoneNumber(phoneNumber);
    }

    public Member() {
    }

    public Member(String name, String studentNumber, String gender, String phoneNumber, String presentOrAbsent) {
        setName(name);
        setStudentNumber(studentNumber);
        setGender(gender);
        setPhoneNumber(phoneNumber);
    }

    public Member(String name, String studentNumber) {
        setName(name);
        setStudentNumber(studentNumber);
    }


    public Member(Member member) {
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

    public void setName(String name) {
        this.name = name.toUpperCase(Locale.ROOT);
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber.toUpperCase(Locale.ROOT);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.toUpperCase(Locale.ROOT);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
