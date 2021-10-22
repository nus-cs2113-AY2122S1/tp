package seedu.duke.commands;

public class HelpCommand extends Command {
    public HelpCommand() {
        String help
                = "Here are the list of commands:\n"
                + "[list /muni]: Display all universities from the master list\n"
                + "[list /suni]: Display all the universities and their module mappings added by the user\n"
                + "[list /mmod]: Display all the modules from the master list\n"
                + "[list /suni]: Display all the modules added by the user\n"
                + "[add /uni <uni index/name>]: Add a university to the selected list\n"
                + "[add /mod <mod index/name>]: Add a module to the selected list\n"
                + "[searchmap <uni index>]: Displays the available module mappings for the "
                + "selected modules for that university\n"
                + "[addmap <uni index> <mapping index>]: Add that module mapping to the selected list\n"
                + "[remove /uni or /mod or /map <index>]: Remove the specified university/module/mapping";
        System.out.println(help);
    }
}
