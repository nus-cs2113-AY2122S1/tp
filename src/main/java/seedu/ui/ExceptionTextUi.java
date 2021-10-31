package seedu.ui;

public class ExceptionTextUi {
    private static final String LINE = "____________________________________________________________\n";

    // Used for print messages after user inputs
    private static void printDoubleLineMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    // Used for system messages without user inputs
    private static void printBottomLineMessage(String message) {
        System.out.println(message + "\n" + LINE);
    }

    private static void printTopLineMessage(String message) {
        System.out.println(LINE + "\n" + message);
    }

    // Used to print a line after displayed data
    public static void printBottomLine() {
        System.out.println("\n" + LINE);
    }

    public static void invalidPersonalNameErrorMessage() {
        String message = "The name is not correctly formatted,\n"
                + "Rules for name :\n"
                + "    * Uppercase and lowercase letters\n"
                + "    * Spaces\n"
                + "    * No numbers or special characters\n"
                + "    * Cannot be \"null\"\n\n"
                + "Please enter your name again.";
        printDoubleLineMessage(message);
        System.out.print("Name: ");
    }

    public static void invalidPersonalGithubUsernameErrorMessage() {
        String message = "The github username is not correctly formatted,\n"
                + "Rules for Github username :\n"
                + "    * Only contain alphanumeric characters or hyphens\n"
                + "    * Only lowercase allowed\n"
                + "    * Maximum 39 characters allowed\n"
                + "    * Cannot have multiple consecutive hyphens\n"
                + "    * Cannot begin or end with a hyphen\n\n"
                + "Please enter your github username again\n"
                + "or press ENTER to skip.";
        printDoubleLineMessage(message);
        System.out.print("GitHub Username: ");
    }

    public static void invalidPersonalTelegramUsernameErrorMessage() {
        String message = "The telegram username is not correctly formatted,\n"
                + "Rules for Telegram username :\n"
                + "    * Uppercase and lowercase letters allowed\n"
                + "    * Numbers and underscore allowed\n"
                + "    * Length at-least 5 characters\n\n"
                + "Please enter your telegram username again\n"
                + "or press ENTER to skip.";
        printDoubleLineMessage(message);
        System.out.print("Telegram Handle: @");
    }

    public static void invalidPersonalTwitterUsernameErrorMessage() {
        String message = "The twitter username is not correctly formatted,\n"
                + "Rules for Twitter username :\n"
                + "    * Lowercase letters only\n"
                + "    * Numbers and underscore allowed\n"
                + "    * Maximum 15 characters allowed\n\n"
                + "Please enter your twitter username again\n"
                + "or press ENTER to skip.";
        printDoubleLineMessage(message);
        System.out.print("Twitter Username: ");
    }

    public static void invalidPersonalEmailErrorMessage() {
        String message = "The email id is not correctly formatted,\n"
                + "Rules for email id :\n"
                + "    * Lowercase letters only\n"
                + "    * Numbers, underscore, hyphen and dot allowed\n"
                + "    * @ cannot be at the start or end\n\n"
                + "Please enter your email again\n"
                + "or press ENTER to skip.";
        printDoubleLineMessage(message);
        System.out.print("Email Address: ");
    }

    public static void invalidPersonalLinkedinUsernameErrorMessage() {
        String message = "The linkedin username is not correctly formatted,\n"
                + "Rules for Linkedin username :\n"
                + "    * Lowercase letters only\n"
                + "    * Numbers, underscore and hyphen allowed\n"
                + "    * Length between 3 to 100 characters\n\n"
                + "Please enter your linkedin username again\n"
                + "or press ENTER to skip.";
        printDoubleLineMessage(message);
        System.out.print("LinkedIn Username: ");
    }

    // Error Messages
    public static void fileErrorMessage(String contactFilePath) {
        String message = "ConTech is unfortunately unable to access / create a\n" + " save file at " + contactFilePath
                + ".\n" + "Please relocate the file and try again.";
        printBottomLineMessage(message);
    }

    public static void invalidCommandMessage() {
        String message = "ConTech is unable to understand your request.\n" + "Please try again with a valid command.";
        printDoubleLineMessage(message);
    }

    public static void invalidFlagMessage() {
        String message = "There appears to be a flag that is not recognised.\n"
                + "Please try again with a valid flag.\n" + "  -n NAME\n" + "  -g GITHUB\n" + "  -l LINKEDIN\n"
                + "  -te TELEGRAM\n" + "  -tw TWITTER\n" + "  -e EMAIL";
        printDoubleLineMessage(message);
    }

    public static void invalidIndexMessage() {
        String message = "Please enter a valid contact index.";
        printDoubleLineMessage(message);
    }

    public static void missingDetailMessage() {
        String message = "There are missing details.\n" + "Please remove any flags with no details,\n"
                + "and ensure that your flags used are correct:\n" + "  -n NAME\n" + "  -g GITHUB\n" + "  -l LINKEDIN\n"
                + "  -te TELEGRAM\n" + "  -tw TWITTER\n" + "  -e EMAIL";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void missingArgEditMessage() {
        String message = "There seems to be missing parameters in your request.\n"
                + "Please enter command in this format:\n"
                + "      edit <INDEX> -n <NAME> -g <GITHUB> -e <EMAIL> -te <TELEGRAM> -l <LINKEDIN> -tw <TWITTER>\n"
                + "      example : edit 0 -n George -g procoder -te george123\n"
                + "NOTE : At least one flag and description required\n"
                + "       Order of parameters do not matter except for INDEX\n"
                + "       \"me\" is used as the INDEX for personal contact.";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void missingArgAddMessage() {
        String message = "There seems to be missing parameters in your request.\n"
                + "Please enter command in this format:\n"
                + "      add -n <NAME> -g <GITHUB> -e <EMAIL> -te <TELEGRAM> -l <LINKEDIN> -tw <TWITTER>\n"
                + "      example : add -n John Doe -g johndoecoder -e john@email.com -te johndoe\n"
                + "NOTE : At least name and description required\n"
                + "       Order of parameters do not matter";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void missingArgSearchMessage() {
        String message = "There seems to be missing parameters in your request.\n"
                + "Please enter command in this format:\n"
                + "      search <FLAG> <QUERY>\n"
                + "      example : search Ashraf\n"
                + "                search -g revflash\n"
                + "NOTE : Flag is optional and only one can be specified";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void missingIndexMessage() {
        String message = "There seems to be missing or invalid index in your request.\n"
                + "Please enter command in the following way:\n"
                + "      <COMMAND_WORD> <INDEX> <OPTIONAL_FLAGS>\n"
                + "Where <COMMAND_WORD> is rm or view";
        printDoubleLineMessage(message);
    }

    //@@author
    public static void invalidNumMessage() {
        String message = "That does not seem to be a number.\n" + "Please provide a number instead.";
        printDoubleLineMessage(message);
    }

    public static void invalidFormatMessage() {
        String message = "ConTech is unable to understand your request.\n"
                + "The request has not been formatted correctly. Please try again.";
        printDoubleLineMessage(message);
    }

    public static void missingNameMessage() {
        String message = "There are missing details.\n"
                + "Please specify a name when creating a contact with the flag -n";
        printDoubleLineMessage(message);
    }

    public static void numOutOfRangeMessage(int listSize) {
        String message;
        int maxIndex = listSize - 1;
        if (listSize == 0) {
            message = "There are no contacts stored in ConTech.";
        } else if (listSize == 1) {
            message = "The index you have input is out of range.\n"
                    + "You only have 1 contact stored.";
        } else {
            message = "The index you have input is out of range.\n"
                    + "Please input a index between 0 and " + maxIndex;
        }
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void numOutOfRangeEditMessage(int listSize) {
        String message;
        int maxIndex = listSize - 1;
        if (listSize == 0) {
            message = "There are no contacts stored in ConTech.\n"
                    + "Please input index \"me\" if you wish to edit your Personal Contact details.";
        } else if (listSize == 1) {
            message = "The index you have input is out of range.\n"
                    + "You only have 1 contact stored.\n"
                    + "Please input index \"me\" if you wish to edit your Personal Contact details.";
        } else {
            message = "The index you have input is out of range.\n"
                    + "Please input a number between 0 and " + maxIndex + " to edit saved contacts.\n"
                    + "Otherwise, input index \"me\" if you wish to edit your Personal Contact details.";
        }
        printDoubleLineMessage(message);
    }

    //@@author
    public static void corruptLineMessage(String line, int lineIndex, String contactFilePath) {
        System.out.println(contactFilePath + ":" + lineIndex + " - \"" + line + "\" is corrupted and not loaded.");
    }

    public static void corruptPersonalContactMessage() {
        String message = "Your personal contact file at data/me.txt is corrupted and\n"
                + "not loaded. We would need to set up your personal contact\n"
                + "again.\n\n"
                + "Enter anything to continue...";
        printDoubleLineMessage(message);
    }

    public static void invalidPersonalContactFileMessage() {
        String message = "It seems that your personal contact at data/me.txt has been"
                + "\naltered. We would need to set up your personal contact again.\n\n"
                + "Enter anything to continue...";
        printDoubleLineMessage(message);
    }

    public static void forbiddenInputCommaMessage(String newUserInput) {
        String message = "Due to the storage nature of ConTech, we will remove\n"
                + "commas (\",\"), and attempt to parse it as:\n"
                + newUserInput;
        printTopLineMessage(message);
    }

    public static void forbiddenDetailMessage() {
        String message = "As one of the details to be stored is \"null\",\n"
                + "ConTech is unable to process it";
        printDoubleLineMessage(message);
    }

    public static void duplicateDetailMessage() {
        String message = "There appears to be duplicated flags in your command.\n"
                + "Please kindly remove them to prevent any ambiguous action performed.";
        printDoubleLineMessage(message);
    }

    public static void invalidLoadedLineMessage(int lineIndex, String contactFilePath) {
        System.out.println(contactFilePath + ":" + lineIndex + " - There is an invalid field.");
    }

    public static void missingImportFileMessage() {
        String message = "ConTech is unable to find the import file.\n"
                + "Please kindly ensure that you have saved the file as data/import.txt.";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void invalidNameInput() {
        String message = "The name is not correctly formatted,\n"
                + "Rules for name :\n"
                + "    * Uppercase and lowercase letters\n"
                + "    * Spaces\n"
                + "    * No numbers or special characters\n"
                + "    * Cannot be \"null\"";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void invalidGithubUsernameInput() {
        String message = "The github username is not correctly formatted,\n"
                + "Rules for Github username :\n"
                + "    * Only contain alphanumeric characters or hyphens\n"
                + "    * Only lowercase allowed\n"
                + "    * Maximum 39 characters allowed\n"
                + "    * Cannot have multiple consecutive hyphens\n"
                + "    * Cannot begin or end with a hyphen";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void invalidTelegramUsernameInput() {
        String message = "The telegram username is not correctly formatted,\n"
                + "Rules for Telegram username :\n"
                + "    * Uppercase and lowercase letters allowed\n"
                + "    * Numbers and underscore allowed\n"
                + "    * Length at-least 5 characters";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void invalidTwitterUsernameInput() {
        String message = "The twitter username is not correctly formatted,\n"
                + "Rules for Twitter username :\n"
                + "    * Lowercase letters only\n"
                + "    * Numbers and underscore allowed\n"
                + "    * Maximum 15 characters allowed";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    //not full, need to update
    public static void invalidEmailInput() {
        String message = "The email id is not correctly formatted,\n"
                + "Rules for email id :\n"
                + "    * Lowercase letters only\n"
                + "    * Numbers, underscore, hyphen and dot allowed\n"
                + "    * @ cannot be at the start or end";
        printDoubleLineMessage(message);
    }

    //@@author mayankp291
    public static void invalidLinkedinUsernameInput() {
        String message = "The linkedin username is not correctly formatted,\n"
                + "Rules for Linkedin username :\n"
                + "    * Lowercase letters only\n"
                + "    * Numbers, underscore and hyphen allowed\n"
                + "    * Length between 3 to 100 characters";
        printDoubleLineMessage(message);
    }

    public static void invalidDeleteFlag() {
        String message = "Contact name cannot be deleted. \n"
                + "Please delete the entire contact instead.";
        printDoubleLineMessage(message);
    }
}
