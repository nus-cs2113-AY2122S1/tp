package seedu.duke.common;


/**
 * Class containing hardcoded Strings.
 */
public class Messages {

    /**
     * Used in the printing of Welcome Message.
     */
    public static final String LOGO = " ____ ____ ____ ____ ____ ____ ____ \n"
            + "||E |||l |||d |||e |||r |||l |||y ||\n"
            + "||__|||__|||__|||__|||__|||__|||__||\n"
            + "|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|\n"
            + " ____ ____                          \n"
            + "||I |||n ||                         \n"
            + "||__|||__||                         \n"
            + "|/__\\|/__\\|                         \n"
            + " ____ ____ ____ ____                \n"
            + "||Y |||o |||u |||r ||               \n"
            + "||__|||__|||__|||__||               \n"
            + "|/__\\|/__\\|/__\\|/__\\|               \n"
            + " ____ ____ ____ ____                \n"
            + "||H |||o |||o |||d ||               \n"
            + "||__|||__|||__|||__||               \n"
            + "|/__\\|/__\\|/__\\|/__\\|               ";
    public static final String MESSAGE_WELCOME = "[*] Welcome admin. Type after the '>' to start.";

    /**
     * Used to prompt user to type in commands.
     */
    public static final String MESSAGE_PROMPT = "> ";

    /**
     * Used to respond to user when command is unrecognized.
     */
    public static final String MESSAGE_INVALID_COMMAND = "[*] Sorry user, I do not recognize this command";

    /**
     * Used when exiting the application when the bye command is issued.
     */
    public static final String MESSAGE_GOODBYE = "[*] Bye command has been recognized. Goodbye!";

    /**
     * Used when entering risk level message.
     */
    public static final String MESSAGE_ENTER_RISK_LEVEL = "[*] Enter risk level. (i.e. l for low, m for med, h for"
            + " high.)";

    /**
     * Used when selected risk level is invalid.
     */
    public static final String MESSAGE_INVALID_RISK_LEVEL = "[!] Risk level keyed in is invalid. Please select "
            + "from given options (l, m, h) only. Please redo addelderly command.";

    /**
     * Used when invalid set blood pressure.
     */
    public static final String MESSAGE_INVALID_SET_BLOOD_PRESSURE = "[!] Set blood "
            + "pressure format: setbloodpressure u/USERNAME s/SYSTOLIC_PRESSURE d/DIASTOLIC_PRESSURE";

    /**
     * Used when duplicate elderly name is found in database.
     */
    public static final String MESSAGE_DUPLICATE_ELDERLY = "[!] Elderly username already found in system! Addition "
            + "rejected!";

    /**
     * Used when selecting hospital.
     */
    public static final String MESSAGE_ENTER_HOSPITAL = "[*] Enter hospital number choice. (e.g. 1 for Changi General "
            + "Hospital etc.)";

    /**
     * Used when selected hospital index is invalid.
     */
    public static final String MESSAGE_INVALID_HOSPITAL_INDEX = "[!] Hospital index keyed in is invalid. Please select "
            + "from given options (1 - 6) only. Please redo addelderly command.";

    /**
     * Used when selected doctor index is invalid.
     */
    public static final String MESSAGE_INVALID_DOCTOR_INDEX = "[!] Doctor index keyed in is invalid. Please select "
            + "from given options (1 - 5) only. Please redo addelderly command.";

    /**
     * Used when inputting conditions.
     */
    public static final String MESSAGE_ENTER_CONDITIONS = "[*] Enter elderly condition below. Hit enter when done.";

    /**
     * Used when inputting notes on care.
     */
    public static final String MESSAGE_ENTER_NOTES_ON_CARE = "[*] Enter notes on care below. Hit enter when done.";

    /**
     * Used when selecting doctor.
     */
    public static final String MESSAGE_ENTER_DOCTOR = "[*] Enter doctor number choice. (e.g. 1)";

    /**
     * Used when elderly has been added.
     */
    public static final String MESSAGE_ADD_ELDERLY = "[*] Elderly, %s, has been added!%n";


    /**
     * Used when invalid addelderly is typed.
     */
    public static final String MESSAGE_ADD_ELDERLY_FORMAT =
            "[*] Add Elderly format: addelderly u/USERNAME n/NAME r/RISK_LEVEL";

    /**
     * Used when invalid elderly name is given.
     */
    public static final String MESSAGE_NO_SUCH_ELDERLY = "[!] Elderly name you have keyed in does not exist";

    /**
     * Used when appointment has been added.
     */
    public static final String MESSAGE_ADD_APPOINTMENT = "[*] Appointment has been added!";

    public static final String MESSAGE_INVALID_DATE = "[*] Date is invalid!";

    public static final String MESSAGE_INVALID_TIME = "[*] Time is invalid!";

    /**
     * Used when invalid addappt is typed.
     */
    public static final String MESSAGE_ADD_APPOINTMENT_FORMAT = "[*] Add Appointment format: addappt u/USERNAME "
            + "l/LOCATION d/DATE t/TIME p/[PURPOSE]";

    /**
     * Used when invalid viewappt is typed.
     */
    public static final String MESSAGE_VIEW_APPOINTMENT_FORMAT = "[*] View Appointment format: viewappt u/USERNAME";

    /**
     * Used when medicine has been added.
     */
    public static final String MESSAGE_ADD_MEDICINE = "[*] Medicine has been added!";

    /**
     * Used when invalid addmed is typed.
     */
    public static final String MESSAGE_ADD_MED_FORMAT = "[*] Add Medicine format: addmed u/USERNAME m/MEDICINE_NAME"
            + " f/FREQUENCY";

    /**
     * Used when invalid viewmed is typed.
     */
    public static final String MESSAGE_VIEW_MED_FORMAT = "[*] View Medicine format: viewmed u/USERNAME";

    /**
     * Used when invalid addnok is typed.
     */
    public static final String MESSAGE_ADD_NOK_FORMAT = "[*] Add Next-Of-Kin information format: addnok u/USERNAME "
            + "k/NOKNAME p/NOKPHONE e/NOKEMAIL a/NOKADDR r/NOKRSHIP";

    public static final String MESSAGE_DELETE_NOK_FORMAT = "[*] Delete Next-Of-Kin information format: deletenok "
            + "u/USERNAME n/NOKNAME";

    public static final String MESSAGE_DELETE_MEDICAL_HISTORY_FORMAT =
            "[*] Delete medical history information format: deletenok "
            + "u/USERNAME";

    public static final String MESSAGE_DELETE_MEDICINE_FORMAT = "[*] Delete medicine format: deletemed "
            + "u/USERNAME m/MEDICINE_NAME";

    public static final String MESSAGE_DELETE_APPOINTMENT_FORMAT = "[*] Delete appointment format: deleteappt "
            + "u/USERNAME d/DATE t/TIME";

    public static final String MESSAGE_DELETE_MEDHISTORY_FORMAT = "[*] Delete medical history format: "
            + "deletemedicalhistory u/USERNAME";

    /**
     * Used when invalid viewnok is typed.
     */
    public static final String MESSAGE_VIEW_NOK_FORMAT = "[*] View Next-Of-Kin format: viewnok u/USERNAME";

    /**
     * Used when invalid viewmedicalhistory is typed.
     */
    public static final String MESSAGE_VIEW_MEDICAL_HISTORY_FORMAT =
            "[*] View Medical History format: viewmedicalhistory u/USERNAME";

    /**
     * Used when invalid addmedicalhistory is typed.
     */
    public static final String MESSAGE_ADD_MEDICAL_HISTORY_FORMAT =
            "[*] Set Medical History format: addmedicalhistory u/USERNAME";

    /**
     * Used when invalid addmedicalhistory is typed.
     */
    public static final String MESSAGE_VIEW_BLOOD_PRESSURE_FORMAT =
            "[*] Set View Blood Pressure format: viewbloodpressure u/USERNAME";


    /**
     * Used when Next-Of_kin has been added.
     */
    public static final String MESSAGE_ADD_NOK = "[*] Next-of-Kin has been added!";


    /**
     * Used when record of elderly has been added.
     */
    public static final String MESSAGE_ADD_RECORD = "[*] Record of elderly has been added!";

    /**
     * Used when invalid addnok is typed.
     */
    public static final String MESSAGE_ADD_RECORD_FORMAT = "[*] Add Elderly Record Information format: addrec "
            + "u/USERNAME p/PHONENUMBER a/HOMEADDRESS";

    /**
     * Used when invalid addmedicalhistory.
     */
    public static final String MESSAGE_ADD_MEDHISTORY_FORMAT = "[*] Add Medical History format: addmedicalhistory "
            + "u/USERNAME";

    /**
     * Format of setbirthday.
     */
    public static final String MESSAGE_ADD_BIRTHDAY_FORMAT = "[*] Set Birthday format: setbirthday "
            + "u/USERNAME b/BIRTHDAY";

    /**
     * Format of setvaccination.
     */
    public static final String MESSAGE_ADD_VACCINATION_FORMAT = "[*] Set Vaccination format: setvaccination "
            + "u/USERNAME";

    /**
     * Format of setdiet.
     */
    public static final String MESSAGE_ADD_DIET_FORMAT = "[*] Set Diet format: setdiet "
            + "u/USERNAME";

    /**
     * Format of setbloodpressure.
     */
    public static final String MESSAGE_ADD_BP_FORMAT = "[*] Set Blood Pressure format: setbloodpressure "
            + "u/USERNAME s/SYSTOLIC_PRESSURE d/DIATOLIC_PRESSURE";


    /**
     * Used when invalid viewrecord is typed.
     */
    public static final String MESSAGE_VIEW_RECORD_FORMAT = "[*] View Elderly Record Information format: "
            + "viewrec u/USERNAME";

    /**
     * Used for format of viewmedicalhistory.
     */
    public static final String MESSAGE_VIEW_MEDHISTORY_FORMAT = "[*] View Medical History format: "
            + "viewmedicalhistory u/USERNAME";

    /**
     * Used for format of viewbirthday.
     */
    public static final String MESSAGE_VIEW_BIRTHDAY_FORMAT = "[*] View Birthday format: "
            + "viewbirthday u/USERNAME";

    /**
     * Used for format of viewvaccination.
     */
    public static final String MESSAGE_VIEW_VACCINATION_FORMAT = "[*] View Vaccination format: "
            + "viewvaccination u/USERNAME";

    /**
     * Used for format of viewdiet.
     */
    public static final String MESSAGE_VIEW_DIET_FORMAT = "[*] View Diet format: "
            + "viewdiet u/USERNAME";

    /**
     * Used for format of viewbloodpressure.
     */
    public static final String MESSAGE_VIEW_BP_FORMAT = "[*] View Blood Pressure format: "
            + "viewbloodpressure u/USERNAME";

    /**
     * Used when vaccination status of the elderly is set to be vaccinated.
     */
    public static final String MESSAGE_SET_VACCINATED = "[*] We have set %s to be vaccinated\r\n";

    /**
     * Used when birthday of the elderly has been set.
     */
    public static final String MESSAGE_SET_BIRTHDAY = "[*] Birthday of %s has been set to %s\r\n";

    /**
     * Used when blood pressure of the elderly has been updated.
     */
    public static final String MESSAGE_SET_BLOOD_PRESSURE = "[*] Blood pressure of %s has been updated to "
            + "%d %d!\n";

    /**
     * Used when blood pressure of the elderly has not been set.
     */
    public static final String MESSAGE_NO_BLOOD_PRESSURE = "[*] Blood pressure of %s has not yet been set!";

    /**
     * Used when dietary preference of the elderly has been updated.
     */
    public static final String MESSAGE_SET_DIET = "[*] Dietary preference of %s has been set to %s\r\n";

    /**
     * Used when dietary preference of the elderly has been updated.
     */
    public static final String MESSAGE_SET_MEDICAL_HISTORY = "[*] Medical history of %s has been updated\r\n";

    /**
     * Used when dietary preference of the elderly has been updated.
     */
    public static final String MESSAGE_DELETE_MEDICAL_HISTORY = "[*] Medical history of %s has been deleted\r\n";

    /**
     * Used for the exception message of the index out of range for the given diets plans.
     */
    public static final String MESSAGE_ERROR_IN_DIET_INDEX = "[!] Wrong index of diet is keyed in.\r\n";

    /**
     * Used for the exception message of the index out of range for the given diets plans.
     */
    public static final String MESSAGE_ERROR_IN_VIEW_DIET = "[!] Correct viewdiet input: viewdiet u/USER_NAME.\r\n";

    /**
     * Used for the exception message of the index out of range for the given diets plans.
     */
    public static final String MESSAGE_ERROR_IN_SET_DIET = "[!] Correct setdiet input: setdiet u/USER_NAME.\r\n";

    /**
     * Used when counting number of elderly for list.
     */
    public static final String MESSAGE_NUMBER_OF_ELDERLY = "[*] There are currently %d elderly registered in the "
            + "system\n";

    public static final String WRONG_SET_VACC_INPUT = "[!] Correct setvaccination input: setvaccinated u/USER_NAME";

    public static final String WRONG_VIEW_VACC_INPUT = "[!] Correct viewvaccination input: viewvaccination u/USER_NAME";

    public static final String WRONG_VIEW_BIRTHDAY_INPUT = "viewbirthday u/USER_NAME";

    public static final String WRONG_SET_BIRTHDAY_INPUT = "setbirthday "
            + "u/USER_NAME b/YYYY-MM-DD";

    public static final String WRONG_BIRTHDAY_FORMAT_INPUT = "[!] Invalid date keyed in"
            + " Kindly check the date!";


    /**
     * Used to list down all the supported dietary preferences for user's selection.
     */
    public static final String PROMPT_LIST_OF_DIETS =
            "[*] Select the possible diet of the elderly%n1. Halal%n2. Vegetarian%n3. Vegan%n"
                    + "4. Beef free%n5. Diabetes%n6. No restriction%n";

    /**
     * Used to prompt user to key in medical history.
     */
    public static final String PROMPT_KEY_IN_MEDICAL_HISTORY = "[*] Please key in your medical history%n";

    /**
     * Used to prompt user to confirm their deletion operation on medical history.
     */
    public static final String PROMPT_DELETE_MEDICAL_HISTORY =
            "[*] Are you sure you want to delete the medical history of %s? (Y/N)%n";

    /**
     * Used when invalid elderly name is given.
     */
    public static final String MESSAGE_ELDERLY_NOT_FOUND = "[*] Elderly named %s does not exist. Try again!%n";

    /**
     * Used when printing elderly object.
     */
    public static final String MESSAGE_DIETPREF = "[*] Diet Preference of %s: %s";
    public static final String MESSAGE_VACCINATED = "[*] Vaccination status of %s: %s";
    public static final String MESSAGE_BIRTHDAY = "[*] Birthday of %s: %s";
    public static final String MESSAGE_RECORDS = "[*] Here are %s's records:\n%s\n";
    public static final String MESSAGE_APPOINTMENTS = "[*] Here is the list of upcoming appointments:\n%s\n";
    public static final String MESSAGE_MEDICINES = "[*] This is the list of medicine(s):\n%s\n";
    public static final String MESSAGE_NOKS = "[*] These are NOK(s) attached to %s:\n%s\n";
    public static final String MESSAGE_MEDICALHIST = "[*] Medical History: %s";
    public static final String MESSAGE_BLOODPRESSURE = "[*] Blood pressure of %s is now (%d %d)";
    public static final String TO_STRING_MESSAGE_ABSTRACT_ELDERLY = "[*] Elderly Username: %s\n[*] Elderly Name: %s\n"
            + "%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s";
    public static final String TO_STRING_MESSAGE_LOW_RISK_ELDERLY = "[*] Risk Level : LOW\n%s";
    public static final String TO_STRING_MESSAGE_MEDIUM_RISK_ELDERLY = "[*] Risk Level : MEDIUM\n%s\n%s\n%s\n%s";
    public static final String TO_STRING_MESSAGE_HIGH_RISK_ELDERLY = "[*] Risk Level : HIGH\n%s\n%s\n%s\n%s\n%s";


    /**
     * Used in querying for information.
     */
    public static final String MESSAGE_QUERY_RESULTS_INTRO = "[*] This is the results for the following query: %s%n";

    /**
     * Used when invalid findbymed is typed.
     */
    public static final String MESSAGE_FIND_BY_MED_FORMAT = "[*] Find By Medicine format: findbymed m/MEDICINE_NAME";

    /**
     * Used when invalid findbydiet is typed.
     */
    public static final String MESSAGE_FIND_BY_DIET_FORMAT = "[*] Find By Diet format: findbydiet d/DIET";

    /**
     * Used when invalid findbyname is typed.
     */
    public static final String MESSAGE_FIND_BY_NAME_FORMAT = "[*] Find By Name format: findbyname n/NAME";

    /**
     * Used when invalid deletebyelderly is typed.
     */
    public static final String MESSAGE_DELETE_ELDERLY_FORMAT = "[*] Delete format: deleteelderly u/USERNAME";

    /**
     * Used when invalid store command is typed.
     */
    public static final String MESSAGE_STORE_FORMAT = "[*] Store format: store fp/FILE";

    /**
     * Used when invalid load command is typed.
     */
    public static final String MESSAGE_LOAD_FORMAT = "[*] Load format: load fp/FILE";

    /**
     * Used when printing help command messages.
     */
    public static final String MESSAGE_HELP_MENU = "[*] The following is a list of commands:\nMisc. Commands\n\n%s\n"
            + "Creation Commands\n\n%s\n"
            + "Reading Commands\n\n%s\n"
            + "Filter Commands\n\n%s\n"
            + "Delete Commands\n\n%s\n"
            + "File I/O Commands\n\n%s\n";
    public static final String MESSAGE_CREATION_FORMAT = "%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n";
    public static final String MESSAGE_READING_FORMAT = "%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n";
    public static final String MESSAGE_FILTER_FORMAT = "%s\n%s\n%s\n";
    public static final String MESSAGE_DELETION_FORMAT = "%s\n%s\n%s\n%s\n%s\n";
    public static final String MESSAGE_FILE_IO_FORMAT = "%s\n%s\n";
    public static final String MESSAGE_MISC_FORMAT = "%s\n%s\n%s\n";
    public static final String MESSAGE_BYE_FORMAT = "bye - Quits Program";
    public static final String MESSAGE_HELP_FORMAT = "help - Prints this help menu";
    public static final String MESSAGE_LIST_FORMAT = "list - List all elderly in the program";

    /**
     * Used when printing the closest match to user query.
     */
    public static final String MESSAGE_CLOSEST_SEARCH_RESULT = "[*] I am sorry, did you mean: %s\n";
    public static final String MESSAGE_INVALID_SEARCH_TERM = "[!] I cannot find %s\n";

    /**
     * Used when deleting elderly from list.
     */
    public static final String MESSAGE_DELETE_ELDERLY_SUCCESSFUL = "[*] %s has been deleted.\n";

    /**
     * Used when checking if file exists.
     */
    public static final String MESSAGE_FILE_EXISTS = "[*] %s exists. Loading from file!\n";
    public static final String MESSAGE_FILE_DOES_NOT_EXISTS = "[!] %s does not exists.\nTry again!\n";

    /**
     * Used when file format is wrong.
     */
    public static final String MESSAGE_FILE_WRONG_FORMAT = "[!] File is in the wrong format, could not load!";
    public static final String MESSAGE_LOAD_ELDERLY_ERROR = "[!] %s object has issue!\n";


    public static final String MESSAGE_DATA_STORE_SUCCESS = "[*] Data is stored successfully!";
}
