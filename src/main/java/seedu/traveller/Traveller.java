package seedu.traveller;


import seedu.traveller.commands.Command;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.*;
import seedu.traveller.database.DatabaseInput;

import java.util.Objects;
import java.util.Scanner;

public class Traveller {
    private final Ui ui;
    private final TripsList tripsList;

    public Traveller() {
        ui = new Ui();
        tripsList = new TripsList();
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Dijkstra dijkstra = new Dijkstra();
                int i = 0;

//                Vertex v1 = new Vertex("SGP", ++i);
//                Vertex v2 = new Vertex("MLY", ++i);
//                Vertex v3 = new Vertex("HKD", ++i);
//                Vertex v4 = new Vertex("LAX", ++i);
//
//                GraphList.addVertex(v1);
//                GraphList.addVertex(v2);
//                GraphList.addVertex(v3);
//                GraphList.addVertex(v4);
//
//                GraphList.createEdge(1.0, v1, v2);
//                GraphList.createEdge(2.0, v1, v3);
//                GraphList.createEdge(3.0, v2, v4);
//                GraphList.createEdge(4.0, v3, v4);
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = ParserTrip.parse(fullCommand);
                c.execute(tripsList, ui);
                isExit = c.getExit();
            } catch (TravellerException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public void setupRoutes() {
        Dijkstra dijkstra = new Dijkstra();
        int i = 0;

//        Vertex v1 = new Vertex("SGP", ++i);
//        Vertex v2 = new Vertex("MLY", ++i);
//        Vertex v3 = new Vertex("HKD", ++i);
//        Vertex v4 = new Vertex("LAX", ++i);
//
//        GraphList.addVertex(v1);
//        GraphList.addVertex(v2);
//        GraphList.addVertex(v3);
//        GraphList.addVertex(v4);
//
//        GraphList.createEdge(1.0, v1, v2);
//        GraphList.createEdge(2.0, v1, v3);
//        GraphList.createEdge(3.0, v2, v4);
//        GraphList.createEdge(4.0, v3, v4);

        //dijkstra.run(v1, v4); //expected output A-B-D, dist=4.0
       // GraphList.modifyEdge(11.0, v1, v2);
        //dijkstra.run(v1, v4); //expected output A-C-D, dist=6.0

        //commented out below but is important

//        Scanner in = new Scanner(System.in);
//        ParserRoute ParserRoute = new ParserRoute();
//        String line;
//
//        while (!Objects.equals(ParserRoute.input(), "bye")) {
//
//            System.out.println("Give me an instruction Prof Wee!");
//            line = in.nextLine();
//            ParserRoute.feed(line);
//
//            if (Objects.equals(ParserRoute.keyword(), "v")) {
//                if (!GraphList.doesContains(ParserRoute.item())) {
//                    System.out.println("Adding new node!");
//                    Vertex v = new Vertex(ParserRoute.item(), ++i);
//                    GraphList.addVertex(v);
//                } else {
//                    System.out.println("Node already in list, try another name boss!");
//                }
//            } else if (Objects.equals(ParserRoute.keyword(), "e")) {
//                if (!GraphList.doesContains(ParserRoute.item())) {
//                    try {
//                        Vertex s = GraphList.findVertex(ParserRoute.secondPhrase());
//                        Vertex t = GraphList.findVertex(ParserRoute.thirdPhrase());
//                        if (GraphList.edgeExists(s, t)) {
//                            System.out.println("Modifying edge!");
//                            GraphList.modifyEdge(Double.parseDouble(ParserRoute.firstPhrase()), s, t);
//                        } else {
//                            System.out.println("Creating edge!");
//                            GraphList.createEdge(Double.parseDouble(ParserRoute.firstPhrase()), s, t);
//                        }
//                    } catch (EmptyVertexException e) {
//                        System.out.println("Either of the nodes doesn't exist!");
//                    }
//                }
//            } else if (Objects.equals(ParserRoute.keyword(), "d")) {
//                if (!GraphList.doesContains(ParserRoute.item())) {
//                    try {
//                        Vertex s = GraphList.findVertex(ParserRoute.firstPhrase());
//                        Vertex t = GraphList.findVertex(ParserRoute.secondPhrase());
//                        System.out.println("Finding shortest path!");
//                        dijkstra.run(s, t);
//                    } catch (EmptyVertexException e) {
//                        System.out.println("Either of the nodes doesn't exist!");
//                    }
//                }
//            }
//        }



    }


    public static void main(String[] args) {
        DatabaseInput.readFile();
        //new Traveller().setupRoutes();
        listEverything();
        new Traveller().run();
    }

    /*
    This function lists all the vertexes(cities) and edges(hours)
     */
    public static void listEverything() {
        Vertex from;
        Vertex to;
        for (int a = 0; a < 5; a++) {
            System.out.println(GraphList.vertexArray.get(a));
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                from = GraphList.vertexArray.get(i);
                to = GraphList.vertexArray.get(j);
                System.out.println("From : " + from + " To : " + to
                        + " is " + GraphList.edgeMatrix[from.key()][to.key()]);
            }
        }
    }
}

