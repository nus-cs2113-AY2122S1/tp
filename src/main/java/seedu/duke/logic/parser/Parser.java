package seedu.duke.logic.parser;


import seedu.duke.logic.commands.AddCommand;
import seedu.duke.logic.commands.AddRecurringCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.DeleteAllCommand;
import seedu.duke.logic.commands.DeleteCommand;
import seedu.duke.logic.commands.DeleteRecurringCommand;
import seedu.duke.logic.commands.EditCommand;
import seedu.duke.logic.commands.EditRecurringCommand;
import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.SetBudgetCommand;
import seedu.duke.logic.commands.ViewBudgetCommand;
import seedu.duke.logic.commands.ViewCategoriesCommand;
import seedu.duke.logic.commands.ViewCommand;
import seedu.duke.model.entries.ExpenseCategory;
import seedu.duke.model.entries.IncomeCategory;
import seedu.duke.model.entries.Interval;
import seedu.duke.model.entries.Type;
import seedu.duke.model.entries.Entry;
import seedu.duke.model.entries.Expense;
import seedu.duke.model.entries.Income;
import seedu.duke.model.entries.RecurringEntry;
import seedu.duke.model.entries.RecurringExpense;
import seedu.duke.model.entries.RecurringIncome;
import seedu.duke.utility.MintException;
import seedu.duke.model.financemanager.RecurringFinanceManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final String userTag = "\\s[a-z]/";
    public static final String userTagRaw = "(.*)\\s[a-z]/(.*)";
    public static final String STRING_EMPTY = "";
    public static final String SEPARATOR = ". ";
    public static final String STRING_INCLUDE = "Please include the following in your command or make them valid: \n";
    public static final String STRING_DESCRIPTION = "Name(Description) of item\n";
    public static final String STRING_DATE = "Date of purchase or start date of the recurring period"
            + " (2000-01-01 to 2200-12-31)\n";
    public static final String STRING_AMOUNT = "Amount (Valid non-negative number below 1 million (after automatically"
            + " rounding to 2s.f.), no negative signs)\n";
    public static final String STRING_CATNUM = "Category number (0 to 7)\n";
    public static final String STRING_INTERVAL = "Interval of item (month or year in case-insensitive format e.g.,"
            + " mOnTh, year, MONTH)\n";
    public static final String STRING_END_DATE = "End date of the recurring period (should be after the start date"
            + " but within valid range)\n";
    public static final String ERROR_INVALID_CATNUM = "Please enter a valid category number! c/0 to c/7";
    public static final String ADD_ENTRY = "add";
    public static final String ADD_RECURRING = "addR";
    public static final String DELETE_ENTRY = "delete";
    public static final String DELETE_RECURRING = "deleteR";
    public static final String EDIT_ENTRY = "edit";
    public static final String VIEW = "view";
    public static final String VIEW_ALL_CATEGORIES = "cat";
    public static final String EDIT_RECURRING = "editR";
    public static final String SET_BUDGET = "set";
    public static final String VIEW_BUDGET = "budget";
    public static final String DELETEALL = "deleteAll";
    public static final String DELETEALL2 = "deleteall";
    public static final String HELP = "help";
    public static final String EXIT = "exit";
    public static final String SPACE = "\\s+";
    public static final String AMOUNT_STR_DEFAULT = "0";
    public static final String MONTH_STR_DEFAULT = "MONTH";
    public static final String END_DATE_STR_DEFAULT = "2200-12-31";
    public static final String CAT_NUM_FOOD = "0";
    public static final String CAT_NUM_ENTERTAINMENT = "1";
    public static final String CAT_NUM_TRASPORATION = "2";
    public static final String CAT_NUM_HOUSEHOLD = "3";
    public static final String CAT_NUM_APPAREL = "4";
    public static final String CAT_NUM_BEAUTY = "5";
    public static final String CAT_NUM_GIFT = "6";
    public static final String CAT_NUM_OTHERS = "7";
    public static final String CAT_NUM_ALLOWANCE = "0";
    public static final String CAT_NUM_WAGES = "1";
    public static final String CAT_NUM_SALARY = "2";
    public static final String CAT_NUM_INTEREST = "3";
    public static final String CAT_NUM_INVESTMENT = "4";
    public static final String CAT_NUM_COMMISSION = "5";
    public static final String TYPE_NAME = "n";
    public static final String TYPE_DATE = "d";
    public static final String TYPE_AMOUNT = "a";
    public static final String TYPE_CATEGORY = "c";
    public static final String TYPE_INTERVAL = "i";
    public static final String TYPE_END_DATE = "e";
    public static final String INCOME_KEYWORD = "income";
    public static final String TYPE_NORMAL = "n";
    public static final String NORMAL_KEYWORD = "normal";
    public static final String RECURRING_KEYWORD = "recurring";
    public static final String TYPE_RECURRING = "r";
    public static final String NAME_KEYWORD = "name";
    public static final String DATE_KEYWORD = "date";
    public static final String AMOUNT_KEYWORD = "amount";
    public static final String CAT_NUM_KEYWORD = "catNum";
    public static final String END_DATE_KEYWORD = "endDate";
    public static final String INTERVAL_KEYWORD = "interval";
    protected String command;
    protected String name;
    protected String dateStr;
    protected LocalDate date;
    protected String amountStr;
    protected double amount;
    protected String catNumStr;
    protected ExpenseCategory expenseCategory;
    protected IncomeCategory incomeCategory;
    protected String intervalStr;
    protected Interval interval;
    protected String endDateStr;
    protected LocalDate endDate;
    protected Type type;
    protected RecurringFinanceManager recurringFinanceManager = new RecurringFinanceManager();
    protected boolean isRecurring = false;
    protected String[] argumentsArray;
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy-M-dd][yyyy-MM-d][yyyy-M-d]"
            + "[dd-MM-yyyy][d-MM-yyyy][d-M-yyyy][dd-M-yyyy]"
            + "[dd MMM yyyy][d MMM yyyy][dd MMM yy][d MMM yy]");

    public Parser() {
    }

    //@@author irvinseet
    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    private static int indexOfTag(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.start() : -1;
    }

    public String parserExtractCommand(String userInput) {
        return userInput.split(SPACE, 2)[0];
    }

    public int getCurrentTagIndex(String userInput) {
        int currentTagIndex;
        currentTagIndex = indexOfTag(userInput, userTag);
        return currentTagIndex;
    }

    public String getTagType(String userInput, int currentTagIndex) {
        String tagType;
        tagType = String.valueOf(userInput.charAt(currentTagIndex + 1));
        return tagType;
    }

    public String getDescription(String userInput, int currentTagIndex) {
        String description;
        description = userInput.substring(currentTagIndex + 3).trim();
        return description;
    }

    public String getDescription(String userInput, int currentTagIndex, int nextTagIndex) {
        String description;
        description = userInput.substring(currentTagIndex + 3, nextTagIndex).trim();
        return description;
    }

    public int getNextTagIndex(String userInput, int currentTagIndex) {
        int nextTagIndex;
        nextTagIndex = indexOfTag(userInput.substring(currentTagIndex + 3), userTag) + 3 + currentTagIndex;
        return nextTagIndex;
    }

    public boolean hasNextTag(String userString, int currentTagIndex) {
        return userString.substring(currentTagIndex + 3).matches(userTagRaw);
    }

    private void setAmountViaAmountStr(String amountStr) {
        this.amount = amountTo2SF(amountStr);
    }

    private ExpenseCategory setCategoryViaCatNum(String catNum) throws MintException {
        switch (catNum) {
        case CAT_NUM_FOOD:
            return ExpenseCategory.FOOD;
        case CAT_NUM_ENTERTAINMENT:
            return ExpenseCategory.ENTERTAINMENT;
        case CAT_NUM_TRASPORATION:
            return ExpenseCategory.TRANSPORTATION;
        case CAT_NUM_HOUSEHOLD:
            return ExpenseCategory.HOUSEHOLD;
        case CAT_NUM_APPAREL:
            return ExpenseCategory.APPAREL;
        case CAT_NUM_BEAUTY:
            return ExpenseCategory.BEAUTY;
        case CAT_NUM_GIFT:
            return ExpenseCategory.GIFT;
        case CAT_NUM_OTHERS:
            return ExpenseCategory.OTHERS;
        default:
            throw new MintException(ERROR_INVALID_CATNUM);
        }
    }

    private IncomeCategory setIncomeCategoryViaCatNum(String catNum) throws MintException {
        switch (catNum) {
        case CAT_NUM_ALLOWANCE:
            return IncomeCategory.ALLOWANCE;
        case CAT_NUM_WAGES:
            return IncomeCategory.WAGES;
        case CAT_NUM_SALARY:
            return IncomeCategory.SALARY;
        case CAT_NUM_INTEREST:
            return IncomeCategory.INTEREST;
        case CAT_NUM_INVESTMENT:
            return IncomeCategory.INVESTMENT;
        case CAT_NUM_COMMISSION:
            return IncomeCategory.COMMISSION;
        case CAT_NUM_GIFT:
            return IncomeCategory.GIFT;
        case CAT_NUM_OTHERS:
            return IncomeCategory.OTHERS;
        default:
            throw new MintException(ERROR_INVALID_CATNUM);
        }
    }

    private void setFieldsByTag(String description, String tagType) throws MintException {
        switch (tagType) {
        case TYPE_NAME:
            this.name = description;
            break;
        case TYPE_DATE:
            this.dateStr = description;
            break;
        case TYPE_AMOUNT:
            this.amountStr = description;
            break;
        case TYPE_CATEGORY:
            this.catNumStr = description.split(SPACE, 2)[0];
            this.expenseCategory = setCategoryViaCatNum(catNumStr);
            this.incomeCategory = setIncomeCategoryViaCatNum(catNumStr);
            break;
        case TYPE_INTERVAL:
            if (isRecurring) {
                this.intervalStr = description;
            } else {
                throw new MintException(MintException.ERROR_INVALID_TAG_ERROR);
            }
            break;
        case TYPE_END_DATE:
            if (isRecurring) {
                this.endDateStr = description;
            } else {
                throw new MintException(MintException.ERROR_INVALID_TAG_ERROR);
            }
            break;
        default:
            throw new MintException(MintException.ERROR_INVALID_TAG_ERROR);
        }
    }

    private void initDateStr() {
        this.dateStr = LocalDate.now().toString();
    }

    private void initAmountStr() {
        this.amountStr = AMOUNT_STR_DEFAULT;
    }

    private void initIntervalStr() {
        this.intervalStr = MONTH_STR_DEFAULT;
    }

    private void initCatNumStr() {
        this.catNumStr = CAT_NUM_OTHERS;
    }

    private void initEndDateStr() {
        this.endDateStr = END_DATE_STR_DEFAULT;
    }

    //@@author irvinseet

    /**
     * Parse user input based on tags specified and set fields accordingly to the tags.
     * Tags are of format " x/", where x can be any alphabet and there should be a space in front of it.
     * Pattern matching is done by regular expressions to ensure flexibility of the program.
     *
     * @param userInput The String that we want to parse
     * @throws MintException when user did not follow the correct tagging format, did not provide mandatory tags
     *                       or included invalid tags.
     */
    private void parseInputByTagsLoop(String userInput) throws MintException {
        String tagType;
        String description;
        while (userInput.matches(userTagRaw)) {
            int currentTagIndex = getCurrentTagIndex(userInput);
            int nextTagIndex = userInput.length();
            tagType = getTagType(userInput, currentTagIndex);

            if (hasNextTag(userInput, currentTagIndex)) {
                nextTagIndex = getNextTagIndex(userInput, currentTagIndex);
                description = getDescription(userInput, currentTagIndex, nextTagIndex);
            } else {
                description = getDescription(userInput, currentTagIndex);
            }

            setFieldsByTag(description, tagType);
            userInput = userInput.substring(nextTagIndex);
        }
    }

    //@@author irvinseet
    public ArrayList<String> parseInputByTags(String userInput) throws MintException {
        // for Add, initialise Date to today's date and category to "Others"
        try {
            ValidityChecker.checkTagsFormatSpacing(userInput);
            ValidityChecker.identifyDuplicateTags(this, userInput);
            parseType(userInput);
            parseInputByTagsLoop(userInput);
            ArrayList<String> validTags = ValidityChecker.checkExistenceAndValidityOfTags(this, userInput);
            isRecurring = false;
            return validTags;
        } catch (MintException e) {
            throw new MintException(e.getMessage());
        }
    }

    //@@author Yitching
    public void checkDuplicateTagsForNormalEdit(String userInput) throws MintException {
        isRecurring = false;
        try {
            ValidityChecker.identifyDuplicateTags(this, userInput);
        } catch (MintException e) {
            throw new MintException(e.getMessage());
        }
    }

    public void checkDuplicateTagsForRecurringEdit(String userInput) throws MintException {
        isRecurring = true;
        try {
            ValidityChecker.identifyDuplicateTags(this, userInput);
        } catch (MintException e) {
            throw new MintException(e.getMessage());
        }
    }

    //@@author yanjia1777
    public void parseType(String userInput) throws MintException {
        parseInputByArguments(userInput);
        if (argumentsArray.length > 1 && Objects.equals(argumentsArray[1], INCOME_KEYWORD)) {

            type = Type.Income;
        } else {
            type = Type.Expense;
        }
    }

    public Entry checkType() {
        if (Objects.equals(argumentsArray[1], INCOME_KEYWORD)) {
            return createIncomeObject();
        } else {
            return createExpenseObject();
        }
    }

    public void parseInputByArguments(String userInput) {
        argumentsArray = userInput.split(SPACE);
    }


    //@@author irvinseet
    private double amountTo2SF(String amountStr) {
        return Math.round(Double.parseDouble(amountStr) * 100.0) / 100.0;
    }

    private Expense createExpenseObject() {
        date = LocalDate.parse(dateStr, dateFormatter);
        amount = amountTo2SF(amountStr);
        int catNum = Integer.parseInt(catNumStr);
        expenseCategory = ExpenseCategory.values()[catNum];
        return new Expense(name, date, amount, expenseCategory);
    }

    //@@author yanjia1777
    private Income createIncomeObject() {
        date = LocalDate.parse(dateStr, dateFormatter);
        amount = amountTo2SF(amountStr);
        int catNum = Integer.parseInt(catNumStr);
        incomeCategory = IncomeCategory.values()[catNum];
        return new Income(name, date, amount, incomeCategory);
    }

    //@@author pos0414

    /**
     * Creates a RecurringExpense object by parsing the string form of fields.
     *
     * @return RecurringExpense object
     * @throws MintException When exception thrown while parsing
     */
    private RecurringExpense createRecurringExpenseObject() throws MintException {
        try {
            date = LocalDate.parse(dateStr, dateFormatter);
            amount = amountTo2SF(amountStr);
            int catNum = Integer.parseInt(catNumStr);
            expenseCategory = ExpenseCategory.values()[catNum];
            interval = Interval.determineInterval(intervalStr);
            endDate = LocalDate.parse(endDateStr, dateFormatter);
        } catch (MintException e) {
            throw new MintException(e.getMessage());
        }
        return new RecurringExpense(name, date, amount, expenseCategory, interval, endDate);
    }

    /**
     * Creates a RecurringIncome object by parsing the string form of fields.
     *
     * @return RecurringIncome object
     * @throws MintException When exception thrown while parsing
     */
    private RecurringIncome createRecurringIncomeObject() throws MintException {
        try {
            date = LocalDate.parse(dateStr, dateFormatter);
            amount = amountTo2SF(amountStr);
            int catNum = Integer.parseInt(catNumStr);
            incomeCategory = IncomeCategory.values()[catNum];
            interval = Interval.determineInterval(intervalStr);
            endDate = LocalDate.parse(endDateStr, dateFormatter);
        } catch (MintException e) {
            throw new MintException(e.getMessage());
        }
        return new RecurringIncome(name, date, amount, incomeCategory, interval, endDate);
    }

    /**
     * Prepare to add a normal entry by initializing & parsing fields.
     *
     * @param userInput User input string
     * @return Command object for adding normal entry
     */
    private Command prepareAddEntry(String userInput) {
        try {
            initDateStr();
            initCatNumStr();
            parseInputByTags(userInput);
            Entry entry = (type == Type.Income) ? createIncomeObject() : createExpenseObject();
            return new AddCommand(entry);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Prepare to delete a normal entry by initializing, parsing, and identifying fields that user queried.
     *
     * @param userInput User input string
     * @return Command object for deleting normal entry
     */
    private Command prepareDeleteEntry(String userInput) {
        try {
            initDateStr();
            initCatNumStr();
            initAmountStr();
            ArrayList<String> validTags = parseInputByTags(userInput);
            if (argumentsArray.length <= 1) {
                throw new MintException(MintException.ERROR_NO_DELIMETER);
            }
            Entry entry = createIncomeObject();
            assert Objects.requireNonNull(validTags).size() >= 1 : "There should be at least one valid tag";
            return new DeleteCommand(validTags, entry);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    //@@author yanjia1777
    private Command prepareView(String userInput) {
        try {
            parseInputByArguments(userInput);
            ViewOptions viewOptions = new ViewOptions(argumentsArray);
            logger.log(Level.INFO, "User execute view");
            return new ViewCommand(viewOptions);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    //@@author pos0414

    /**
     * Prepare to edit normal entry by initializing, parsing, and identifying fields that user queried.
     *
     * @param userInput User input string
     * @return Command object for editing normal entry
     */
    private Command prepareEditEntry(String userInput) {
        try {
            initDateStr();
            initCatNumStr();
            initAmountStr();
            ArrayList<String> validTags = parseInputByTags(userInput);
            Entry entry = createIncomeObject();
            assert validTags.size() >= 1 : "There should be at least one valid tag";
            return new EditCommand(validTags, entry);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Prepare to add a recurring entry by initializing & parsing fields.
     *
     * @param userInput User input string
     * @return Command object for adding recurring entry
     */
    private Command prepareAddRecurringEntry(String userInput) {
        try {
            initDateStr();
            initCatNumStr();
            initEndDateStr();
            isRecurring = true;
            parseInputByTags(userInput);
            RecurringEntry entry = (type == Type.Income)
                    ? createRecurringIncomeObject() : createRecurringExpenseObject();
            return new AddRecurringCommand(entry);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Prepare to delete a recurring entry by initializing, parsing, and identifying fields that user queried.
     *
     * @param userInput User input string
     * @return Command object for deleting recurring entry
     */
    private Command prepareDeleteRecurringEntry(String userInput) {
        try {
            initDateStr();
            initCatNumStr();
            initAmountStr();
            initIntervalStr();
            initEndDateStr();
            isRecurring = true;
            ArrayList<String> validTags = parseInputByTags(userInput);
            if (argumentsArray.length <= 1) {
                throw new MintException(MintException.ERROR_NO_DELIMETER);
            }
            RecurringEntry entry = createRecurringIncomeObject();
            return new DeleteRecurringCommand(validTags, entry);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Prepare to edit recurring entry by initializing, parsing, and identifying fields that user queried.
     *
     * @param userInput User input string
     * @return Command object for editing recurring entry
     */
    private Command prepareEditRecurringEntry(String userInput) {
        try {
            initDateStr();
            initCatNumStr();
            initAmountStr();
            initIntervalStr();
            initEndDateStr();
            isRecurring = true;
            ArrayList<String> validTags = parseInputByTags(userInput);
            assert validTags.size() >= 1 : "There should be at least one valid tag";
            RecurringEntry entry = createRecurringIncomeObject();
            return new EditRecurringCommand(validTags, entry);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    //@@author irvinseet
    private Command prepareSetBudget(String userInput) {
        try {
            parseInputByTags(userInput);
            setCategoryViaCatNum(catNumStr);
            setAmountViaAmountStr(amountStr);
            return new SetBudgetCommand(this.expenseCategory, this.amount);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    //@@author Yitching

    /**
     * Prepares the user input by splitting it into the respective fields to overwrite existing recurring expense.
     *
     * @param entry Entry type variable, casted to RecurringEntry type, that contains all the attributes of the
     *              recurring expense.
     * @return returns a HashMap containing all the different attributes in String.
     */
    public HashMap<String, String> prepareRecurringEntryToAmendForEdit(Entry entry) {
        RecurringEntry recurringEntry = (RecurringEntry) entry;
        String name = recurringEntry.getName();
        LocalDate date = recurringEntry.getDate();
        double amount = recurringEntry.getAmount();
        LocalDate endDate = recurringEntry.getEndDate();
        Interval interval = recurringEntry.getInterval();
        String dateStr = date.toString();
        String amountStr = String.valueOf(amountTo2SF(Double.toString(amount)));
        String endDateStr = endDate.toString();
        String intervalStr = interval.toString();
        String catNumStr = String.valueOf(entry.getCategory().ordinal());
        String[] entryFieldsToAdd = {name, dateStr, amountStr, endDateStr, intervalStr, catNumStr};
        String[] entryFieldKeys = {NAME_KEYWORD, DATE_KEYWORD, AMOUNT_KEYWORD,
            END_DATE_KEYWORD, INTERVAL_KEYWORD, CAT_NUM_KEYWORD};
        HashMap<String, String> entryFields = new HashMap<>();
        for (int index = 0; index < entryFieldsToAdd.length; index++) {
            entryFields.put(entryFieldKeys[index], entryFieldsToAdd[index]);
        }
        return entryFields;
    }

    /**
     * Prepares the user input by splitting it into the respective fields to overwrite existing expense.
     *
     * @param entry Entry type variable that contains all the attributes of the expense.
     * @return returns a HashMap containing all the different attributes in String.
     */
    public HashMap<String, String> prepareEntryToAmendForEdit(Entry entry) {
        String name = entry.getName();
        LocalDate date = entry.getDate();
        double amount = entry.getAmount();
        String catNumStr = String.valueOf(entry.getCategory().ordinal());
        String dateStr = date.toString();
        String amountStr = String.valueOf(amountTo2SF(Double.toString(amount)));
        String[] entryFieldsToAdd = {name, dateStr, amountStr, catNumStr};
        String[] entryFieldKeys = {NAME_KEYWORD, DATE_KEYWORD, AMOUNT_KEYWORD, CAT_NUM_KEYWORD};
        HashMap<String, String> entryFields = new HashMap<>();
        for (int index = 0; index < entryFieldsToAdd.length; index++) {
            entryFields.put(entryFieldKeys[index], entryFieldsToAdd[index]);
        }
        return entryFields;
    }

    /**
     * Conversion of attributes from string to their respective data types.
     *
     * @param entryFields HashMap containing all the String type attributes.
     * @param type        refers to whether it is an expense or an income.
     * @return returns the new RecurringEntry to overwrite the old RecurringEntry.
     */
    public RecurringEntry convertRecurringEntryToRespectiveTypes(HashMap<String, String> entryFields,
            Type type) throws MintException {
        String name = entryFields.get(NAME_KEYWORD);
        String dateStr = entryFields.get(DATE_KEYWORD);
        String amountStr = entryFields.get(AMOUNT_KEYWORD);
        String catNumStr = entryFields.get(CAT_NUM_KEYWORD);
        String intervalStr = entryFields.get(INTERVAL_KEYWORD);
        String endDateStr = entryFields.get(END_DATE_KEYWORD);

        LocalDate date = LocalDate.parse(dateStr, ValidityChecker.dateFormatter);
        double amount = amountTo2SF(amountStr);
        LocalDate endDate = LocalDate.parse(endDateStr, ValidityChecker.dateFormatter);
        int pos = Integer.parseInt(catNumStr);
        ValidityChecker.checkValidCatNum(pos);
        Enum category = type == Type.Expense ? ExpenseCategory.values()[pos] : IncomeCategory.values()[pos];
        Interval interval = Interval.determineInterval(intervalStr);
        if (type == Type.Expense) {
            return new RecurringExpense(name, date, amount, (ExpenseCategory) category, interval, endDate);
        } else {
            return new RecurringIncome(name, date, amount, (IncomeCategory) category, interval, endDate);
        }
    }

    /**
     * Conversion of attributes from string to their respective data types.
     *
     * @param entryFields HashMap containing all the String type attributes.
     * @param type        refers to whether it is an expense or an income.
     * @return returns the new Entry to overwrite the old Entry.
     */
    public Entry convertEntryToRespectiveTypes(HashMap<String, String> entryFields,
            Type type) throws MintException {
        String name = entryFields.get(NAME_KEYWORD);
        String dateStr = entryFields.get(DATE_KEYWORD);
        String amountStr = entryFields.get(AMOUNT_KEYWORD);
        String catNumStr = entryFields.get(CAT_NUM_KEYWORD);

        LocalDate date = LocalDate.parse(dateStr, ValidityChecker.dateFormatter);
        double amount = amountTo2SF(amountStr);
        int pos = Integer.parseInt(catNumStr);
        ValidityChecker.checkValidCatNum(pos);
        Enum category = type == Type.Expense ? ExpenseCategory.values()[pos] : IncomeCategory.values()[pos];
        if (type == Type.Expense) {
            return new Expense(name, date, amount, (ExpenseCategory) category);
        } else {
            return new Income(name, date, amount, (IncomeCategory) category);
        }
    }

    //@@author yanjia1777
    public Command prepareDeleteAll(String userInput) {
        try {
            parseInputByArguments(userInput);
            if (Objects.equals(argumentsArray[1], TYPE_NORMAL) || Objects.equals(argumentsArray[1], NORMAL_KEYWORD)) {
                return new DeleteAllCommand(true, false);
            } else if (Objects.equals(argumentsArray[1], TYPE_RECURRING)
                    || Objects.equals(argumentsArray[1], RECURRING_KEYWORD)) {
                return new DeleteAllCommand(false, true);
            } else {
                throw new MintException(MintException.ERROR_NO_DELIMETER);
            }
        } catch (IndexOutOfBoundsException e) {
            return new DeleteAllCommand(true, true);
        } catch (MintException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    //@@author pos0414
    public Command parseCommand(String userInput) {
        this.command = parserExtractCommand(userInput);
        switch (command) {
        case ADD_ENTRY:
            return prepareAddEntry(userInput);
        case DELETE_ENTRY:
            return prepareDeleteEntry(userInput);
        case EDIT_ENTRY:
            return prepareEditEntry(userInput);
        case ADD_RECURRING:
            return prepareAddRecurringEntry(userInput);
        case DELETE_RECURRING:
            return prepareDeleteRecurringEntry(userInput);
        case EDIT_RECURRING:
            return prepareEditRecurringEntry(userInput);
        case SET_BUDGET:
            return prepareSetBudget(userInput);
        case VIEW_BUDGET:
            return new ViewBudgetCommand();
        case DELETEALL:
            //fallthrough
        case DELETEALL2:
            return prepareDeleteAll(userInput);
        case VIEW:
            return prepareView(userInput);
        case VIEW_ALL_CATEGORIES:
            return new ViewCategoriesCommand();
        case HELP:
            return new HelpCommand();
        case EXIT:
            return new ExitCommand();
        default:
            return new InvalidCommand(MintException.ERROR_INVALID_COMMAND);
        }
    }
}