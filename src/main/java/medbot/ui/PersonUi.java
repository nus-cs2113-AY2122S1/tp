package medbot.ui;

public abstract class PersonUi {
    protected static final String END_LINE = System.lineSeparator();
    protected static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;

    protected static String getCommandList() {
        return "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "exit" + END_LINE + "archive" + END_LINE + "unarchive"
                + END_LINE + "get view" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes" + END_LINE;
    }

    protected static String getListHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        String capFistLetterPerson = capitalizeFirstLetter(person);
        return "View information of all current " + person + "s." + END_LINE
                + "Format: list" + END_LINE
                + "Expected Output for 2 " + person + "s: " + END_LINE
                + capFistLetterPerson + " ID: [" + uppercasePerson + "_ID_1] IC: [" + uppercasePerson + "_IC]"
                + "Name: [" + uppercasePerson + "_NAME] H/P: "
                + "[PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + END_LINE
                + capFistLetterPerson + " ID: [" + uppercasePerson
                + "_ID_2] IC: [" + uppercasePerson + "_IC]"
                + "Name: [" + uppercasePerson + "_NAME] H/P: "
                + "[PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + END_LINE;
    }

    protected static String getViewHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        return "View a " + person + "’s personal information." + END_LINE
                + "Format: view " + uppercasePerson + "_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "id: " + uppercasePerson + "_ID " + "name: NAME " + "phone number: PHONE_NUMBER "
                + "email: EMAIL " + "address: ADDRESS" + END_LINE;
    }

    protected static String getAddHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        return "Add a " + person + " to the " + person + "’s list." + END_LINE
                + "Format:" + END_LINE
                + "add i/" + uppercasePerson + "_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "Expected output:" + END_LINE
                + "Added " + person + " with " + person + " ID: " + uppercasePerson + "_ID" + END_LINE;
    }

    protected static String getEditHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        String capFistLetterPerson = capitalizeFirstLetter(person);
        return "Edit the personal and medical information of a " + person + " in the list." + END_LINE
                + "Format:" + END_LINE
                + "edit " + uppercasePerson + " ID [i/" + uppercasePerson + "_IC] [n/NAME] [p/PHONE_NUMBER]"
                + " [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "Expected output: " + END_LINE
                + "The information of " + person + " with ID " + uppercasePerson + "_ID has been edited to:" + END_LINE
                + capFistLetterPerson + " ID: [" + uppercasePerson + "_ID] IC: [" + uppercasePerson
                + "_IC] Name: [NAME] H/P: [PHONE_NUMBER] "
                + "Email: [EMAIL] Address: [ADDRESS] " + END_LINE;
    }

    public static String getFindHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        String capFistLetterPerson = capitalizeFirstLetter(person);
        return "Find " + person + "s from the list based on given attributes." + END_LINE
                + "Format:" + END_LINE
                + "find [i/" + uppercasePerson + "_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "    * The attributes do not have to be in full." + END_LINE
                + "    * At least one attribute must be present." + END_LINE
                + "Expected Output:" + END_LINE
                + capFistLetterPerson + " ID: " + uppercasePerson + "_ID IC: " + uppercasePerson + "_IC Name: NAME "
                + "H/P: PHONE_NUMBER Email: EMAIL Address: ADDRESS" + END_LINE;
    }

    public static String getDeleteHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        String capFistLetterPerson = capitalizeFirstLetter(person);
        return "Delete a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "delete " + uppercasePerson + "_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + capFistLetterPerson + " with id " + uppercasePerson + "_ID deleted from system." + END_LINE;
    }

    public static String getArchiveHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        String capFistLetterPerson = capitalizeFirstLetter(person);
        return "Archive a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "archive " + uppercasePerson + "_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + capFistLetterPerson + " with id " + uppercasePerson + "_ID is successfully archived." + END_LINE;
    }

    public static String getUnarchiveHelpMessage(String person) {
        String uppercasePerson = capitalize(person);
        String capFistLetterPerson = capitalizeFirstLetter(person);
        return "Unarchive a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "unarchive " + uppercasePerson + "_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + capFistLetterPerson + " with id " + uppercasePerson + "_ID is successfully unarchived." + END_LINE;
    }


    /**
     * Returns the profile of a person.
     *
     * @param personInfo the Info of the person to be printed.
     * @return the Person information
     */
    public static String getPersonInfo(String person, String personInfo) {
        return "Here's the requested " + person + ":" + END_LINE + END_LINE
                + personInfo + END_LINE;
    }

    /**
     * Returns all persons' information in a list.
     *
     * @param personListString String containing information of all persons.
     * @return all Persons' information.
     */

    public static String getAllPersonsString(String person, String personListString) {
        String output = getTableHeader(person);
        output += personListString;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    /**
     * Returns the header of the person information table.
     *
     * @return the header of the person information table.
     */
    public static String getTableHeader(String person) {
        String uppercasePerson = capitalize(person);

        String output = "Here is a list of all " + person + "s:" + END_LINE;
        output += "For full details of each " + person + ", please use the command \"view " + uppercasePerson
                + "_ID\"" + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        output += " |  ID  | IC Number |         Name         |"
                + " Phone No. |        Email         |       Address        | " + END_LINE;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    private static String capitalize(String word) {
        return word.toUpperCase();
    }

    private static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
