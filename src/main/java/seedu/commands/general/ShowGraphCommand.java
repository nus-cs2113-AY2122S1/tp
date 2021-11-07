
package seedu.commands.general;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.StonksGraph;
import seedu.utility.Ui;

import java.time.LocalDate;

public class ShowGraphCommand extends Command {
    private int year;

    public ShowGraphCommand() {
        this.year = currentYear();
    }

    //@@author KZQ1999
    private int currentYear() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear();
    }
    //@@author KZQ1999
    
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        StonksGraph stonksGraph = new StonksGraph(finances,year);
        ui.printGraph(stonksGraph);
    }
}


