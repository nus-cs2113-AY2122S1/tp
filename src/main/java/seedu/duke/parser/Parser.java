package seedu.duke.parser;

import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.AddExpenditureCommand;
import seedu.duke.commands.AddLoanCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteAllExpenditureCommand;
import seedu.duke.commands.DeleteAllLoanCommand;
import seedu.duke.commands.DeleteBudgetCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DeleteMultipleExpenditureCommand;
import seedu.duke.commands.DeleteMultipleLoanCommand;
import seedu.duke.commands.DeleteSingleExpenditureCommand;
import seedu.duke.commands.DeleteSingleLoanCommand;
import seedu.duke.commands.EditBudgetCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.EditExpenditureCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ListRecordsCommand;
import seedu.duke.commands.YearCommand;
import seedu.duke.data.records.Category;
import seedu.duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import static seedu.duke.common.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_AMOUNT;
import static seedu.duke.common.Messages.MESSAGE_INVALID_CATEGORY;
import static seedu.duke.common.Messages.MESSAGE_INVALID_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_DATE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_DELETE_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_INDEX_OF_EXPENDITURE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_LIST_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_MONTH_OF_BUDGET;

//import java.time.LocalDate;
//import java.util.Locale;

public class Parser {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Offset after reading type identifier tag. */
    public static final int TYPE_IDENTIFIER_END_INDEX = 2;

    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        if (split.length == 2) {
            return new String[]{split[0].toLowerCase(), split[1]};
        }
        return new String[]{split[0].toLowerCase(), ""};
    }

    //    public static String[] splitExpenditureParams(String expenditureParams) {
    //        return expenditureParams.split(" ", 3);
    //    }

    public static HashMap<String, String> splitArguments(String commandParams, String[] prefixes) {
        HashMap<String, String> argumentMap = new HashMap<>();
        for (String prefix : prefixes) {
            String parameterValue;
            int startIndex = commandParams.indexOf(prefix) + 2;
            if (startIndex == 1) {
                argumentMap.put(prefix, "");
                continue;
            }
            String substring = commandParams.substring(startIndex);
            if (substring.contains("/")) {
                int endIndex = substring.indexOf("/") - 2;
                parameterValue = substring.substring(0, endIndex);
            } else {
                parameterValue = substring;
            }
            argumentMap.put(prefix, parameterValue);
        }
        return argumentMap;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) throws ArrayIndexOutOfBoundsException {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1].trim();
        assert commandType.equals(commandType.toLowerCase());
        Command command;
        try {
            switch (commandType) {
            case AddCommand.COMMAND_WORD:
                command = prepareAddCommand(commandParams);
                break;
            case DeleteCommand.COMMAND_WORD:
                command = prepareDeleteCommand(commandParams);
                break;
            case ListRecordsCommand.COMMAND_WORD:
                command = prepareListMonthCommand(commandParams);
                break;
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand();
                break;
            case YearCommand.COMMAND_WORD:
                command = prepareYearCommand(commandParams);
                break;
            case HelpCommand.COMMAND_WORD:
                command = new HelpCommand();
                break;
            case FindCommand.COMMAND_WORD:
                command = prepareFindCommand(commandParams);
                break;
            case EditCommand.COMMAND_WORD:
                command = prepareEditCommand(commandParams);
                break;
            default:
                command = new InvalidCommand("Sorry. I don't understand your command!");
                break;
            }
        } catch (NumberFormatException e) {
            command = new InvalidCommand("Month/ Index inputs don't exist! They are compulsory!");
        } catch (EmptyDescriptionException e) {
            command = new InvalidCommand("Expenditure description is empty!");
        }
        return command;
    }

    private Command prepareEditBudgetCommand(String commandParams) {
        String[] split = commandParams.trim().split("m/|a/", 3);
        assert split[0].equals("");

        int month;
        double amount;
        month = Integer.parseInt(split[1].trim());
        amount = Double.parseDouble(split[TYPE_IDENTIFIER_END_INDEX].trim());

        return new EditBudgetCommand(month, amount);
    }

    private Command prepareEditCommand(String commandParams) throws EmptyDescriptionException {
        String editOption = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
        String paramsToEdit = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
        switch (editOption) {
        case ("-b"):
            return prepareEditBudgetCommand(paramsToEdit);
        case ("-e"):
            return EditExpenditureParser.parse(commandParams);
        default:
            return new InvalidCommand("Missing inputs! Please indicate '-e', '-b' or '-l");
        }
    }

    private Command prepareEditExpenditureCommand(String commandParams) {

        String[] split = commandParams.trim().split("m/|i/", 4);
        assert split[0].equals("");

        int month;
        int index;

        month = Integer.parseInt(split[1].trim());
        int indexOfFirstSpace = split[2].indexOf(" ");
        String indexString = split[2].substring(0, indexOfFirstSpace);
        index = Integer.parseInt(indexString.trim()) - 1;

        String paramString = split[2].substring(indexOfFirstSpace);
        String description;
        LocalDate date;
        double amount;

        if (paramString.contains("c/")) {
            int startIndex = commandParams.indexOf("c/") + 2;
            String substring = commandParams.substring(startIndex);
            if (substring.contains("/")) {
                int endIndex = substring.indexOf("/") - 2;
                description = substring.substring(0, endIndex);
            } else {
                description = substring;
            }
        } else {
            description = "";
        }

        if (paramString.contains("a/")) {
            int startIndex = commandParams.indexOf("a/") + 2;
            String substring = commandParams.substring(startIndex);
            if (substring.contains("/")) {
                int endIndex = substring.indexOf("/") - 2;
                amount = Double.parseDouble(substring.substring(0, endIndex));
            } else {
                amount = Double.parseDouble(substring);
            }
        } else {
            amount = 0.00;
        }

        if (paramString.contains("d/")) {
            int startIndex = commandParams.indexOf("d/") + 2;
            String substring = commandParams.substring(startIndex);
            if (substring.contains("/")) {
                int endIndex = substring.indexOf("/") - 2;
                date = LocalDate.parse(substring.substring(0, endIndex));
            } else {
                date = LocalDate.parse(substring);
            }
        } else {
            date = LocalDate.now();
        }


        //        double amount = Double.parseDouble(split[3].trim());
        //        LocalDate date;
        //        if (split[4].equals("")) {
        //            date = LocalDate.now();
        //        } else {
        //            date = LocalDate.parse(split[4].trim());
        //        }
        //        String description = split[4].trim();

        return new EditExpenditureCommand(month, index, amount, date, description);
    }

    private Command prepareListMonthCommand(String commandParams) {
        try {
            String listOption = commandParams.substring(2);
            switch (listOption) {
            case ("all"):
                return new ListRecordsCommand();
            default:
                int listMonth = Integer.parseInt(listOption);
                return new ListRecordsCommand(listMonth);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_LIST_COMMAND, ListRecordsCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits params into the different add commands.
     *
     * @param commandParams raw String commandParams
     * @return AddBudgetCommand or AddExpenditureCommand depending on the addType
     */
    private Command prepareAddCommand(String commandParams) {
        try {
            String addType = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
            String addParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (addType) {
            case ("-b"):
                return prepareAddBudgetCommand(addParams);
            case ("-e"):
                return prepareAddExpenditureCommand(addParams);
            case ("-l"):
                return prepareAddLoanCommand(addParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
        }

    }


    private Command prepareAddLoanCommand(String addParams) {
        try {
            String[] split = addParams.trim().split("n/|a/|d/", 4);
            assert split[0].equals("");
            String name = split[1].trim();
            double amount = Double.parseDouble(split[2].trim());
            LocalDate date;
            if (split[3].equals("")) {
                date = LocalDate.now();
            } else {
                date = LocalDate.parse(split[3].trim());
            }
            return new AddLoanCommand(name, amount, date);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_AMOUNT, AddExpenditureCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException dtpe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DATE, AddExpenditureCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits the commandParams into amount and date.
     *
     * @param addParams String without type identifier
     * @return an AddBudgetCommand with proper parameters
     * @throws ArrayIndexOutOfBoundsException if amount input does not exist.
     */
    private Command prepareAddBudgetCommand(String addParams) throws ArrayIndexOutOfBoundsException {
        try {
            String[] split = addParams.trim().split("a/|m/", 3);
            assert split[0].equals("");
            double amount = Double.parseDouble(split[1].trim());
            int month = Integer.parseInt(split[2].trim());

            return new AddBudgetCommand(amount, month);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_MONTH_OF_BUDGET, AddBudgetCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits the commandParams into description, amount, date.
     *
     * @param addParams String without type identifier
     * @return an AddExpenditureCommand with proper parameters
     */
    private Command prepareAddExpenditureCommand(String addParams) {
        try {
            String[] split = addParams.trim().split("n/|a/|d/|c/", 5);
            assert split[0].equals("");
            String description = split[1].trim();
            double amount = Double.parseDouble(split[2].trim());
            LocalDate date;
            if (split[3].trim().equals("")) {
                date = LocalDate.now();
            } else {
                date = LocalDate.parse(split[3].trim());
            }
            Category category;
            if (split[4].equals("")) {
                category = Category.GENERAL;
            } else {
                category = Category.valueOf(split[4]);
            }
            return new AddExpenditureCommand(description, amount, date, category);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_AMOUNT, AddExpenditureCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException dtpe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DATE, AddExpenditureCommand.MESSAGE_USAGE));
        } catch (IllegalArgumentException iae) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_CATEGORY, AddExpenditureCommand.MESSAGE_USAGE));
        }

    }

    private Command prepareFindCommand(String commandParams) {
        try {
            return new FindCommand(commandParams);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format("Add message later please", AddCommand.MESSAGE_USAGE));
        }
    }

    private Command prepareYearCommand(String commandParams) {
        try {
            String directoryOfRecordList = "./data/" + commandParams + ".txt";
            return new YearCommand(directoryOfRecordList);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits params into the different delete commands.
     *
     * @param commandParams raw String commandParams
     * @return AddBudgetCommand or AddExpenditureCommand depending on the addType
     */
    private Command prepareDeleteCommand(String commandParams) {
        try {
            String deleteType = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
            String deleteParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (deleteType) {
            case ("-b"):
                return prepareDeleteBudgetCommand(deleteParams);
            case ("-e"):
                return prepareDeleteExpenditureCommand(deleteParams);
            case ("-l"):
                return prepareDeleteLoanCommand(deleteParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DELETE_COMMAND, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits the commandParams to get which month of budget to be deleted.
     *
     * @param commandParams raw String input
     * @return a DeleteBudgetCommand with proper parameters
     */
    private Command prepareDeleteBudgetCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("m/", 2);
            assert split[0].equals("");
            String indexOfMonthString = split[1].trim();
            int month = Integer.parseInt(indexOfMonthString);
            return new DeleteBudgetCommand(month);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(
                    String.format(MESSAGE_INVALID_MONTH_OF_BUDGET, DeleteBudgetCommand.MESSAGE_USAGE)
            );
        }
    }

    /**
     * Splits the commandParams to get index/s of expenditure to be deleted.
     *
     * @param commandParams raw String input
     * @return a DeleteExpenditureCommand with proper parameters
     */
    private Command prepareDeleteExpenditureCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("m/|i/", 3);
            assert split[0].equals("");
            String indexOfMonthString = split[1].trim();
            int month = Integer.parseInt(indexOfMonthString);
            String indexOfExpenditureToBeDeleted = split[2].trim();
            if (indexOfExpenditureToBeDeleted.length() == 1) {
                int index;
                index = Integer.parseInt(indexOfExpenditureToBeDeleted);
                return new DeleteSingleExpenditureCommand(index, month);
            } else if (indexOfExpenditureToBeDeleted.length() > 1) {
                int startIndex;
                int endIndex;
                String[] indexSplit = indexOfExpenditureToBeDeleted.trim().split("-|", 2);
                String index1 = indexSplit[0].trim();
                String index2 = indexSplit[1].trim();
                startIndex = Integer.parseInt(index1);
                endIndex = Integer.parseInt(index2);
                return new DeleteMultipleExpenditureCommand(startIndex, endIndex, month);
            }

            return new DeleteAllExpenditureCommand(month);

        } catch (NumberFormatException nfe) {
            return new InvalidCommand(
                    String.format(MESSAGE_INVALID_INDEX_OF_EXPENDITURE, DeleteSingleExpenditureCommand.MESSAGE_USAGE)
            );
        }
    }

    /**
     * Splits the commandParams to get index/s of loan to be deleted.
     *
     * @param commandParams raw String input
     * @return a DeleteExpenditureCommand with proper parameters
     */
    private Command prepareDeleteLoanCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("m/|i/", 3);
            assert split[0].equals("");
            String indexOfMonthString = split[1].trim();
            int month = Integer.parseInt(indexOfMonthString);
            String indexOfLoanToBeDeleted = split[2].trim();
            if (indexOfLoanToBeDeleted.length() == 1) {
                int index;
                index = Integer.parseInt(indexOfLoanToBeDeleted);
                return new DeleteSingleLoanCommand(index, month);
            } else if (indexOfLoanToBeDeleted.length() > 1) {
                int startIndex;
                int endIndex;
                String[] indexSplit = indexOfLoanToBeDeleted.trim().split("-|", 2);
                String index1 = indexSplit[0].trim();
                String index2 = indexSplit[1].trim();
                startIndex = Integer.parseInt(index1);
                endIndex = Integer.parseInt(index2);
                return new DeleteMultipleLoanCommand(startIndex, endIndex, month);
            }

            return new DeleteAllLoanCommand(month);

        } catch (NumberFormatException nfe) {
            return new InvalidCommand(
                    String.format(MESSAGE_INVALID_INDEX_OF_EXPENDITURE, DeleteSingleLoanCommand.MESSAGE_USAGE)
            );
        }
    }
}