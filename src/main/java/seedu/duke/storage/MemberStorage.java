package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import seedu.duke.Ui;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class MemberStorage {

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
                System.out.println("CCA Members file not detected. Creating.");
                assert memberFile != null : "duke member file should be created";
            } catch (IOException e) {
                System.out.println("I/O error has occurred");
            }
        } else {
            System.out.println("CCA Members file found & loaded");
            loadMemberFile(memberFile, memberList);
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
