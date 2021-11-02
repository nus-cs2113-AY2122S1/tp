package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.ui.TextUi;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String DIVIDER = "========================================================";
    public static final String TITLE_DIVIDER = "/////////////////////////////////////////////////////////";
    public static final String EXPENDITURE_LOAN_DIVIDER = "--------------------------------------------------------";
    public String keyword;
    
    public FindCommand(String commandParams) {
        this.keyword = commandParams;
    }

    public void execute(boolean isLoadingStorage) {
        System.out.println("Here are the Expenditure and Loan matches we have found!");
        System.out.println(TITLE_DIVIDER);

        for (int i = 1; i <= 12; i++) {
            findExpenditureLoanOfMonth(i);
        }
    }

    public void findExpenditureLoanOfMonth(int i) {
        ArrayList<Expenditure> budgetList = allRecordList.getExpenditureRecords(i);
        ArrayList<Loan> loanList = allRecordList.getLoanRecords(i);

        ArrayList<Expenditure> matchedExpenditureList = (ArrayList<Expenditure>) budgetList.stream()
                .filter(n -> n.toString().contains(keyword))
                .collect(Collectors.toList());

        ArrayList<Loan> matchedLoanList = (ArrayList<Loan>) loanList.stream()
                .filter(n -> n.toString().contains(keyword))
                .collect(Collectors.toList());

        int sizeOfMatchedExpenditureList = matchedExpenditureList.size();
        int sizeOfMatchedLoanList = matchedLoanList.size();

        TextUi.printExpenditureLoanFoundInMonth(matchedExpenditureList,
                matchedLoanList, sizeOfMatchedExpenditureList, sizeOfMatchedLoanList);
    }

}
