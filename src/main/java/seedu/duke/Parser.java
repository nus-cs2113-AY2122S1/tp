package seedu.duke;

import seedu.commands.AddExpenseCommand;
import seedu.commands.AddIncomeCommand;
import seedu.commands.ClearAllEntriesCommand;
import seedu.commands.Command;
import seedu.commands.DeleteExpenseCommand;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.ExitCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListExpenseCommand;
import seedu.commands.ListIncomeCommand;
import seedu.commands.TotalExpenseBetweenCommand;
import seedu.commands.TotalExpenseCommand;
import seedu.commands.TotalIncomeBetweenCommand;
import seedu.commands.TotalIncomeCommand;

import seedu.commands.FindCommand;
import seedu.commands.BalanceCommand;


import seedu.entry.Expense;
import seedu.entry.Income;

import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidExpenseIndexException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidIncomeIndexException;
import seedu.utility.Messages;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_EXPENSE_ARGUMENT_FORMAT =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern DELETE_EXPENSE_ARGUMENT_FORMAT =
            Pattern.compile("i/(?<index>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern DELETE_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("i/(?<index>[^/]+)");

    private static final Pattern DATE_RANGE_ARGUMENT_FORMAT =
            Pattern.compile("s/(?<start>[^/]+)"
                    + "e/(?<end>[^/]+)");
    
    
    private static final String HELP_COMMAND_KEYWORD = "help";
    private static final String ADD_EXPENSE_KEYWORD = "add_ex";
    private static final String ADD_INCOME_KEYWORD = "add_in";
    private static final String DELETE_EXPENSE_KEYWORD = "del_ex";
    private static final String DELETE_INCOME_KEYWORD = "del_in";
    private static final String LIST_EXPENSE_KEYWORD = "list_ex";
    private static final String LIST_INCOME_KEYWORD = "list_in";
    private static final String TOTAL_EXPENSE_KEYWORD = "total_ex";
    private static final String TOTAL_INCOME_KEYWORD = "total_in";
    private static final String FIND_KEYWORD = "find";
    private static final String BALANCE_KEYWORD = "balance";
    private static final String EXIT_KEYWORD = "end";
    private static final String EXPENSE_RANGE_KEYWORD = "btw_ex";
    private static final String INCOME_RANGE_KEYWORD = "btw_in";
    private static final String CLEAR_ALL_ENTRIES_KEYWORD = "clear_all_entries";
    
    private static final String DATA_SEPARATOR = ", ";

    private static final Pattern EXPENSE_DATA_FORMAT
            = Pattern.compile("E" + DATA_SEPARATOR + "(?<description>[^/]+)" + DATA_SEPARATOR
            + "(?<amount>[^/]+)" + DATA_SEPARATOR + "(?<category>[^/]+)" + DATA_SEPARATOR + "(?<date>[^/]+)");
    private static final Pattern INCOME_DATA_FORMAT
            = Pattern.compile("I" + DATA_SEPARATOR + "(?<description>[^/]+)" + DATA_SEPARATOR
            + "(?<amount>[^/]+)" + DATA_SEPARATOR + "(?<category>[^/]+)" + DATA_SEPARATOR + "(?<date>[^/]+)");
    

    /**
     * Parses user input into command for execution.
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case HELP_COMMAND_KEYWORD:
            return prepareHelp(arguments);
        case ADD_EXPENSE_KEYWORD:
            return prepareAddExpense(arguments);
        case ADD_INCOME_KEYWORD:
            return prepareAddIncome(arguments);
        case DELETE_EXPENSE_KEYWORD:
            return prepareDeleteExpense(arguments);
        case DELETE_INCOME_KEYWORD:
            return prepareDeleteIncome(arguments);
        case LIST_EXPENSE_KEYWORD:
            return prepareListExpense(arguments);
        case LIST_INCOME_KEYWORD:
            return prepareListIncome(arguments);
        case TOTAL_EXPENSE_KEYWORD:
            return prepareTotalExpense(arguments);
        case TOTAL_INCOME_KEYWORD:
            return prepareTotalIncome(arguments); 
        case FIND_KEYWORD:
            return prepareFind(arguments);
        case BALANCE_KEYWORD:
            return prepareBalance(arguments);
        case EXIT_KEYWORD:
            return prepareExit(arguments);
        case EXPENSE_RANGE_KEYWORD:
            return prepareExpenseRange(arguments);
        case INCOME_RANGE_KEYWORD:
            return prepareIncomeRange(arguments);
        case CLEAR_ALL_ENTRIES_KEYWORD:
            return prepareClearAllEntries(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareIncomeRange(String arguments) {
        final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        try {
            String start = matcher.group("start").trim();
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String end = matcher.group("end").trim();
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new TotalIncomeBetweenCommand(startDate,endDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }
    
    private Command prepareExpenseRange(String arguments) {
        final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        try {
            String start = matcher.group("start").trim();
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String end = matcher.group("end").trim();
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new TotalExpenseBetweenCommand(startDate,endDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }
    
    private Command prepareBalance(String arguments) {
        if (arguments.trim().isBlank()) {
            return new BalanceCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }
    
    private Command prepareFind(String arguments) {
        if (arguments.trim().isBlank()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        return new FindCommand(arguments.trim());
    }
    
    
    private Command prepareHelp(String arguments) {
        if (arguments.trim().isBlank()) {
            return new HelpCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareAddExpense(String arguments) {
        final Matcher matcher = ADD_EXPENSE_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenAmount = matcher.group("amount").trim();
        double expenseAmount;
        try {
            expenseAmount = parseExpenseAmount(userGivenAmount);
        } catch (InvalidExpenseAmountException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert expenseAmount > 0;

        String expenseDescription = matcher.group("description").trim();
        if (expenseDescription.isBlank()) {
            return new InvalidCommand(Messages.BLANK_DESCRIPTION_MESSAGE);
        }
        
        String expenseCategory = matcher.group("category").trim().toUpperCase();
        if (expenseCategory.isBlank()) {
            return new InvalidCommand(Messages.BLANK_CATEGORY_MESSAGE);
        }
        Expense expense = new Expense(expenseDescription, expenseAmount, expenseCategory);
        return new AddExpenseCommand(expense);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareAddIncome(String arguments) {
        final Matcher matcher = ADD_INCOME_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenAmount = matcher.group("amount").trim();
        double incomeAmount;
        try {
            incomeAmount = parseIncomeAmount(userGivenAmount);
        } catch (InvalidIncomeAmountException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert incomeAmount > 0;

        String incomeDescription = matcher.group("description").trim();
        if (incomeDescription.isBlank()) {
            return new InvalidCommand(Messages.BLANK_DESCRIPTION_MESSAGE);
        }

        String incomeCategory = matcher.group("category").trim().toUpperCase();
        if (incomeCategory.isBlank()) {
            return new InvalidCommand(Messages.BLANK_CATEGORY_MESSAGE);
        }
        Income income = new Income(incomeDescription, incomeAmount, incomeCategory);
        return new AddIncomeCommand(income);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareDeleteExpense(String arguments) {
        final Matcher matcher = DELETE_EXPENSE_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenIndex = matcher.group("index").trim();
        int deleteExpenseIndex;
        try {
            deleteExpenseIndex = parseExpenseIndex(userGivenIndex);
        } catch (InvalidExpenseIndexException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert deleteExpenseIndex >= 1;

        return new DeleteExpenseCommand(deleteExpenseIndex);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareDeleteIncome(String arguments) {
        final Matcher matcher = DELETE_INCOME_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenIndex = matcher.group("index").trim();
        int deleteIncomeIndex;
        try {
            deleteIncomeIndex = parseIncomeIndex(userGivenIndex);
        } catch (InvalidIncomeIndexException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert deleteIncomeIndex >= 1;

        return new DeleteIncomeCommand(deleteIncomeIndex);
    }

    private Command prepareListExpense(String arguments) {
        if (arguments.trim().isBlank()) {
            return new ListExpenseCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareListIncome(String arguments) {
        if (arguments.trim().isBlank()) {
            return new ListIncomeCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareTotalExpense(String arguments) {
        if (arguments.trim().isBlank()) {
            return new TotalExpenseCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareTotalIncome(String arguments) {
        if (arguments.trim().isBlank()) {
            return new TotalIncomeCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }
    
    private Command prepareClearAllEntries(String arguments) {
        if (arguments.trim().isBlank()) {
            return new ClearAllEntriesCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareExit(String arguments) {
        if (arguments.trim().isBlank()) {
            return new ExitCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private double parseExpenseAmount(String userGivenAmount) throws InvalidExpenseAmountException {
        double expenseAmount;
        try {
            expenseAmount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidExpenseAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (expenseAmount <= 0) {
            throw new InvalidExpenseAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        }
        return expenseAmount;
    }

    private double parseIncomeAmount(String userGivenAmount) throws InvalidIncomeAmountException {
        double incomeAmount;
        try {
            incomeAmount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidIncomeAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (incomeAmount <= 0) {
            throw new InvalidIncomeAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        }
        return incomeAmount;
    }

    private int parseExpenseIndex(String userGivenIndex) throws InvalidExpenseIndexException {
        int deleteExpenseIndex;
        try {
            deleteExpenseIndex = Integer.parseInt(userGivenIndex);
        } catch (NumberFormatException e) {
            throw new InvalidExpenseIndexException(Messages.N0N_NUMERIC_INDEX_MESSAGE);
        }
        if (deleteExpenseIndex <= 0) {
            throw new InvalidExpenseIndexException(Messages.NON_POSITIVE_INDEX_MESSAGE);
        }
        return deleteExpenseIndex;
    }

    private int parseIncomeIndex(String userGivenIndex) throws InvalidIncomeIndexException {
        int deleteIncomeIndex;
        try {
            deleteIncomeIndex = Integer.parseInt(userGivenIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIncomeIndexException(Messages.N0N_NUMERIC_INDEX_MESSAGE);
        }
        if (deleteIncomeIndex <= 0) {
            throw new InvalidIncomeIndexException(Messages.NON_POSITIVE_INDEX_MESSAGE);
        }
        return deleteIncomeIndex;
    }

    public String convertExpenseToData(Expense expense) {
        return "E" + DATA_SEPARATOR + expense.getDescription() + DATA_SEPARATOR + expense.getValue() + DATA_SEPARATOR 
                + expense.getCategory() + DATA_SEPARATOR + expense.getDate();
    }

    public String convertIncomeToData(Income income) {
        return "I" + DATA_SEPARATOR + income.getDescription() + DATA_SEPARATOR + income.getValue() + DATA_SEPARATOR 
                + income.getCategory() + DATA_SEPARATOR + income.getDate();
    }
    
    public Expense convertDataToExpense(String data) throws InvalidExpenseAmountException,
            InvalidExpenseDataFormatException, DateTimeException {
        final Matcher matcher = EXPENSE_DATA_FORMAT.matcher(data);
        if (!matcher.matches()) {
            throw new InvalidExpenseDataFormatException();
        }
        
        String expenseDescription = matcher.group("description").trim();
        if (expenseDescription.isBlank()) {
            throw new InvalidExpenseDataFormatException();
        }
        String dataAmount = matcher.group("amount").trim();
        double expenseAmount = parseExpenseAmount(dataAmount);
        String expenseCategory = matcher.group("category").trim();
        if (expenseCategory.isBlank()) {
            throw new InvalidExpenseDataFormatException();
        }
        String date = matcher.group("date").trim();
        LocalDate expenseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Expense(expenseDescription, expenseAmount, expenseCategory, expenseDate);
    }

    public Income convertDataToIncome(String data) throws InvalidIncomeAmountException, 
            InvalidIncomeDataFormatException, DateTimeException {
        final Matcher matcher = INCOME_DATA_FORMAT.matcher(data);
        if (!matcher.matches()) {
            throw new InvalidIncomeDataFormatException();
        }
        
        String incomeDescription = matcher.group("description").trim();
        if (incomeDescription.isBlank()) {
            throw new InvalidIncomeDataFormatException();
        }
        String dataAmount = matcher.group("amount").trim();
        double incomeAmount = parseIncomeAmount(dataAmount);
        String incomeCategory = matcher.group("category").trim();
        if (incomeCategory.isBlank()) {
            throw new InvalidIncomeDataFormatException();
        }
        String date = matcher.group("date").trim();
        LocalDate incomeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Income(incomeDescription, incomeAmount, incomeCategory, incomeDate);
    }
    
}
