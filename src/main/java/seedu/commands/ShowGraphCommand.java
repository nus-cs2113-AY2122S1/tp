package seedu.commands;

import seedu.utility.FinancialTracker;
import seedu.utility.StonksGraph;
import seedu.utility.Ui;

public class ShowGraphCommand extends Command {

    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        StonksGraph stonksGraph = new StonksGraph(finances);
        ui.printGraph(stonksGraph.convertGridToString());
    }
}
