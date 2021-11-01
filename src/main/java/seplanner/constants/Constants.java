package seplanner.constants;

public class Constants {
    // Ui
    public static final String INDEX_WRAP_FRONT = "[";
    public static final String INDEX_WRAP_BACK = "]";
    public static final String MODULE_MAPPING_SEPARATOR = " - ";
    public static final String MODULE_NAME_SEPARATOR = " : ";
    public static final String LINE_SEPARATOR = "============================="
            + "====================================================";
    public static final String INDENTATION = "      ";
    public static final int MODULE_CODE_LENGTH = 8;
    public static final int INDEX_LENGTH = 3;
    public static final int UNIVERSITY_NAME_LENGTH = 50;
    public static int MODUlE_NAME_LENGTH = 40;

    // Commands
    public static final String COMMAND_REMOVE = "remove";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_SEARCHMAP = "searchmap";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_FIND = "find";

    // Flags
    public static final String FLAG_UNIVERSITY = "/uni";
    public static final String FLAG_MODULE = "/mod";
    public static final String FLAG_MODULE_CODE = "/code";
    public static final String FLAG_MAP = "/map";
    public static final String FLAG_MASTER_UNIVERSITYLIST = "/muni";
    public static final String FLAG_SELECTED_UNIVERSITYLIST = "/suni";
    public static final String FLAG_MASTER_MODULELIST = "/mmod";
    public static final String FLAG_SELECTED_MODULELIST = "/smod";

    // Exception messages
    public static final String ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS = "Incorrect flags passed.";
    public static final String ERRORMSG_PARSEEXCEPTION_INCORRECTCOMMAND = "Incorrect command passed.";
    public static final String ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS = "Missing arguments.";
    public static final String ERRORMSG_PARSEEXCEPTION_UNINOTFOUND = "University not found.";
    public static final String ERRORMSG_PARSEEXCEPTION_MODNOTFOUND = "Module not found.";
    public static final String ERRORMSG_PARSEEXCEPTION_MAPPINGNOTFOUND = "Mapping not found.";
    public static final String ERRORMSG_PARSEEXCEPTION_MATCHERCLASS = "Matcher class exception.";
    public static final String ERRORMSG_PARSEEXCEPTION_DUPLICATEMOD = "This module has already been added.";
    public static final String ERRORMSG_PARSEEXCEPTION_DUPLICATEUNI = "This university has already been added.";
    public static final String ERRORMSG_PARSEEXCEPTION_DUPLICATEMAP = "This mapping has already been added.";
    public static final String ERRORMSG_PARSEEXCEPTION_INVALIDUNI = "This university is not in your selected list.";
    public static final String ERRORMSG_PARSEEXCEPTION_NOMAPPING = "This university has no available mappings.";
    public static final String ERRORMSG_PARSEEXCEPTION_INVALIDMAPPING = "Mapping with this index does not exist.";

    // Log
    public static final String LOGGER_NAME = "SEPlanner log";
    public static final String LOGMSG_PARSESUCCESS = "Parse completed successfully";
    public static final String LOGMSG_PARSESTARTED = "Parse process started";
    public static final String LOGMSG_PARSEFAILED = "Parse failed";

}
