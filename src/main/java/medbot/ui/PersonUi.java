package medbot.ui;

//@@author Kureans
public abstract class PersonUi {
    protected static final String END_LINE = System.lineSeparator();
    protected static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;
    private static final String PATIENT = "patient";

    /**
     * Returns a list of valid commands.
     *
     * @return String containing a list of valid commands
     */
    protected static String getCommandList() {
        return "Here is the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE
                + "add" + END_LINE
                + "delete" + END_LINE
                + "edit" + END_LINE
                + "view" + END_LINE
                + "list" + END_LINE
                + "find" + END_LINE
                + "hide" + END_LINE
                + "show" + END_LINE
                + "switch" + END_LINE
                + "get view" + END_LINE
                + "exit" + END_LINE + END_LINE
                + "To view more information about each command and their respective command formats, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes." + END_LINE
                + "For expected output examples, please refer to the User Guide." + END_LINE;
    }

    /**
     * Returns a message String with help about the list command.
     *
     * @param person String indicating the type of person
     * @return String with help about the list command
     */
    protected static String getListHelpMessage(String person) {
        return "View information of all non-hidden " + person + "s." + END_LINE
                + "To view only hidden " + person + "s, add the '-h' parameter." + END_LINE
                + "Format: list [-h]" + END_LINE;
    }

    /**
     * Returns a message String with help about the view command.
     *
     * @param person String indicating the type of person
     * @return String with help about the view command
     */
    protected static String getViewHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "View a " + person + "'s personal information." + END_LINE
                + "Format: view " + uppercasePerson + "_ID" + END_LINE;
    }

    /**
     * Returns a message String with help about the add command.
     *
     * @param person String indicating the type of person
     * @return String with help about the add command
     */
    protected static String getAddHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Adds a " + person + " to the " + person + " list." + END_LINE
                + "Format:" + END_LINE
                + "add i/" + uppercasePerson + "_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]"
                + END_LINE;
    }

    /**
     * Returns a message String with help about the edit command.
     *
     * @param person String indicating the type of person
     * @return String with help about the edit command
     */
    protected static String getEditHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Edit the personal and medical information of a " + person + " in the list." + END_LINE
                + "Format:" + END_LINE
                + "edit " + uppercasePerson + "_ID [i/" + uppercasePerson + "_IC] [n/NAME] [p/PHONE_NUMBER]"
                + " [e/EMAIL] [a/ADDRESS]" + END_LINE;
    }

    /**
     * Returns a message String with help about the find command.
     *
     * @param person String indicating the type of person
     * @return String with help about the find command
     */
    protected static String getFindHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Find " + person + "s from the list based on given attributes." + END_LINE
                + "Format:" + END_LINE
                + "find [i/" + uppercasePerson + "_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "    * The attributes do not have to be in full." + END_LINE
                + "    * At least one attribute must be present." + END_LINE;
    }

    /**
     * Returns a message String with help about the delete command.
     *
     * @param person String indicating the type of person
     * @return String with help about the delete command
     */
    protected static String getDeleteHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Deletes a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "delete " + uppercasePerson + "_ID" + END_LINE;
    }

    //@@author EricBryann
    /**
     * Returns a message String with help about the hide command.
     *
     * @param person String indicating the type of person
     * @return String with help about the hide command
     */
    protected static String getHideHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Hides a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "hide " + uppercasePerson + "_ID" + END_LINE;
    }

    /**
     * Returns a message String with help about the show command.
     *
     * @param person String indicating the type of person
     * @return String with help about the show command
     */
    protected static String getShowHelpMessage(String person) {
        String uppercasePerson = person.toUpperCase();
        return "Show a " + person + " from the list." + END_LINE
                + "Format:" + END_LINE
                + "show " + uppercasePerson + "_ID" + END_LINE;
    }

    /**
     * Returns a String containing all persons' information in a table.
     *
     * @param person String indicating the type of person
     * @param personListString String containing information of all persons.
     * @return String containing all Persons' information.
     */
    protected static String getAllPersonsString(String person, String personListString, boolean isHiddenPersonList) {
        String output = getTableHeader(person, isHiddenPersonList);
        output += personListString;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    /**
     * Returns a String containing the header of the person information table.
     *
     * @param person String indicating the type of person
     * @return String containing the header of the person information table.
     */
    protected static String getTableHeader(String person, boolean isHiddenPersonList) {
        String uppercasePerson = person.toUpperCase();
        String hiddenOrShownPerson = isHiddenPersonList ? "hidden " : "not-hidden ";

        String output = "Here is a list of all " + hiddenOrShownPerson + person + "s:" + END_LINE;
        output += "For full details of each " + person + ", please use the command \"view " + uppercasePerson
                + "_ID\"" + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        output += " |  ID  | IC Number |         Name         |"
                + " Phone No. |        Email         |       Address        | " + END_LINE;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    /**
     * Returns a String containing the header of the person information table for find command.
     *
     * @param person String indicating the type of person
     * @return String containing the header of the person information table for find command.
     */
    protected static String getTableMatchedHeader(String person) {
        String optionalPluralSuffix = (person.equals(PATIENT)) ? "s" : "";
        String output = "Here is a list of matched " + person + optionalPluralSuffix + ":" + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        output += " |  ID  | IC Number |         Name         |"
                + " Phone No. |        Email         |       Address        | " + END_LINE;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    /**
     * Returns a message String indicating that the person with the specified information has been added
     * to the system.
     *
     * @param person String indicating the type of person
     * @param personInfo String containing the information about the person
     * @return String indicating that the specified person has been added to the system
     */
    protected static String getAddPersonMessage(String person, String personInfo) {
        return "Added " + person + " with " + personInfo + END_LINE;
    }

    /**
     * Returns a message String indicating that the person with the specified ID has been deleted from the system.
     *
     * @param person String indicating the type of person
     * @param id ID of the person who was removed from the system
     * @return String indicating that the specified person has been deleted from the system
     */
    protected static String getDeletePersonMessage(String person, int id) {
        String uppercasePerson = capitalizeFirstLetter(person);

        return uppercasePerson + " with ID " + id + " deleted from system." + END_LINE;
    }

    /**
     * Returns a message String indicating that the person with the specified ID has had their information edited
     * to the specified information.
     *
     * @param person String indicating the type of person
     * @param id ID of the person whose information was edited
     * @param info String containing the new information of the person
     * @return String indicating that the specified person's information has been edited
     */
    protected static String getEditPersonMessage(String person, int id, String info) {
        return "The information of the " + person + " with ID " + id + " has been edited to:" + END_LINE + END_LINE
                + info + END_LINE;
    }

    /**
     * Returns a message String containing the information of the person.
     *
     * @param person String indicating the type of person
     * @param personInfo the info of the person to be printed.
     * @return String containing the information of the person
     */
    protected static String getViewPersonMessage(String person, String personInfo) {
        return "Here's the requested " + person + " information:" + END_LINE + END_LINE
                + personInfo + END_LINE;
    }

    /**
     * Returns a message String indicating that the person with the specified ID is now set to hidden.
     *
     * @param person String indicating the type of person
     * @param id ID of the person who was set to shown
     * @return String indicating that the person with the specified ID is now set to hidden
     */
    protected static String getHidePersonMessage(String person, int id) {
        return "The " + person + " with ID " + id + " is now hidden." + END_LINE;
    }

    /**
     * Returns a message String indicating that the person with the specified ID is now set to shown.
     *
     * @param person String indicating the type of person
     * @param id ID of the person who was set to shown
     * @return String indicating that the person with the specified ID is now shown
     */
    protected static String getShowPersonMessage(String person, int id) {
        return "The " + person + " with ID " + id + " is now not hidden." + END_LINE;
    }

    /**
     * Returns a message String indicating the current view.
     *
     * @param person String indicating the type of person
     * @return String indicating the current view.
     */
    protected static String getCurrentViewPersonMessage(String person) {
        return "You are currently in the " + capitalizeFirstLetter(person) + " Management view.";
    }

    private static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
