package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;

public class TotalExpenseBetweenCommand extends Command {
    private LocalDate start;
    private LocalDate end;
    
    public TotalExpenseBetweenCommand(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        double totalExpenseBetween = finances.getTotalExpenseBetween(start,end);
        ui.printTotalExpenseBetween(totalExpenseBetween,start,end);
    }
}

