package seedu.duke.logic.parser;

import seedu.duke.utility.MintException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

//@@author yanjia1777
public class ViewOptions {
    public String sortType;
    public boolean onlyExpense = false;
    public  boolean onlyIncome = false;
    public LocalDate fromDate = null;
    public LocalDate endDate = null;
    public Month month;
    public int year = 0;
    public boolean isViewAll = true;
    public boolean isAscending = false;
    public boolean isViewExpenseOrIncome = false;

    public ViewOptions(String[] argumentArrayInput) throws MintException {
        ArrayList<String>  argumentArray = new ArrayList<>(Arrays.asList(argumentArrayInput));

        expenseCheck(argumentArray);
        incomeCheck(argumentArray);
        sortCheck(argumentArray);
        yearCheck(argumentArray);
        monthCheck(argumentArray);
        dateCheck(argumentArray);
        ascendingCheck(argumentArray);
    }

    public void expenseCheck(ArrayList<String> argumentArray) {
        if (argumentArray.contains("expense")) {
            onlyExpense = true;
            isViewExpenseOrIncome = true;
        }
    }

    public void incomeCheck(ArrayList<String> argumentArray) {
        if (argumentArray.contains("income")) {
            onlyIncome = true;
            isViewExpenseOrIncome = true;
        }
    }

    public void sortCheck(ArrayList<String> argumentArray) throws MintException {
        if (argumentArray.contains("by")) {
            try {
                sortType = argumentArray.get(argumentArray.indexOf("by") + 1);
            } catch (IndexOutOfBoundsException e) {
                throw new MintException(MintException.ERROR_INVALID_SORTTYPE);
            }
        }
    }

    public void yearCheck(ArrayList<String> argumentArray) throws MintException {
        if (argumentArray.contains("year")) {
            try {
                isViewAll = false;
                year = Integer.parseInt(argumentArray.get(argumentArray.indexOf("year") + 1));
                if (year < 2000 || year > 2200) {
                    throw new MintException(MintException.ERROR_INVALID_YEAR);
                }
            } catch (NumberFormatException e) {
                throw new MintException(MintException.ERROR_INVALID_YEAR);
            } catch (IndexOutOfBoundsException e) {
                year = LocalDate.now().getYear();
            }
        }
    }

    public void monthCheck(ArrayList<String> argumentArray) throws MintException {
        if (argumentArray.contains("month")) {
            try {
                isViewAll = false;
                month = Month.of(Integer.parseInt(argumentArray.get(argumentArray.indexOf("month") + 1)));
            } catch (DateTimeException | NumberFormatException e) {
                throw new MintException(MintException.ERROR_INVALID_MONTH);
            } catch (IndexOutOfBoundsException e) {
                month = LocalDate.now().getMonth();
            }
            if (year == 0) {
                year = LocalDate.now().getYear();
            }
        }
    }

    public void dateCheck(ArrayList<String> argumentArray) throws MintException {
        if (argumentArray.contains("from")) {
            try {
                fromDate = LocalDate.parse(argumentArray.get(argumentArray.indexOf("from") + 1));
                try {
                    endDate = LocalDate.parse(argumentArray.get(argumentArray.indexOf("from") + 2));
                } catch (IndexOutOfBoundsException | DateTimeParseException ignored) {
                    endDate = null;
                }
                if (endDate == null) {
                    endDate = LocalDate.now();
                }
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new MintException(MintException.ERROR_INVALID_DATE);
            }
            isViewAll = false;
        }
    }

    public void ascendingCheck(ArrayList<String> argumentArray) {
        if (argumentArray.contains("ascending") || argumentArray.contains("up")) {
            isAscending = true;
        }
    }
}
