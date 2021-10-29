package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String DIVIDER = "========================================================";
    private static final String TITLE_DIVIDER = "/////////////////////////////////////////////////////////";
    private static final String EXPENDITURE_LOAN_DIVIDER = "--------------------------------------------------------";
    public String keyword;

    public FindCommand(String commandParams) {
        this.keyword = commandParams;
    }

    public void execute(boolean isLoadingStorage) {
        System.out.println("Here are the Expenditure and Loan match we have found!");
        System.out.println(TITLE_DIVIDER);

        for (int i = 1; i <= 12; i++) {
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

            if (sizeOfMatchedExpenditureList == 0) {
                System.out.println("No Expenditures found for this month");
            } else {
                System.out.println("Here are the Expenditures we found!");
                System.out.println(TITLE_DIVIDER);
                for (int j = 0; j < sizeOfMatchedExpenditureList; j += 1) {
                    System.out.print((j + 1) + ". ");
                    System.out.println(matchedExpenditureList.get(j).toString());
                }
            }

            System.out.println(EXPENDITURE_LOAN_DIVIDER);

            if (sizeOfMatchedLoanList == 0) {
                System.out.println("No Loan found for this month");
            } else {
                System.out.println("Here are the Loan we found!");
                System.out.println(TITLE_DIVIDER);
                for (int j = 0; j < sizeOfMatchedLoanList; j += 1) {
                    System.out.print((j + 1) + ". ");
                    System.out.println(matchedLoanList.get(j).toString());
                }
            }

            System.out.println(DIVIDER);
        }
    }
}
