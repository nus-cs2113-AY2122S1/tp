package cooper.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.dopsun.chatbot.cli.Argument;
import com.dopsun.chatbot.cli.ParseResult;
import com.dopsun.chatbot.cli.Parser;

import cooper.command.AddCommand;
import cooper.command.AvailableCommand;
import cooper.command.Command;
import cooper.command.ExitCommand;
import cooper.command.ListCommand;
import cooper.command.LoginCommand;
import cooper.command.MeetingsCommand;
import cooper.command.HelpCommand;
import cooper.exceptions.InvalidArgumentException;
import cooper.exceptions.InvalidCommandException;
import cooper.ui.Ui;
import cooper.util.Util;


public class CommandParser extends ParserBase {

    private Parser parser;
    private Ui ui;

    /**
     * Constructor. Initialise internal parser.
     */
    public CommandParser(Ui ui) throws URISyntaxException {
        super();

        this.ui = ui;
        try {
            InputStream commandSetInputStream = this.getClass().getResourceAsStream("/parser/command-data.properties");

            File commandSetTmpFile = Util.inputStreamtoTmpFile(commandSetInputStream,
                    System.getProperty("user.dir") + "/tmp", "/tmp_file_command.txt");

            InputStream trainingPathInputStream = this.getClass().getResourceAsStream("/parser/training-data.yml");
            File trainingTmpFile = Util.inputStreamtoTmpFile(trainingPathInputStream,
                    System.getProperty("user.dir") + "/tmp", "/tmp_file_training.txt");

            parser = prepareParser(commandSetTmpFile.getPath(), trainingTmpFile.getPath());

        } catch (IOException e) {
            ui.showText("Error encountered when creating temp file: "
                    + System.getProperty("user.dir") + "/tmp" + "/tmp_file_command.txt" + " or "
                    + System.getProperty("user.dir") + "/tmp" + "/tmp_file_training.txt");
        }
    }

    /**
     * API to parse a command in string.
     * @param input command to be parsed
     * @return a command object, to be passed into commandhandler
     */
    public Command parse(String input) throws InvalidCommandException, InvalidArgumentException {
        if (input.split(" ").length < 2) {
            return parseSimpleInput(input);
        } else {
            return parseComplexInput(input);
        }
    }

    private Command parseSimpleInput(String input) throws InvalidCommandException {
        switch (input) {
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "meetings":
            return new MeetingsCommand();
        case "exit":
            return new ExitCommand();
        default:
            ui.showText("Unrecognised command: " + input);
            throw new InvalidCommandException();
        }
    }

    private Command parseComplexInput(String input) throws InvalidCommandException, InvalidArgumentException {
        Optional<ParseResult> optResult = parser.tryParse(input);
        if (optResult.isPresent()) {
            var result = optResult.get();
            String command = result.allCommands().get(0).name();
            // String template = res.allCommands().get(0).template();
            List<Argument> commandArgs = result.allCommands().get(0).arguments();
            switch (command) {
            case "available":
                return parseAvailableArgs(commandArgs);
            case "login":
                return parseLoginArgs(commandArgs);
            case "add":
                return parseAddArgs(commandArgs);
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }


    private Command parseAddArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        String amount = "";
        boolean isInflow = true;
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
                case ("amount-hint"):
                    if (argVal.charAt(0) == '(' && argVal.charAt(argVal.length()-1) == ')') {
                        isInflow = false;
                        amount = argVal.substring(1,argVal.length()-1);
                    } else {
                        isInflow = true;
                        amount = argVal;
                    }
                    break;
                default:
                    throw new InvalidArgumentException();
            }
        }
        return new AddCommand(amount, isInflow);
    }
    private Command parseLoginArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new LoginCommand();
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            System.out.println("Argument name: " + argName);
            System.out.println("Argument val: " + argVal);
            /*
            switch (argName) {
            case "task-hint":
                command.setTaskDescription(argVal);
                break;
            /
            case "date-hint":
                command.setTimeInfo(argVal);
                break;
            /
            default:
                ui.showText("Unrecognised argument for todo: " + argName);
                throw new InvalidArgumentException();
            }
            */
        }
        return command;

    }
    private Command parseAvailableArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new AvailableCommand();
        /*
        command.setTaskType(TaskType.TODO);
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "task-hint":
                command.setTaskDescription(argVal);
                break;
            /
            case "date-hint":
                command.setTimeInfo(argVal);
                break;
            /
            default:
                ui.showText("Unrecognised argument for todo: " + argName);
                throw new InvalidArgumentException();
            }
        }
        */
        return command;
    }

}
