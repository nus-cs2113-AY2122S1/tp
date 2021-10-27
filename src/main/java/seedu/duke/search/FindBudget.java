package seedu.duke.search;

import seedu.duke.data.AllRecordList;
import seedu.duke.data.records.Expenditure;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindBudget {
    private static final String DIVIDER = "========================================================";

    public static void findExpenditure(AllRecordList recordList, String keyword) {
        System.out.println("Here are the match we have found!");

        for (int i = 1; i <= 12; i++) {
            ArrayList<Expenditure> budgetList = recordList.getExpenditureRecords(i);

            ArrayList<Expenditure> matchedExpenditureList = (ArrayList<Expenditure>) budgetList.stream()
                    .filter(n -> n.toString().contains(keyword))
                    .collect(Collectors.toList());

            int sizeOfMatchedList = matchedExpenditureList.size();

            if (sizeOfMatchedList == 0) {
                System.out.println("No results found for this month");
            } else {
                for (int j = 0; j < sizeOfMatchedList; j += 1) {
                    System.out.print((j + 1) + ". ");
                    System.out.println(matchedExpenditureList.get(j).toString());
                }
            }

            System.out.println(DIVIDER);
        }
    }
}
