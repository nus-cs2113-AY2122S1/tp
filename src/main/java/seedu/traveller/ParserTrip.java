package seedu.traveller;

import seedu.traveller.commands.Command;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.Dijkstra;
import seedu.traveller.mapper.EmptyVertexException;
import seedu.traveller.mapper.GraphList;
import seedu.traveller.mapper.Vertex;

public class ParserTrip {
    public static Command parse(String rawInput) throws TravellerException {
        String details;
        Command command = null;

        String[] userInput = rawInput.split(" ", 5);
        String userCommand = userInput[0].toLowerCase();

        switch (userCommand) {
        case "new":
            try {
                String tripName = userInput[1];
                command = new NewCommand(tripName);
                Vertex s = GraphList.findVertex(userInput[2]);
                Vertex t = GraphList.findVertex(userInput[3]);
                System.out.println("Finding shortest path!");
                Dijkstra.run(s, t);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidNewFormatException();
            } catch (EmptyVertexException e) {
                System.out.println("Either of the nodes doesn't exist!");
            }

            break;
        case "viewall":
            command = new ViewAllCommand();
            break;
        case "exit":
            command = new ExitCommand();
            break;
        /*case "d":
            try {

                //Dijkstra.run(s, t);}

                break;*/
        default:
            throw new CommandNotFoundException(userCommand);
        }
        return command;
    }
}
