package seedu.duke.finance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinanceParserTest {

    @Test
    void addFinance_valid_success() {
        FinanceList finances = new FinanceList();
        FinanceParser financeParser = new FinanceParser();
        String[] addCommand = "add-finance/2021-10-27/100".trim().split("/");
        financeParser.addFinance(addCommand, finances);
        assertEquals(1, finances.financeList.size());
    }

    @Test
    void deleteFinance_valid_success() {
        FinanceList finances = new FinanceList();
        FinanceParser financeParser = new FinanceParser();
        String[] addCommand = "add-finance/2021-10-27/100".trim().split("/");
        String[] deleteCommand = "remove-finance/1".trim().split("/");
        financeParser.addFinance(addCommand, finances);
        assertEquals(1, finances.financeList.size());
        financeParser.deleteFinance(deleteCommand, finances);
        assertEquals(0, finances.financeList.size());
    }

    @Test
    void editFinance_valid_success() {
        FinanceList finances = new FinanceList();
        FinanceParser financeParser = new FinanceParser();
        String[] addCommand = "add-finance|2021-10-27|100".trim().split("\\|");
        String[] editCommand = "edit-dish|1|200".trim().split("\\|");
        financeParser.addFinance(addCommand, finances);
        assertEquals(1, finances.financeList.size());
        financeParser.editFinance(editCommand, finances);
        assertEquals(200, finances.financeList.get(0).getAccount());
    }
}
