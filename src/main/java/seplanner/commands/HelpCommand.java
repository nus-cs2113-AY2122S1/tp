package seplanner.commands;

//@@author titustortoiseturtle1999
/**
 * Help command to display all commands functions.
*/
public class HelpCommand extends Command {
    public HelpCommand() {
        String help
                = "Here are the commands:\n"
                + "1:  list /muni --------------------------- Display all universities from the master list\n"
                + "2:  list /suni ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Display all the universities and their module "
                + "mappings added by the user\n"
                + "3:  list /mmod --------------------------- Display all the modules from the master list\n"
                + "4:  list /suni ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Display all the modules added by the user\n"
                + "5:  find /uni <search field> ------------- Find universities that contains the search field\n"
                + "6:  find /mod <search field> ~~~~~~~~~~~~~ Find module names that contain the search field\n"
                + "7:  find /code <search field> ------------ Find module codes that contain the search field\n"
                + "8:  add /uni <uni index/name> ~~~~~~~~~~~~ Add a university to the selected list\n"
                + "9:  add /mod <mod index/name> ------------ Add a module to the selected list\n"
                + "10: searchmap <uni index> ~~~~~~~~~~~~~~~~ Display the available module mappings for "
                + "selected modules for that university\n"
                + "11: searchmap all ------------------------ Display available module mappings for all universities\n"
                + "12: add /map <uni index> <mapping index> ~ Add that module mapping to the selected list\n"
                + "13: remove </uni> <index> ---------------- Remove the specified university\n"
                + "14: remove </mod> <index> ~~~~~~~~~~~~~~~~ Remove the specified module\n"
                + "15: remove /map <uni index> <map index> -- Remove the specified module mapping";
        System.out.println(help);
    }
}
