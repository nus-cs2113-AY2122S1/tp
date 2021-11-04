package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.duke.Ui;
import seedu.duke.command.exception.InvalidAddMemberException;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class MemberStorage {

    static String validPhoneNumberRegex = "^[8|9]\\d{7}";
    static String validStudentNumberRegex = "^[A]\\d{7}[A-Z]";
    static String validGenderRegex = "^[M|F|m|f]";

    static String invalidPhoneNumberErrorExcelMessage = "Invalid phone number found in CCA Members CSV. Please fix this before running the program again.";
    static String invalidGenderErrorExcelMessage = "Invalid gender found in CCA Members CSV.Please fix this before running the program again.";
    static String invalidNameErrorExcelMessage = "invalid name found in CCA Members CSV.Please fix this before running the program again.";
    static String invalidStudentNumberErrorExcelMessage = "Invalid student number found in CCA Members CSV.Please fix this before running the program again.";

    static String duplicateErrorExcelMessage = "Duplicates name,student number or phone number found in CCA Members CSV.Please fix this before running the program again.";


    /**
     * Sets up the duke members csv file. It will first try to find if the file exists in the current
     * directory. If the file exists, it will load the CCAMembers CSV file into the current member list.
     * If not it will create a new member csv file in the current directory.
     *
     * @param memberList the list of current members.
     */
    public static void setupMemberFile(MemberList memberList) {
        File memberFile = new File("CCAMembers.csv");
        if (!memberFile.exists()) {
            try {
                memberFile.createNewFile();
                initializeMemberFile(memberFile);
                System.out.println("Member file not detected. Creating.");
                assert memberFile != null : "duke member file should be created";
            } catch (IOException e) {
                System.out.println("I/O error has occurred");
            }
        } else {
            System.out.println("CCA Members file found & loaded");
            verifyMemberDetails(memberFile);
            loadMemberFile(memberFile, memberList);
        }
    }

    /**
     * verifies that member details from CSV file are valid
     *
     * @param memberFile CCAMembers CSV file to read data from.
     */
    private static void verifyMemberDetails(File memberFile) {
        verifyIndividualFields(memberFile);
        verifyDuplicateFields(memberFile);
    }

    /**
     * verifies that no duplicates of member name, phone number and student number exists.
     *
     * @param memberFile CCAMembers CSV file to read data from.
     */
    private static void verifyDuplicateFields(File memberFile) {
        try {
            verifyDuplicateNames(memberFile);
            verifyDuplicateStudentNumber(memberFile);
            verifyDuplicatePhoneNumber(memberFile);
        } catch (InvalidAddMemberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * verifies that no duplicates of student number exists.
     *
     * @param memberFile CCAMembers CSV file to read data from.
     */
    private static void verifyDuplicateStudentNumber(File memberFile) throws InvalidAddMemberException {
        String StudentNumber;
        List<String> pendingStudentNumber = new ArrayList<String>();
        try {
            Scanner memberScanner = new Scanner(memberFile);
            memberScanner.nextLine();
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                String[] memberDetails = fullMemberDetails.split("\\,", 4);
                StudentNumber = memberDetails[1];
                pendingStudentNumber.add(StudentNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyMembersFile();
        }
        checkDuplicates(pendingStudentNumber);
    }

    /**
     * verifies that no duplicates of student number exists.
     *
     * @param memberFile CCAMembers CSV file to read data from.
     */
    private static void verifyDuplicatePhoneNumber(File memberFile) throws InvalidAddMemberException {
        String PhoneNumber;
        List<String> pendingPhoneNumber = new ArrayList<String>();
        try {
            Scanner memberScanner = new Scanner(memberFile);
            memberScanner.nextLine();
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                String[] memberDetails = fullMemberDetails.split("\\,", 4);
                PhoneNumber = memberDetails[3];
                pendingPhoneNumber.add(PhoneNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyMembersFile();
        }
        checkDuplicates(pendingPhoneNumber);
    }

    /**
     * verifies that no duplicates of member name exists.
     *
     * @param memberFile CCAMembers CSV file to read data from.
     */
    private static void verifyDuplicateNames(File memberFile) throws InvalidAddMemberException {
        String name;
        List<String> pendingMemberNames = new ArrayList<String>();
        try {
            Scanner memberScanner = new Scanner(memberFile);
            memberScanner.nextLine();
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                String[] memberDetails = fullMemberDetails.split("\\,", 4);
                name = memberDetails[0];
                pendingMemberNames.add(name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyMembersFile();
        }
        checkDuplicates(pendingMemberNames);
    }

    /**
     * Checks for any duplicates in the list.
     *
     * @param pendingList current list of Strings to check.
     * @throws InvalidAddMemberException if there are any duplicates.
     */
    private static void checkDuplicates(List<String> pendingList) throws InvalidAddMemberException {
        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i + 1; j < pendingList.size(); j++) {
                if (pendingList.get(i).equals(pendingList.get(j))) {
                    throw new InvalidAddMemberException(duplicateErrorExcelMessage);
                }
            }
        }
    }


    /**
     * verifies that individual details from CSV file are valid
     *
     * @param memberFile CCAMembers CSV file to read data from.
     */
    private static void verifyIndividualFields(File memberFile) {
        String name;
        String studentNumber;
        String gender;
        String phoneNumber;
        try {
            Scanner memberScanner = new Scanner(memberFile);
            memberScanner.nextLine();
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                String[] memberDetails = fullMemberDetails.split("\\,", 4);
                name = memberDetails[0];
                verifyMemberName(name);
                studentNumber = memberDetails[1];
                verifyMemberStudentNumber(studentNumber);
                gender = memberDetails[2];
                verifyMemberGender(gender);
                phoneNumber = memberDetails[3];
                verifyMemberPhoneNumber(phoneNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyMembersFile();
        } catch (InvalidAddMemberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Checks if phone number is valid.
     *
     * @param phoneNumber the phone number to be checked.
     * @throws InvalidAddMemberException when phone number is invalid.
     */
    private static void verifyMemberPhoneNumber(String phoneNumber) throws InvalidAddMemberException {
        boolean validPhoneNumber = phoneNumber.matches(validPhoneNumberRegex);
        if (!validPhoneNumber) {
            throw new InvalidAddMemberException(invalidPhoneNumberErrorExcelMessage);
        }

    }

    /**
     * Checks if gender is valid.
     *
     * @param gender the gender to be checked.
     * @throws InvalidAddMemberException when gender is invalid.
     */
    private static void verifyMemberGender(String gender) throws InvalidAddMemberException {
        boolean validGender = gender.matches(validGenderRegex);
        if (!validGender) {
            throw new InvalidAddMemberException(invalidGenderErrorExcelMessage);
        }

    }

    /**
     * Checks if studentNumber is valid.
     *
     * @param studentNumber the student number to be checked.
     * @throws InvalidAddMemberException when studentNumber is invalid.
     */
    private static void verifyMemberStudentNumber(String studentNumber) throws InvalidAddMemberException {
        boolean validStudentNumber = studentNumber.matches(validStudentNumberRegex);
        if (!validStudentNumber) {
            throw new InvalidAddMemberException(invalidStudentNumberErrorExcelMessage);
        }
    }

    /**
     * Checks if member name is valid
     *
     * @param name the member name to be checked.
     * @throws InvalidAddMemberException when member name is invalid.
     */
    private static void verifyMemberName(String name) throws InvalidAddMemberException {
        boolean nameEmpty = (name == null || name.trim().isEmpty());
        boolean nameContainDigits = name.matches(".*\\d.*");
        boolean validName = !nameEmpty && !nameContainDigits;
        if (!validName) {
            throw new InvalidAddMemberException(invalidNameErrorExcelMessage);
        }
    }


    /**
     * Loads the duke member file and writes to the current member list. Only happens on start-up.
     *
     * @param memberFile CCAMembers CSV file to read data from.
     * @param memberList the list of current members.
     */
    public static void loadMemberFile(File memberFile, MemberList memberList) {
        String name;
        String studentNumber;
        String gender;
        String phoneNumber;
        try {
            Scanner memberScanner = new Scanner(memberFile);
            memberScanner.nextLine();
            int index = 1;
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                assert fullMemberDetails != null : "fullMemberDetails should not be empty";
                String[] memberDetails = fullMemberDetails.split("\\,", 4);
                name = memberDetails[0];
                studentNumber = memberDetails[1];
                gender = memberDetails[2];
                phoneNumber = memberDetails[3];
                Member member = new Member(name, studentNumber, gender, phoneNumber);
                member.setIndex(index);
                memberList.addMember(member);
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyMembersFile();
        }
    }

    /**
     * Initializes the headers of duke member file.
     *
     * @param memberFile the CCAMembers CSV file.
     */
    public static void initializeMemberFile(File memberFile) {
        try (PrintWriter memberWriter = new PrintWriter(memberFile)) {
            memberWriter.write("name");
            memberWriter.write(',');
            memberWriter.write("student number");
            memberWriter.write(',');
            memberWriter.write("gender");
            memberWriter.write(',');
            memberWriter.write("phone number");
            memberWriter.write('\n');
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

    /**
     * Rewrites the entire duke member file.
     *
     * @param memberFile the CCAMembers CSV file.
     * @param memberList the list of current members..
     */
    public static void writeMemberFile(File memberFile, MemberList memberList) {
        int memberListSize = memberList.getMemberListSize();
        try (PrintWriter memberWriter = new PrintWriter(memberFile)) {
            memberWriter.write("name");
            memberWriter.write(',');
            memberWriter.write("student number");
            memberWriter.write(',');
            memberWriter.write("gender");
            memberWriter.write(',');
            memberWriter.write("phone number");
            memberWriter.write('\n');
            for (int i = 1; i <= memberListSize; i++) {
                memberWriter.write(memberList.getMemberName(i));
                memberWriter.write(',');
                memberWriter.write(memberList.getMemberStudentNumber(i));
                memberWriter.write(',');
                memberWriter.write(memberList.getMemberGender(i));
                memberWriter.write(',');
                memberWriter.write(memberList.getMemberPhoneNumber(i));
                memberWriter.write('\n');
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

}
