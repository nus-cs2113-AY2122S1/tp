package seedu.duke;

import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MemberStorage {
    /**
     * This method sets up the duke members csv file.
     * It firsts tries to find if the file exists in the current directory.
     * If the file exists, it will call the loadDukeMemberFile method.
     * If not it will create a new duke member csv file in the current directory.
     *
     * @param members the list of current members
     */
    public static void setupMemberFile(MemberList members) {
        File dukeMemberFile = new File("DukeMembers.csv");
        if (!dukeMemberFile.exists()) {
            try {
                dukeMemberFile.createNewFile();
                initializeMemberFile(dukeMemberFile);
                System.out.println("file created");
                assert dukeMemberFile != null : "duke member file should be created";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("file loaded" + dukeMemberFile.getCanonicalPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadDukeMemberFile(dukeMemberFile, members);
        }
    }

    /**
     * This method loads the duke member file and writes to the current member list. Only happens on start-up.
     *
     * @param dukeMemberFile CSV file to read data from.
     * @param memberList MemberList to write to.
     */
    public static void loadDukeMemberFile(File dukeMemberFile, MemberList memberList) {
        String name;
        String studentNumber;
        String gender;
        String phoneNumber;
        try  {
            Scanner dukeMemberScanner = new Scanner(dukeMemberFile);
            dukeMemberScanner.nextLine(); //skips the first header row
            while (dukeMemberScanner.hasNextLine()) {
                String fullMemberDetails = dukeMemberScanner.nextLine();
                assert fullMemberDetails != null : "fullMemberDetails should not be empty";
                System.out.println(fullMemberDetails);
                String[] memberDetails = fullMemberDetails.split("\\,", 4);

                name = memberDetails[0]; //used this to prevent magic numbers
                studentNumber = memberDetails[1];
                gender =  memberDetails[2];
                phoneNumber = memberDetails[3];
                Member member = new Member(name,studentNumber,gender,phoneNumber);
                memberList.addMember(member);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initializes the headers of duke member file.
     *
     * @param dukeMemberFile the member file
     */
    public static void initializeMemberFile(File dukeMemberFile) {
        try (PrintWriter dukeMemberWriter = new PrintWriter(dukeMemberFile)) {
            dukeMemberWriter.write("name");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("student number");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("gender");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("phone number");
            dukeMemberWriter.write('\n');
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * This method rewrites the entire duke member file.
     *
     * @param dukeMemberFile  the member file
     * @param memberList     the current member list
     */
    public static void writeMemberFile(File dukeMemberFile, MemberList memberList) {
        int memberListSize = memberList.getMemberListSize();
        try (PrintWriter dukeMemberWriter = new PrintWriter(dukeMemberFile)) {
            dukeMemberWriter.write("name");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("student number");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("gender");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("phone number");
            dukeMemberWriter.write('\n');
            for (int i = 1; i <= memberListSize; i++) {
                dukeMemberWriter.write(memberList.getMemberName(i));
                dukeMemberWriter.write(',');
                dukeMemberWriter.write(memberList.getMemberStudentNumber(i));
                dukeMemberWriter.write(',');
                dukeMemberWriter.write(memberList.getMemberGender(i));
                dukeMemberWriter.write(',');
                dukeMemberWriter.write(memberList.getMemberPhoneNumber(i));
                dukeMemberWriter.write('\n');
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}