package seedu.duke;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;

public class Parser {

    public static boolean hasListMemberKeyword(String arg) {
        return arg.trim().matches("^list /m");
    }

    public static boolean hasListTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("list /t");
    }

    public static boolean hasAddMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /m");
    }

    public static boolean hasAddTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /t");
    }

    public static boolean hasDeleteMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /m");
    }

    public static boolean hasDeleteTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /t");
    }
    public static boolean hasMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /m");
    }

    public static boolean hasTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /m");
    }

    public static boolean hasFindMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("find /m");
    }

    public static boolean hasFindTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("find /t");
    }


    /**
     * Returns the required value for keyword which is the first word keyed in by user.
     *
     * @param query user raw data input.
     * @return keyword value from Keyword enum class.
     */
    public static Keyword getKeywordStatus(String query) {
        Keyword keyword;
        if (hasMemberKeyword(query)) {
            keyword = Keyword.MEMBER_ENTRY;
        } else if (hasTrainingKeyword(query)) {
            keyword = Keyword.TRAINING_SCHEDULE_ENTRY;
        } else if (hasAddMemberKeyword(query)) {
            keyword = Keyword.ADD_MEMBER_KEYWORD;
        } else if (hasAddTrainingKeyword(query)) {
            keyword = Keyword.ADD_TRAINING_KEYWORD;
        } else if (hasListMemberKeyword(query)) {
            keyword = Keyword.LIST_MEMBER_KEYWORD;
        } else if (hasListTrainingKeyword(query)) {
            keyword = Keyword.LIST_TRAINING_KEYWORD;
        } else if (query.trim().equals("bye")) {
            keyword = Keyword.EXIT_KEYWORD;
        } else if (hasDeleteMemberKeyword(query)) {
            keyword = Keyword.DELETE_MEMBER_KEYWORD;
        } else if (hasDeleteTrainingKeyword(query)) {
            keyword = Keyword.DELETE_TRAINING_KEYWORD;
        } else if (hasFindKeyword(query)) {
            keyword = Keyword.FIND_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        return keyword;
    }

    /**
     * Returns the description of the task only, without the date or the keyword.
     *
     * @param query user raw data input.
     * @return description of task.
     */
    public static String getTrainingDescription(String query) {
        String[] words = query.trim().split("[\\s]+");
        String[] allButFirstWord = Arrays.copyOfRange(words, 1, words.length);
        StringBuilder sentenceAfterDeletion = new StringBuilder();
        for (String word : allButFirstWord) {
            if (word.contains("/")) {
                break;
            } else {
                sentenceAfterDeletion.append(word).append(" ");
            }
        }
        return sentenceAfterDeletion.toString();
    }

    /**
     * Creates Member class by input given by user
     *
     * @param query user raw data input.
     * @return Member according to user input.
     */
    public static Member getMemberDetails(String query) {
        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

        String name = "";
        String studentNumber = "";
        char gender = ' ';
        int phoneNumber = 0;

        int wordIndex = 1;
        while (matcher.find()) {
            switch (matcher.group()){
            case "/m":
                name = words[wordIndex].trim();
                break;
            case "/s":
                studentNumber = words[wordIndex].trim();
                break;
            case "/g":
                gender = words[wordIndex].trim().charAt(0);
                break;
            case "/p":
                phoneNumber = Integer.parseInt(words[wordIndex].trim());
                break;
            }
            wordIndex++;
        }

        return new Member(name, studentNumber,gender,phoneNumber);
    }

    /**
     * Creates Member class by input given by user
     *
     * @param query user raw data input.
     * @return Member according to user input.
     */
    public static Integer getMemberIndex(String query) {
        String regex = "(\\/[a-z])+";
        String[] words = query.trim().split(regex);
        int memberNumber = Integer.parseInt(words[1].trim());
        return memberNumber;
    }


    /**
     * Function creates a new member to be input in MemberList Class.
     *
     * @param members MemberList which contains list of members
     * @param query user input
     */
    public static void makeMemberEntry(MemberList members, String query) {
        Member member = getMemberDetails(query);
        members.addMember(member);
        System.out.println("Added a Member: " + member);
    }

    /**
     * Function creates a new Event task to be input in tasks.
     *
     * @param tasks ArrayList of tasks
     * @param query user input
     */
    public static void makeTrainingEntry(ArrayList<TrainingSchedule> trainings, String query) {
        trainings.add(new TrainingSchedule(Parser.getTrainingDescription(query)));
        System.out.println("Added a Training entry: " + Parser.getTrainingDescription(query));
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param tasks ArrayList of tasks
     * @param query user input
     */
    public static void findInTraining(ArrayList<TrainingSchedule> trainings, String query) {
        ArrayList<TrainingSchedule> matchingTrainings = new ArrayList<>();
        for (TrainingSchedule training : trainings) {
            if (training.getDescription().toLowerCase().contains(Parser.getTrainingDescription(query))) {
                matchingTrainings.add(training);
            }
        }
        Ui.printMatchingTrainingList(matchingTrainings, query);
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param tasks ArrayList of tasks
     * @param query user input
     */
    public static void findInMembers(ArrayList<Member> members, String query) {
        ArrayList<Member> matchingMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.getDescription().toLowerCase().contains(Parser.getTrainingDescription(query))) {
                matchingMembers.add(member);
            }
        }
        Ui.printMatchingTrainingList(matchingMembers, query);
    }

    /**
     * Function deletes a member from the MemberList class.
     *
     * @param members MemberList which contains list of members
     * @param query user input
     */
    public static void deleteMember(MemberList members, String query) {
        try {
            int memberNumber = getMemberIndex(query);
            Member member = members.deleteMember(memberNumber);
            System.out.println("The following member have been deleted\n"+member.toString());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("There is no such member number...");
        } catch (NumberFormatException e){
            System.out.println("Please input a proper number...");
        }
    }

    public static void wrongInputTypeMessage() {
        try {
            throw new InvalidInputsException.MissingKeyword(Ui.printInvalidMessage());
        } catch (InvalidInputsException.MissingKeyword exception) {
            exception.printStackTrace();
            System.out.println("Invalid keyword!!!");
        }
    }

    /**
     * Function waits for user input, or takes input from ./list.txt.
     */
    public static void waitForQuery() {
        String query = "";
        Storage.loadTask();
        Scanner userInput = new Scanner(System.in);
        while (!query.equals("bye")) {
            System.out.print("=>");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
                Storage.saveTask(query);
            }
            TaskList.addTask(query);
        }
    }
}

