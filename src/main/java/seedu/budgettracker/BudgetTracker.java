package seedu.budgettracker;

import java.time.LocalDate;
import java.lang.String;

import seedu.budgettracker.logic.commands.Command;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.logic.parser.Parser;
import seedu.budgettracker.storage.Storage;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_MONTH;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INPUT;

public class BudgetTracker {
    private final TextUi textUi;
    private final Parser parser;
    private static int loanCounter = 1;
    private final AllRecordList recordList;
    private final String recordListDirectory = "";

    public BudgetTracker() {
        recordList = new AllRecordList();
        textUi = new TextUi();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new BudgetTracker().run();
    }

    /**
     * Loan reminder to warn user that the following loans are due.
     *
     * @param dateNow the local date now
     * @param dateMonthNow the month param of date now as an integer
     */
    public void loanReminder(LocalDate dateNow, int dateMonthNow) {
        for (int i = 0; i < recordList.getLoanListSize(dateMonthNow); i++) {
            recordList.getLoan(i,dateMonthNow).setDueDate();
            if (dateNow.isAfter(recordList.getLoan(i,dateMonthNow).getDueDate())) {
                TextUi.showLoanReminder(loanCounter, recordList.getLoan(i, dateMonthNow));
                loanCounter++;
            }
        }
    }

    public void run() {
        TextUi.showWelcomeMessage();
        Storage budgetStorage = new Storage();
        budgetStorage.makeStorageTextFile(recordListDirectory);
        recordList.storageDirectory = budgetStorage.loadStorage(recordList, recordListDirectory);
        recordList.statIntro(recordList);
        LocalDate dateNow = LocalDate.now();
        String[] dateNowString = dateNow.toString().split("-",3);
        int dateMonthNow = Integer.parseInt(dateNowString[1]);

        // Calls loanReminder to remind user of loans that are due.
        loanReminder(dateNow, dateMonthNow);
        if (dateMonthNow != 1) {
            loanReminder(dateNow, dateMonthNow - 1);
        } else {
            loanReminder(dateNow, 12);
        }

        TextUi.printDivider();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = textUi.getUserInput();
                Command command = parser.parseCommand(userInput);
                command.setAllRecordList(recordList);
                command.execute();
                isExit = command.isExit();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MESSAGE_INVALID_INPUT);
                TextUi.printDivider();
            } catch (NullPointerException npe) {
                System.out.println(MESSAGE_INVALID_MONTH);
                TextUi.printDivider();
            } catch (CommandException ce) {
                System.out.println(ce.getMessage());
                TextUi.printDivider();
            }
        }
    }
}