package seedu.duke.member;

public class Member {

    protected String name;
    protected String studentNumber;

    /* Gender of member: M = Male, F = Female */
    protected char gender;

    /* Status of member. True if still in team, False if left the team */
    protected boolean isActive;

    /* True if present for training, False if late for training, member won't be added to attendance list if absent. */
    String presentOrLate;

    protected int phoneNumber;

    /**
     * Constructor for any type of member.
     *
     * @param name              Name of member
     * @param studentNumber     Student number of member
     * @param gender            Gender of student M/F
     * @param phoneNumber       Phone number of student
     */
    public Member(String name, String studentNumber, char gender, int phoneNumber) {
        setName(name);
        setStudentNumber(studentNumber);
        setAsActiveMember();
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setPresentOrLate(presentOrLate);
    }

    public Member() {
    }

    public Member(String name, String studentNumber, String gender, String phoneNumber) {
        setName(name);
        setStudentNumber(studentNumber);
        setAsActiveMember();
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setPresentOrLate(presentOrLate);

    }

    public Member(String name, String studentNumber, char gender, int phoneNumber, String presentOrLate) {
        setName(name);
        setStudentNumber(studentNumber);
        setAsActiveMember();
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setPresentOrLate(presentOrLate);
    }

    public Member(String name, String studentNumber) {
        setName(name);
        setStudentNumber(studentNumber);
        setAsActiveMember();
        setPresentOrLate(presentOrLate);
    }


    public Member(Member member) {
        setName(member.name);
        setStudentNumber(member.studentNumber);
        setAsActiveMember();
        setGender(member.gender);
        setPhoneNumber(member.phoneNumber);
        setPresentOrLate(presentOrLate);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = gender.charAt(0);
    }

    public boolean checkIfActive() {
        return isActive;
    }

    public void setAsActiveMember() { 
        this.isActive = true; 
    }

    public void setAsInActiveMember() {
        this.isActive = false;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Integer.parseInt(phoneNumber);
    }

    public void setPresentOrLate(String presentOrLate) {
        this.presentOrLate = presentOrLate; 
    }

    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getAttendance() {
        return presentOrLate; 
    }

    /**
     * Formats description of member to be displayed to user.
     *
     * @return Formatted string of a member
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Student Number: %s | Gender: %c | Phone Number: %d", this.name,
                this.studentNumber, this.gender, this.phoneNumber);
    }
  
}
