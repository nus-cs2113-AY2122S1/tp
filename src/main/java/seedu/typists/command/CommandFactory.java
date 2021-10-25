package seedu.typists.command;

public class CommandFactory {

    public Command getCommand(String commandType) {
        switch (commandType) {
        case "time":
            return new TimeGameCommand();
        case "word":
            return new WordGameCommand();
        case "content":
            // return something
            return null;
        case "error":
            //something
        case "bye":
            return new ExitCommand();
        default:
            System.out.println("invalid command");
            return null;
        }
    }
}
