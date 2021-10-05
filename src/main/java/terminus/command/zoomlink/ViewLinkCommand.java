package terminus.command.zoomlink;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.LinkManager;
import terminus.exception.InvalidArgumentException;
import terminus.schedule.ZoomLink;
import terminus.ui.Ui;

public class ViewLinkCommand<T> extends Command {

    private T type;

    private int itemNumber;

    public ViewLinkCommand(T type) {
        this.type = type;
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_VIEW_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_VIEW;
    }

    /*@Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if(arguments == null || arguments.isBlank()){
            return;
        }
        try{
            itemNumber = Integer.parseInt(arguments);
            displayAll = false;
        }catch(NumberFormatException e){
            throw new InvalidArgumentException("Error: Invalid numerical value.");
        }
    }

     */

    @Override
    public CommandResult executeLink(Ui ui, ZoomLink zoomLink) throws InvalidArgumentException {
        LinkManager linkManager = zoomLink.getLinkManager();
        linkManager.setSchedules(zoomLink.get(type));
        String result = "";
        result = linkManager.listAllLinks();
        ui.printSection(result);
        return new CommandResult(true,false);
    }
}
