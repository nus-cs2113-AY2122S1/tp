package medbot.ui;

//@@author Kureans
public abstract class PersonUi {
    protected static final String END_LINE = System.lineSeparator();
    protected static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;

    protected static String getCommandList() {
        return "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE
                + "add" + END_LINE
                + "list" + END_LINE
                + "view" + END_LINE
                + "edit" + END_LINE
                + "find" + END_LINE
                + "delete" + END_LINE
                + "switch" + END_LINE
                + "exit" + END_LINE
                + "hide" + END_LINE
                + "show" + END_LINE
                + "get view" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes." + END_LINE
                + "For expected output examples, please refer to the User Guide." + END_LINE;
    }

    protected static String getListHelpMessage(String person) {
        return "View information of all non-hidden " + person + "s." + END_LINE
                + "To view only hidden " + person + "s, add the '-h' parameter." + END_LINE
                + "Format: list [-h]" + END_LINE;
    }

    protected static String getViewHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "View a " + person + "'s personal information." + END_LINE
                + "Format: view " + uppercasePerson + "_ID" + END_LINE;
    }

    protected static String getAddHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Adds a " + person + " to the " + person + " list." + END_LINE
                + "Format:" + END_LINE
                + "add i/" + uppercasePerson + "_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]"
                + END_LINE;
    }

    protected static String getEditHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Edit the personal and medical information of a " + person + " in the list." + END_LINE
                + "Format:" + END_LINE
                + "edit " + uppercasePerson + "_ID [i/" + uppercasePerson + "_IC] [n/NAME] [p/PHONE_NUMBER]"
                + " [e/EMAIL] [a/ADDRESS]" + END_LINE;
    }

    protected static String getFindHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Find " + person + "s from the list based on given attributes." + END_LINE
                + "Format:" + END_LINE
                + "find [i/" + uppercasePerson + "_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "    * The attributes do not have to be in full." + END_LINE
                + "    * At least one attribute must be present." + END_LINE;
    }

    protected static String getDeleteHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Deletes a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "delete " + uppercasePerson + "_ID" + END_LINE;
    }

    //@@author EricBryann
    protected static String getHideHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Hides a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "hide " + uppercasePerson + "_ID" + END_LINE;
    }

    protected static String getShowHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Show a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "show " + uppercasePerson + "_ID" + END_LINE;
    }


    /**
     * Returns the profile of a person.
     *
     * @param personInfo the Info of the person to be printed.
     * @return the Person information
     */
    protected static String getPersonInfo(String person, String personInfo) {
        return "Here's the requested " + person + ":" + END_LINE + END_LINE
                + personInfo + END_LINE;
    }

    /**
     * Returns all persons' information in a list.
     *
     * @param personListString String containing information of all persons.
     * @return all Persons' information.
     */
    protected static String getAllPersonsString(String person, String personListString) {
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
    protected static String getTableHeader(String person) {
        String uppercasePerson = person.toUpperCase();

        String output = "Here is a list of all " + person + "s:" + END_LINE;
        output += "For full details of each " + person + ", please use the command \"view " + uppercasePerson
                + "_ID\"" + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        output += " |  ID  | IC Number |         Name         |"
                + " Phone No. |        Email         |       Address        | " + END_LINE;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    protected static String getAddPersonMessage(String person, String personInfo) {
        return "Added " + person + " with " + personInfo + END_LINE;
    }

    protected static String getDeletePersonMessage(String person, int id) {
        String uppercasePerson = capitalizeFirstLetter(person);

        return uppercasePerson + " with id " + id + " deleted from system." + END_LINE;
    }

    protected static String getEditPersonMessage(String person, int id, String info) {
        return "The information of " + person + " with ID " + id + " has been edited to:" + END_LINE + END_LINE
                + info + END_LINE;
    }

    protected static String getHidePersonMessage(String person, int id) {
        return "The " + person + " with ID: " + id + " is now hidden." + END_LINE;
    }

    /**
     * Returns a message String indicating that the person with the specified ID is now shown.
     *
     * @param person String indicating the type of person
     * @param id ID of the person who was set to shown
     * @return String indicating that the person with the specified ID is now shown
     */
    protected static String getShowPersonMessage(String person, int id) {
        return "The " + person + " with ID: " + id + " is now not hidden." + END_LINE;
    }

    /**
     * Returns a message String indicating the current view.
     *
     * @param person String indicating the type of person
     * @return String indicating the current view.
     */
    protected static String getCurrentViewPersonMessage(String person) {
        return "You are currently in the " + person.toUpperCase() + "'s View.";
    }

    private static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
