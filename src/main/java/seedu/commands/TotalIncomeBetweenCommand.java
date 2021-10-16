package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;

public class TotalIncomeBetweenCommand extends Command {
    private LocalDate start;
    private LocalDate end;
    
    public TotalIncomeBetweenCommand(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        double totalExpenseBetween = finances.getTotalIncomeBetween(start,end);
        ui.printTotalIncomeBetween(totalExpenseBetween,start,end);
    }
}

