package seedu.typists.parser;

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
        default:
            return null;
        }
    }
}
