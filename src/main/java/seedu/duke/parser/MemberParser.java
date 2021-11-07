package seedu.duke.parser;

import seedu.duke.exceptions.DukeException;

public class MemberParser {

    private static final String INTER_MEMBER_DELIMITER = " +";
    private static final String NAME_SPACER = "_";

    /**
     * Parses user input involving the description of member names. For example,
     * "John_Doe Jane_Doe" will return a String[] containing {"John Doe", "Jane Doe"}
     *
     * @param userInput User input containing the names to parse
     * @return A String[] of names that have been parsed
     */
    public static String[] parseMemberCommand(String userInput) {
        try {
            verifyMemberCommand(userInput);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String[] memberNames = userInput.split(INTER_MEMBER_DELIMITER);
        for (int i = 0; i < memberNames.length; i++) {
            memberNames[i] = memberNames[i].replaceAll(NAME_SPACER, " ").toUpperCase();
        }
        return memberNames;
    }

    private static void verifyMemberCommand(String userInput) throws DukeException {
        if (userInput.isBlank()) {
            throw new DukeException("Please give me at least a name! E.g. JOHN_DOE ");
        }
    }
}
