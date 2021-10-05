package terminus.command.zoomlink;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.schedule.ZoomLink;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.LinkManager;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.ui.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddLinkCommand extends Command {

    private String description;
    private String day;
    private String startTime;
    private String zoomLink;

    private int totalArg;

    public AddLinkCommand() {
        this.totalArg = 4;
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD + " \"{description}\" " +
                "\"{day}\" \"{start_time}\" \"{zoom_link}\"";
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        // Perform required checks with regex
        if(arguments == null || arguments.isBlank()){
            throw new InvalidArgumentException("Error: Missing arguments.");
        }
        ArrayList<String> argArray = findArguments(arguments);
        if(!isValidArguments(argArray)){
            throw new InvalidArgumentException("Error: Missing arguments.");
        }
        this.description = argArray.get(0);
        this.day = argArray.get(1);
        this.startTime = argArray.get(2);
        this.zoomLink = argArray.get(3);
    }

    @Override
    public CommandResult executeLink(Ui ui, ZoomLink link) throws InvalidCommandException {
        LinkManager linkManager = link.getLinkManager();
        linkManager.setSchedules(link.getSchedules());
        linkManager.addLink(description, day, startTime, zoomLink);
        link.setSchedules(linkManager.getSchedules());
        return new CommandResult(true,false);
    }

    private ArrayList<String> findArguments(String arg){
        ArrayList<String> argsArray = new ArrayList<>();
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(arg);
        while(m.find()){
            argsArray.add(m.group(1));
        }
        return argsArray;
    }

    private boolean isValidArguments(ArrayList<String> argArray){
        boolean isValid = true;
        if(argArray.size() != totalArg){
            isValid = false;
        }else if(isArrayEmpty(argArray)){
            isValid = false;
        }
        return isValid;
    }

    private boolean isArrayEmpty(ArrayList<String> argArray){
        for(String s:argArray){
            if(s.isBlank()){
                return true;
            }
        }
        return false;
    }

}
