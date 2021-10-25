package medbot.command;

import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class GetCurrentViewCommand extends Command {
    private ViewType viewType;

    public GetCurrentViewCommand(ViewType viewType) {
        this.viewType = viewType;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String viewMessage = Ui.getCurrentViewMessage(viewType);
        ui.printOutput(viewMessage);
    }
}
