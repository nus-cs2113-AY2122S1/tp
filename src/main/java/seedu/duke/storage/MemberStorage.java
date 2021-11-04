package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class MemberStorage {

    /**
     * This method sets up the duke members csv file. It firsts tries to find if the file exists in the current
     * directory. If the file exists, it will call the loadDukeMemberFile method. If not it will create a new duke
     * member csv file in the current directory.
     *
     * @param members the list of current members
     */
    public static void setupMemberFile(MemberList members) {
        File memberFile = new File("CCAMembers.csv");
        if (!memberFile.exists()) {
            try {
                memberFile.createNewFile();
                initializeMemberFile(memberFile);
                System.out.println("CCA Members file not detected. Creating.");
                assert memberFile != null : "duke member file should be created";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("CCA Members file found & loaded");
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadMemberFile(memberFile, members);
        }
    }

    /**
     * This method loads the duke member file and writes to the current member list. Only happens on start-up.
     *
     * @param memberFile CSV file to read data from.
     * @param memberList     MemberList to write to.
     */
    public static void loadMemberFile(File memberFile, MemberList memberList) {
        String name;
        String studentNumber;
        String gender;
        String phoneNumber;
        try {
            Scanner memberScanner = new Scanner(memberFile);
            memberScanner.nextLine(); //skips the first header row
            int index = 1;
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                assert fullMemberDetails != null : "fullMemberDetails should not be empty";
                //System.out.println(fullMemberDetails);
                String[] memberDetails = fullMemberDetails.split("\\,", 4);

                name = memberDetails[0]; //used this to prevent magic numbers
                studentNumber = memberDetails[1];
                gender = memberDetails[2];
                phoneNumber = memberDetails[3];
                Member member = new Member(name, studentNumber, gender, phoneNumber);
                member.setIndex(index);
                memberList.addMember(member);
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initializes the headers of duke member file.
     *
     * @param memberFile the member file
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
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * This method rewrites the entire duke member file.
     *
     * @param memberFile the member file
     * @param memberList     the current member list
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
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}