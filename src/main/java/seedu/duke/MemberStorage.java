package seedu.duke;

import seedu.duke.attendance.AttendanceList;
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
    public static void SetupMemberFile(MemberList members) {
        File dukeMemberFile = new File("DukeMembers.csv");
        if (!dukeMemberFile.exists()) {
            try {
                dukeMemberFile.createNewFile();
                initializeMemberFile(dukeMemberFile);
                System.out.println("file create at " + dukeMemberFile.getCanonicalPath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("file loaded from " + dukeMemberFile.getCanonicalPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadDukeAttendanceFile(dukeMemberFile, members);
        }
    }

    /**
     * This method loads the duke member file and writes to the current member list. //only happens on start up
     *
     * @param dukeMemberFile
     * @param memberList
     */
    public static void loadDukeAttendanceFile(File dukeMemberFile, MemberList memberList) {
        //laze bro
    }

    /**
     * This method initializes the headers of duke attendance file.
     *
     * @param dukeMemberFile the attendance file
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
     * This method rewrites the entire duke attendance file.
     *
     * @param dukeMemberFile  the member file
     * @param memberList     the current attendance list
     */
    public static void writeMemberFile(File dukeMemberFile, MemberList memberList) {
        int memberListSize = memberList.getMemberSize();
        try (PrintWriter dukeMemberWriter = new PrintWriter(dukeMemberFile)) {
            dukeMemberWriter.write("name");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("student number");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("gender");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("phone number");
            dukeMemberWriter.write('\n');
            for (int i = 0; i < memberListSize; i++) {
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