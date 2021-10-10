package seedu.traveller;

import seedu.traveller.commands.*;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.Dijkstra;
import seedu.traveller.mapper.EmptyVertexException;
import seedu.traveller.mapper.GraphList;
import seedu.traveller.mapper.Vertex;

import java.util.List;

public class ParserTrip {
    public static Command parse(String rawInput) throws TravellerException, EmptyVertexException {
        String details;
        Command command = null;

        String[] userInput = rawInput.split(" ", 5);
        String userCommand = userInput[0].toLowerCase();

        switch (userCommand) {
        case "new":
            try {
                String tripName = userInput[1];
                String origin = userInput[2];
                String destination = userInput[3];
                Vertex s = GraphList.findVertex(origin);
                Vertex t = GraphList.findVertex(destination);
                System.out.println("Finding shortest path!");
                List<Vertex> path = Dijkstra.run(s, t);
                command = new NewCommand(tripName, origin, destination, path);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidNewFormatException();
            }
            break;
//        case "edit":
//            try {
//                String tripName = userInput[1];
//                String origin = userInput[2];
//                String destination = userInput[3];
//                Vertex s = GraphList.findVertex(origin);
//                Vertex t = GraphList.findVertex(destination);
//                List<Vertex> path = Dijkstra.run(s, t);
//                command = new EditCommand(tripName, origin, destination, path);
//            } catch (ArrayIndexOutOfBoundsException e) {
//                throw new InvalidNewFormatException();
//            } catch (EmptyVertexException e) {
//                System.out.println("Either of the nodes doesn't exist!");
//            }
//            break;
        case "delete":
            command = new DeleteCommand(userInput[1]);
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
